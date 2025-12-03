package com.gestaofinanceirapessoal.services;

import com.gestaofinanceirapessoal.domains.Banco;
import com.gestaofinanceirapessoal.domains.dtos.BancoDTO;
import com.gestaofinanceirapessoal.domains.dtos.ContaDTO;
import com.gestaofinanceirapessoal.repositories.BancoRepository;
import com.gestaofinanceirapessoal.services.exceptions.DataIntegrityViolationException;
import com.gestaofinanceirapessoal.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepo;

    public List<BancoDTO> findAll() {
        return bancoRepo.findAll().stream()
                .map(BancoDTO::new)
                .collect(Collectors.toList());
    }

    public Banco findById(Long id) {
        return bancoRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Banco não encontrado! Id: " + id));
    }

    public BancoDTO findByIdWithContas(Long id) {
        Banco banco = findById(id);
        BancoDTO dto = new BancoDTO(banco);
        dto.setContas(banco.getContas().stream().map(conta -> id).collect(Collectors.toList()));
        return dto;
    }

    public Banco create(BancoDTO dto) {
        dto.setId(null);
        validaCnpj(dto);
        Banco obj = new Banco(dto);
        return bancoRepo.save(obj);
    }

    public Banco update(Long id, BancoDTO dto) {
        dto.setId(id);
        Banco oldObj = findById(id);
        validaCnpj(dto);
        oldObj.setCnpj(dto.getCnpj());
        oldObj.setRazaoSocial(dto.getRazaoSocial());
        return bancoRepo.save(oldObj);
    }

    public void delete(Long id) {
        Banco obj = findById(id);
        bancoRepo.deleteById(id);
    }

    private void validaCnpj(BancoDTO dto) {
        Optional<Banco> obj = bancoRepo.findByCnpj(dto.getCnpj());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("CNPJ já cadastrado!");
        }
    }
}

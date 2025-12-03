package com.gestaofinanceirapessoal.services;

import com.gestaofinanceirapessoal.domains.Conta;
import com.gestaofinanceirapessoal.domains.dtos.ContaDTO;
import com.gestaofinanceirapessoal.repositories.ContaRepository;
import com.gestaofinanceirapessoal.services.exceptions.DataIntegrityViolationException;
import com.gestaofinanceirapessoal.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepo;

    @Autowired
    private BancoService bancoService;

    @Autowired
    private UsuarioService usuarioService;

    public List<ContaDTO> findAll() {
        return contaRepo.findAll().stream()
                .map(ContaDTO::new)
                .collect(Collectors.toList());
    }

    public List<ContaDTO> findByUsuario(Long usuarioId) {
        return contaRepo.findByUsuario(usuarioService.findById(usuarioId)).stream()
                .map(ContaDTO::new)
                .collect(Collectors.toList());
    }

    public List<ContaDTO> findByBanco(Long bancoId) {
        return contaRepo.findByBancoId(bancoId).stream()
                .map(ContaDTO::new)
                .collect(Collectors.toList());
    }

    public Conta findById(Long id) {
        return contaRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada! Id: " + id));
    }

    public Conta create(ContaDTO dto) {
        dto.setId(null);
        Conta obj = new Conta(dto);
        obj.setBanco(bancoService.findById(dto.getBanco())); // Corrigido para buscar o ID do Banco
        obj.setUsuario(usuarioService.findById(dto.getUsuarioId()));
        return contaRepo.save(obj);
    }

    public Conta update(Long id, ContaDTO dto) {
        dto.setId(id);
        Conta oldObj = findById(id);
        oldObj = new Conta(dto);
        oldObj.setBanco(bancoService.findById(dto.getBanco()));
        oldObj.setUsuario(usuarioService.findById(dto.getUsuarioId()));
        return contaRepo.save(oldObj);
    }

    public void delete(Long id) {
        Conta obj = findById(id);
        try {
            contaRepo.deleteById(id);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir a conta porque ela está relacionada a outros registros.");
        }
    }
}

package com.gestaofinanceirapessoal.services;

import com.gestaofinanceirapessoal.domains.CentroCusto;
import com.gestaofinanceirapessoal.domains.dtos.CentroCustoDTO;
import com.gestaofinanceirapessoal.repositories.CentroCustoRepository;
import com.gestaofinanceirapessoal.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentroCustoService {

    @Autowired
    private CentroCustoRepository centroCustoRepo;

    @Autowired
    private UsuarioService usuarioService;

    public List<CentroCustoDTO> findAll() {
        return centroCustoRepo.findAll().stream()
                .map(CentroCustoDTO::new)
                .collect(Collectors.toList());
    }

    public List<CentroCustoDTO> findByUsuario(Long usuarioId) {
        return centroCustoRepo.findByUsuario(usuarioService.findById(usuarioId)).stream()
                .map(CentroCustoDTO::new)
                .collect(Collectors.toList());
    }

    public CentroCusto findById(Long id) {
        return centroCustoRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Centro de Custo não encontrado! Id: " + id));
    }

    public CentroCusto create(CentroCustoDTO dto) {
        dto.setId(null);
        CentroCusto obj = new CentroCusto(dto);
        obj.setUsuario(usuarioService.findById(dto.getUsuarioId()));
        return centroCustoRepo.save(obj);
    }

    public CentroCusto update(Long id, CentroCustoDTO dto) {
        dto.setId(id);
        CentroCusto oldObj = findById(id);
        oldObj = new CentroCusto(dto);
        oldObj.setUsuario(usuarioService.findById(dto.getUsuarioId()));
        return centroCustoRepo.save(oldObj);
    }

    public void delete(Long id) {
        CentroCusto obj = findById(id);
        try {
            centroCustoRepo.deleteById(id);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir o centro de custo porque ele está relacionado a outros registros.");
        }
    }
}

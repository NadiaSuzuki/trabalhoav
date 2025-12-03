    package com.gestaofinanceirapessoal.services;

import com.gestaofinanceirapessoal.domains.Usuario;
import com.gestaofinanceirapessoal.domains.dtos.UsuarioCreateDTO;
import com.gestaofinanceirapessoal.domains.dtos.UsuarioDTO;
import com.gestaofinanceirapessoal.repositories.UsuarioRepository;
import com.gestaofinanceirapessoal.services.exceptions.DataIntegrityViolationException;
import com.gestaofinanceirapessoal.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder encoder;

    public List<UsuarioDTO> findAll() {
        return usuarioRepo.findAll().stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    public Usuario findById(Long id) {
        return usuarioRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id: " + id));
    }

    public Usuario findByEmail(String email) {
        return usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Email: " + email));
    }

    public Usuario create(UsuarioCreateDTO dto) {
        dto.setId(null);
        validaEmail(dto);
        
        Usuario newObj = new Usuario(dto);
        newObj.setSenha(encoder.encode(dto.getSenha()));
        
        return usuarioRepo.save(newObj);
    }

    public Usuario update(Long id, UsuarioCreateDTO dto) {
        dto.setId(id);
        Usuario oldObj = findById(id);
        
        if (!dto.getEmail().equals(oldObj.getEmail())) {
            validaEmail(dto);
        }
        
        oldObj = new Usuario(dto);
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            oldObj.setSenha(encoder.encode(dto.getSenha()));
        } else {
            oldObj.setSenha(findById(id).getSenha());
        }
        
        return usuarioRepo.save(oldObj);
    }

    public void delete(Long id) {
        Usuario obj = findById(id);
        if (!obj.getContas().isEmpty()) {
            throw new DataIntegrityViolationException("Usuário possui contas vinculadas e não pode ser deletado!");
        }
        if (!obj.getCentrosCusto().isEmpty()) {
            throw new DataIntegrityViolationException("Usuário possui centros de custo vinculados e não pode ser deletado!");
        }
        
        usuarioRepo.deleteById(id);
    }

    private void validaEmail(UsuarioCreateDTO dto) {
        Optional<Usuario> obj = usuarioRepo.findByEmail(dto.getEmail());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
}

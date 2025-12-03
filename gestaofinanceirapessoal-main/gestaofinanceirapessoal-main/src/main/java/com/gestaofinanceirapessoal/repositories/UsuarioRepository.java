package com.gestaofinanceirapessoal.repositories;

import com.gestaofinanceirapessoal.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailIgnoreCase(String email);
    Optional<Usuario> findByNomeIgnoreCase(String nome);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNome(String nome);
    boolean existsByEmail(String email);
}

package com.gestaofinanceirapessoal.repositories;

import com.gestaofinanceirapessoal.domains.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}

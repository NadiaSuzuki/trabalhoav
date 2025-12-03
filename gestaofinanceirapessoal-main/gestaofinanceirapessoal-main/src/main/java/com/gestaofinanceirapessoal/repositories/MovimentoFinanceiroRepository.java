package com.gestaofinanceirapessoal.repositories;

import com.gestaofinanceirapessoal.domains.MovimentoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimentoFinanceiroRepository extends JpaRepository<MovimentoFinanceiro, Long> {
    List<MovimentoFinanceiro> findByContaId(Long contaId);
    List<MovimentoFinanceiro> findByCentroCustoId(Long centroCustoId);
    List<MovimentoFinanceiro> findByDataLancamentoBetween(Date inicio, Date fim);
}

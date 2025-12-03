package com.gestaofinanceirapessoal.services;

import com.gestaofinanceirapessoal.domains.MovimentoFinanceiro;
import com.gestaofinanceirapessoal.domains.dtos.MovimentoFinanceiroDTO;
import com.gestaofinanceirapessoal.repositories.MovimentoFinanceiroRepository;
import com.gestaofinanceirapessoal.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentoFinanceiroService {

    @Autowired
    private MovimentoFinanceiroRepository movimentoRepo;

    @Autowired
    private ContaService contaService;

    @Autowired
    private CentroCustoService centroCustoService;

    public List<MovimentoFinanceiroDTO> findAll() {
        return movimentoRepo.findAll().stream()
                .map(MovimentoFinanceiroDTO::new)
                .collect(Collectors.toList());
    }

    public MovimentoFinanceiro findById(Long id) {
        return movimentoRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Movimento não encontrado! Id: " + id));
    }

    public MovimentoFinanceiro create(MovimentoFinanceiroDTO dto) {
        dto.setId(null);
        MovimentoFinanceiro obj = new MovimentoFinanceiro(dto);
        obj.setConta(contaService.findById(dto.getConta().getId()));
        obj.setCentroCusto(centroCustoService.findById(dto.getCentroCusto().getId()));
        
        // Atualiza o saldo da conta
        updateContaSaldo(obj);
        
        return movimentoRepo.save(obj);
    }

    public MovimentoFinanceiro update(Long id, MovimentoFinanceiroDTO dto) {
        dto.setId(id);
        MovimentoFinanceiro oldObj = findById(id);
        oldObj = new MovimentoFinanceiro(dto);
        oldObj.setConta(contaService.findById(dto.getConta().getId()));
        oldObj.setCentroCusto(centroCustoService.findById(dto.getCentroCusto().getId()));
        return movimentoRepo.save(oldObj);
    }

    public void delete(Long id) {
        MovimentoFinanceiro obj = findById(id);
        try {
            obj.getConta().setSaldo(obj.getConta().getSaldo() - obj.getValor());
            movimentoRepo.deleteById(id);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir o movimento financeiro porque ele está relacionado a outros registros.");
        }
    }

    private void updateContaSaldo(MovimentoFinanceiro movimento) {
        double saldoAtual = movimento.getConta().getSaldo();
        movimento.getConta().setSaldo(saldoAtual + movimento.getValor());
    }

    public List<MovimentoFinanceiroDTO> findByPeriodo(Date inicio, Date fim) {
        return movimentoRepo.findByDataLancamentoBetween(inicio, fim).stream()
                .map(MovimentoFinanceiroDTO::new)
                .collect(Collectors.toList());
    }
}

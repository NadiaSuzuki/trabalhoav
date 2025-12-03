package com.gestaofinanceirapessoal.services;

import com.gestaofinanceirapessoal.domains.*;
import com.gestaofinanceirapessoal.domains.enums.*;
import com.gestaofinanceirapessoal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DBService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CentroCustoRepository centroCustoRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private MovimentoFinanceiroRepository movimentoFinanceiroRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void initDB() {

        // ==========================
        // Usuários
        // ==========================
        Usuario user1 = new Usuario(null, "Kaio Monteiro",
                "kaiomonteiro@email.com", encoder.encode("123456"));
        usuarioRepository.save(user1);

        // ==========================
        // Bancos
        // ==========================
        Banco banco1 = new Banco(null, "12345678000199", "Banco do Brasil");
        Banco banco2 = new Banco(null, "98765432000188", "Caixa Econômica");

        bancoRepository.save(banco1);
        bancoRepository.save(banco2);

        // ==========================
        // Contas
        // ==========================
        Conta conta1 = new Conta(null, "Conta Corrente Kaio",
                TipoConta.CORRENTE, 2000.0, 1500.0,
                "1234", "56789", banco1, user1);

        contaRepository.save(conta1);

        // ==========================
        // Centro de Custo
        // ==========================
        CentroCusto cc1 = new CentroCusto(null, "Alimentação", 2000, user1);
        CentroCusto cc2 = new CentroCusto(null, "Transporte", 1000, user1);

        centroCustoRepository.save(cc1);
        centroCustoRepository.save(cc2);

        // ==========================
        // Movimento Financeiro
        // ==========================
        MovimentoFinanceiro mov1 = new MovimentoFinanceiro(
                null,
                "Pagamento Conta de Luz",
                LocalDate.now(),
                250.0,
                TipoMovimento.DESPESA,
                Categoria.MORADIA,
                conta1,
                cc1
        );

        movimentoFinanceiroRepository.save(mov1);

        // ==========================
        // Transações
        // ==========================
        Transacao t1 = new Transacao(null, "Compra supermercado", 1.0,
                LocalDate.now(), LocalDate.now(), LocalDate.now(),
                350.0, TipoTransacao.DEBITO, Status.PREVISAO, conta1, cc1);

        Transacao t2 = new Transacao(null, "Salário", 1.0,
                LocalDate.now(), LocalDate.now(), LocalDate.now(),
                3000.0, TipoTransacao.CREDITO, Status.PREVISAO, conta1, cc2);

        transacaoRepository.save(t1);
        transacaoRepository.save(t2);
    }
}
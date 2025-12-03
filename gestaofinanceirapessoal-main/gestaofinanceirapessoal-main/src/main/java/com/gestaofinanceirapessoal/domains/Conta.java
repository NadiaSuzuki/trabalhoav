package com.gestaofinanceirapessoal.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gestaofinanceirapessoal.domains.MovimentoFinanceiro;
import com.gestaofinanceirapessoal.domains.Banco;
import com.gestaofinanceirapessoal.domains.Usuario;
import com.gestaofinanceirapessoal.domains.dtos.ContaDTO;
import com.gestaofinanceirapessoal.domains.enums.TipoConta;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta")
    @SequenceGenerator(name = "seq_conta", sequenceName = "seq_conta", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "tipo_conta", nullable = false)
    private Integer tipoConta;

    @Column(nullable = false)
    private Double limite;

    @Column(nullable = false)
    private Double saldo;

    @Column(nullable = false)
    private String agencia;

    @Column(nullable = false)
    private String numero;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "conta")
    private List<MovimentoFinanceiro> movimentos = new ArrayList<>();

    public Conta() {}

    public Conta(Long id, String descricao, TipoConta tipoConta, Double limite, Double saldo, 
                String agencia, String numero, Banco banco, Usuario usuario) {
        this.id = id;
        this.descricao = descricao;
        this.tipoConta = tipoConta.getCodigo();
        this.limite = limite;
        this.saldo = saldo;
        this.agencia = agencia;
        this.numero = numero;
        this.banco = banco;
        this.usuario = usuario;
    }

    public Conta(ContaDTO dto) {
        this.id = dto.getId();
        this.descricao = dto.getDescricao();
        this.tipoConta = dto.getTipoConta().getCodigo();
        this.limite = dto.getLimite();
        this.saldo = dto.getSaldo();
        this.agencia = dto.getAgencia();
        this.numero = dto.getNumero();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoConta getTipoConta() {
        return TipoConta.toEnum(tipoConta);
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta.getCodigo();
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<MovimentoFinanceiro> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(List<MovimentoFinanceiro> movimentos) {
        this.movimentos = movimentos;
    }
}
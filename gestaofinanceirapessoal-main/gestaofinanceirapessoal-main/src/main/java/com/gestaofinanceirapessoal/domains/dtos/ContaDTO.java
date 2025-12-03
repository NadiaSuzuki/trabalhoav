package com.gestaofinanceirapessoal.domains.dtos;

import com.gestaofinanceirapessoal.domains.Conta;
import com.gestaofinanceirapessoal.domains.enums.TipoConta;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ContaDTO {
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 3, max = 100, message = "Descrição deve ter entre 3 e 100 caracteres")
    private String descricao;

    @NotNull(message = "Tipo de conta é obrigatório")
    private TipoConta tipoConta;

    @NotNull(message = "Limite é obrigatório")
    private Double limite;

    @NotBlank(message = "Número da conta é obrigatório")
    @Pattern(regexp = "^[0-9-]*$", message = "Número da conta deve conter apenas números e hífen")
    private String numero;

    @NotBlank(message = "Agência é obrigatória")
    @Pattern(regexp = "^[0-9-]*$", message = "Agência deve conter apenas números e hífen")
    private String agencia;

    @NotNull(message = "Saldo é obrigatório")
    private Double saldo;

    @NotNull(message = "Banco é obrigatório")
    @Valid
    private Long banco;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    public ContaDTO() {}

    public ContaDTO(Long id, String descricao, TipoConta tipoConta, Double limite, String numero,
                    String agencia, Double saldo, Long banco, Long usuarioId) {
        this.id = id;
        this.descricao = descricao;
        this.tipoConta = tipoConta;
        this.limite = limite;
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = saldo;
        this.banco = banco;
        this.usuarioId = usuarioId;
    }

    public ContaDTO(Conta conta) {
        this.id = conta.getId();
        this.descricao = conta.getDescricao();
        this.tipoConta = conta.getTipoConta();
        this.limite = conta.getLimite();
        this.numero = conta.getNumero();
        this.agencia = conta.getAgencia();
        this.saldo = conta.getSaldo();
        this.banco = conta.getBanco().getId();
        this.usuarioId = conta.getUsuario().getId();
    }

    public ContaDTO(Long aLong){
        
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Long getBanco() {
        return banco;
    }

    public void setBanco(Long banco) {
        this.banco = banco;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
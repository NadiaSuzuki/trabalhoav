package com.projeto.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaBancariaDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class, message = "Id não aparece na criação")
    @NotNull(groups = Update.class, message = "Id necessario na atualização")
    private Long id;

    @NotBlank(message = "A Instituição é necessária")
    @Size(max = 80, message = "Instituição pode ter até 80 caracteres")
    private String instituicao;

    @NotNull(message = "Agência é obrigatória")
    private Integer agencia;

    @NotNull(message = "Número é um campo obrigatório")
    private Integer numero;

    @NotBlank(message = "Apelido é obrigatório")
    @Size(max = 80, message = "Apelido pode ter até 80 caracteres")
    private String apelido;

    @Digits(integer = 12, fraction = 3, message = "Seu saldo inicial pode ter no até 12 inteiros e 3 decimais")
    @PositiveOrZero(message = "Saldo inicial não deve ser menor que 0")
    private BigDecimal saldoInicial;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataSaldoInicial = LocalDate.now();

    @Min(value = 1, message = "Status do cartão é inválido: use 1 (Ativa) ou 0 (Desativada)")
    @Max(value = 0, message = "Status do cartão é inválido: use 1 (Ativa) ou 0 (Desativada)")
    private int status;

    @NotNull(message = "Usuario obrigatório")
    private Long usuarioId;

    public ContaBancariaDTO() {
    }

    public ContaBancariaDTO(Long id, String instituicao, Integer agencia,
                            Integer numero, String apelido, BigDecimal saldoInicial,
                            LocalDate dataSaldoInicial, int status, Long usuarioId) {
        this.id = id;
        this.instituicao = instituicao;
        this.agencia = agencia;
        this.numero = numero;
        this.apelido = apelido;
        this.saldoInicial = saldoInicial;
        this.dataSaldoInicial = dataSaldoInicial;
        this.status = status;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }
    public Integer getAgencia() {
        return agencia;
    }
    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getApelido() {
        return apelido;
    }
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }
    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }
    public LocalDate getDataSaldoInicial() {
        return dataSaldoInicial;
    }
    public void setDataSaldoInicial(LocalDate dataSaldoInicial) {
        this.dataSaldoInicial = dataSaldoInicial;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}

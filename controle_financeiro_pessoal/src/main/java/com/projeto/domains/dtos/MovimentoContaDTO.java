package com.projeto.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class MovimentoContaDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class, message = "Id não aparece na criação")
    @NotNull(groups = Update.class, message = "Id necessário para atualizar")
    private Long id;

    @NotBlank(message = "Data de movimento necessária")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataMovimento = LocalDate.now();

    @Digits(integer = 12, fraction = 3, message = "Valor total pode ter até 12 inteiros e 3 decimais")
    @PositiveOrZero(message = "Valor total não deve ser menor que zero")
    private BigDecimal valor;

    @NotBlank(message = "Histórico é obrigatório")
    @Size(max = 80, message = "Histórico pode ter até 80 caracteres")
    private String historico;

    @Min(value = 0, message = "Movimento inválido, utilize 0 para (Credito), 1 (Débito]) OU 2 (Transferência)")
    @Max(value = 1, message = "Movimento inválido, utilize 0 para (Credito), 1 (Débito]) OU 2 (Transferência)")
    @Max(value = 2, message = "Movimento inválido, utilize 0 para (Credito), 1 (Débito]) OU 2 (Transferência)")
    private int tipoTransacao;

    @NotNull(message = "Conta bancária necessária")
    private Long contaBancariaId;

    public MovimentoContaDTO() {
    }

    public MovimentoContaDTO(Long id, LocalDate dataMovimento,
                             BigDecimal valor, String historico, int tipoTransacao,
                             Long contaBancariaId) {
        this.id = id;
        this.dataMovimento = dataMovimento;
        this.valor = valor;
        this.historico = historico;
        this.tipoTransacao = tipoTransacao;
        this.contaBancariaId = contaBancariaId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDataMovimento() {
        return dataMovimento;
    }
    public void setDataMovimento(LocalDate dataMovimento) {
        this.dataMovimento = dataMovimento;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public String getHistorico() {
        return historico;
    }
    public void setHistorico(String historico) {
        this.historico = historico;
    }
    public int getTipoTransacao() {
        return tipoTransacao;
    }
    public void setTipoTransacao(int tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
    public Long getContaBancariaId() {
        return contaBancariaId;
    }
    public void setContaBancariaId(Long contaBancariaId) {
        this.contaBancariaId = contaBancariaId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MovimentoContaDTO that = (MovimentoContaDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

package com.projeto.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FaturaCartaoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = CartaoCreditoDTO.Create.class, message = "Id não aparece na criação")
    @NotNull(groups = CartaoCreditoDTO.Update.class, message = "Id necessário para atualizar")
    private Long id;

    @NotBlank(message = "Competência obrigatória")
    @JsonFormat(pattern = "dd")
    @Column(nullable = false)
    private LocalDate competencia = LocalDate.now();

    @NotBlank(message = "Data de fechamento de fatura obrigatório")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataFechamentoFatura = LocalDate.now();

    @NotBlank(message = "Data de vencimento da Fatura obrigatório")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataVencimentoFatura = LocalDate.now();

    @Digits(integer = 12, fraction = 3, message = "O valor total deve ter até 12 inteiros e 3 decimais")
    @PositiveOrZero(message = "valor total não deve ser menor que 0")
    private BigDecimal valorTotal;

    @Min(value = 0, message = "Status da fatura invalido: use 0 (Aberto), 1 (Fechado) ou 2 (Pago)")
    @Max(value = 2, message = "Status da fatura invalido: use 0 (Aberto), 1 (FECHADO) ou 2 (Pago)")
    private int statusFatura;

    @NotNull(message = "Necessário cartão de crédito")
    private Integer cartaoCreditoId;

    public FaturaCartaoDTO() {
    }

    public FaturaCartaoDTO(Long id, LocalDate competencia, LocalDate dataFechamentoFatura,
                           LocalDate dataVencimentoFatura,BigDecimal valorTotal, int statusFatura,
                           Integer cartaoCreditoId) {
        this.id = id;
        this.competencia = competencia;
        this.dataFechamentoFatura = dataFechamentoFatura;
        this.dataVencimentoFatura = dataVencimentoFatura;
        this.valorTotal = valorTotal;
        this.statusFatura = statusFatura;
        this.cartaoCreditoId = cartaoCreditoId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getCompetencia() {
        return competencia;
    }
    public void setCompetencia(LocalDate competencia) {
        this.competencia = competencia;
    }
    public LocalDate getDataFechamentoFatura() {
        return dataFechamentoFatura;
    }
    public void setDataFechamentoFatura(LocalDate dataFechamentoFatura) {
        this.dataFechamentoFatura = dataFechamentoFatura;
    }

    public LocalDate getDataVencimentoFatura() {
        return dataVencimentoFatura;
    }

    public void setDataVencimentoFatura(LocalDate dataVencimentoFatura) {
        this.dataVencimentoFatura = dataVencimentoFatura;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    public int getStatusFatura() {
        return statusFatura;
    }
    public void setStatusFatura(int statusFatura) {
        this.statusFatura = statusFatura;
    }
    public Integer getCartaoCreditoId() {
        return cartaoCreditoId;
    }
    public void setCartaoCreditoId(Integer cartaoCreditoId) {
        this.cartaoCreditoId = cartaoCreditoId;
    }
}

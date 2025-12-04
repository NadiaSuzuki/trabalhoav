package com.projeto.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


public class LancamentoDTO {
    public interface Create {}
    public interface Update {}
    @Null(groups = Create.class, message = "Id não aparece na criação")
    @NotNull(groups = Update.class, message = "Id necessário para atualizar")
    private Long id;

    @NotBlank(message = "Descrição necessária")
    @Size(max = 80, message = "Descrição pode ter até 80 caracteres")
    private String descricao;

    @Digits(integer = 12, fraction = 3, message = "Valor total pode ter até 12 inteiros e 3 decimais")
    @PositiveOrZero(message = "Valor não pode ser menor que zero")
    private BigDecimal valor;

    @NotBlank(message = "Data da competencia necessária")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataCompetencia = LocalDate.now();

    @NotBlank(message = "Data de Vencimento necessária")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate dataVencimento = LocalDate.now();

    @Digits(integer = 12, fraction = 3, message = "O valor baixado pode ter até 12 inteiros e 3 decimais")
    @PositiveOrZero(message = "Valor baixado não pode ser menor que zero")
    private BigDecimal valorBaixado;

    @Min(value = 0, message = "Status: Lançamento inválido, use 0 (Pendente), 1 (Baixado), 2 (Parcial) ou 3 (Cancelado)")
    @Max(value = 1, message = "Status: Lançamento inválido, use 0 (Pendente), 1 (Baixado), 2 (Parcial) ou 3 (Cancelado)")
    @Max(value = 2, message = "Status: Lançamento inválido, use 0 (Pendente), 1 (Baixado), 2 (Parcial) ou 3 (Cancelado)")
    @Max(value = 3, message = "Status: Lançamento inválido, use 0 (Pendente), 1 (Baixado), 2 (Parcial) ou 3 (Cancelado)")
    private int StatusLancamento;

    @Min(value = 0, message = "Metodo de pagamento inválido, use 0 (Conta), 1 (Cartao), 2 (Dinheiro) ou 3 (Pix)")
    @Max(value = 1, message = "Metodo de pagamento inválido, use 0 (Conta), 1 (Cartao), 2 (Dinheiro) ou 3 (Pix)")
    @Max(value = 2, message = "Metodo de pagamento inválido, use 0 (Conta), 1 (Cartao), 2 (Dinheiro) ou 3 (Pix)")
    @Max(value = 3, message = "Metodo de pagamento inválido, use 0 (Conta), 1 (Cartao), 2 (Dinheiro) ou 3 (Pix)")
    private int MeioPagamento;

    @Min(value = 1, message = "Tipo de lançamento inválido, use 1 para (Pagar) ou  para 0 (Receber)")
    @Max(value = 0, message = "Tipo de lançamento inválido, use 1 para (Pagar) ou  para 0 (Receber)")
    private int TipoLancamento;

    @NotNull(message = "Usuario necessário")
    private Integer usuarioId;
    @NotNull(message = "Conta bancária necessária")
    private Integer contaBancariaId;
    @NotNull(message = "Centro de necessário")
    private Integer centroCustoId;
    @NotNull(message = "Entidade necessária")
    private Integer entidadeId;
    @NotNull(message = "Cartão de crédito necessário")
    private Integer cartaoCreditoId;

    public LancamentoDTO() {
    }

    public LancamentoDTO(Long id, String descricao, BigDecimal valor,
                         LocalDate dataCompetencia, LocalDate dataVencimento, BigDecimal valorBaixado, int statusLancamento, int meioPagamento, int tipoLancamento, Integer usuarioId, Integer contaBancariaId, Integer centroCustoId, Integer entidadeId, Integer cartaoCreditoId) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataCompetencia = dataCompetencia;
        this.dataVencimento = dataVencimento;
        this.valorBaixado = valorBaixado;
        StatusLancamento = statusLancamento;
        MeioPagamento = meioPagamento;
        TipoLancamento = tipoLancamento;
        this.usuarioId = usuarioId;
        this.contaBancariaId = contaBancariaId;
        this.centroCustoId = centroCustoId;
        this.entidadeId = entidadeId;
        this.cartaoCreditoId = cartaoCreditoId;
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
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public LocalDate getDataCompetencia() {
        return dataCompetencia;
    }
    public void setDataCompetencia(LocalDate dataCompetencia) {
        this.dataCompetencia = dataCompetencia;
    }
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public BigDecimal getValorBaixado() {
        return valorBaixado;
    }
    public void setValorBaixado(BigDecimal valorBaixado) {
        this.valorBaixado = valorBaixado;
    }
    public int getStatusLancamento() {
        return StatusLancamento;
    }
    public void setStatusLancamento(int statusLancamento) {
        StatusLancamento = statusLancamento;
    }
    public int getMeioPagamento() {
        return MeioPagamento;
    }
    public void setMeioPagamento(int meioPagamento) {
        MeioPagamento = meioPagamento;
    }
    public int getTipoLancamento() {
        return TipoLancamento;
    }
    public void setTipoLancamento(int tipoLancamento) {
        TipoLancamento = tipoLancamento;
    }
    public Integer getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    public Integer getContaBancariaId() {
        return contaBancariaId;
    }
    public void setContaBancariaId(Integer contaBancariaId) {
        this.contaBancariaId = contaBancariaId;
    }
    public Integer getCentroCustoId() {
        return centroCustoId;
    }
    public void setCentroCustoId(Integer centroCustoId) {
        this.centroCustoId = centroCustoId;
    }
    public Integer getEntidadeId() {
        return entidadeId;
    }
    public void setEntidadeId(Integer entidadeId) {
        this.entidadeId = entidadeId;
    }
    public Integer getCartaoCreditoId() {
        return cartaoCreditoId;
    }
    public void setCartaoCreditoId(Integer cartaoCreditoId) {
        this.cartaoCreditoId = cartaoCreditoId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LancamentoDTO that = (LancamentoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

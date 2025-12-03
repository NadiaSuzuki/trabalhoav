package com.gestaofinanceirapessoal.domains.dtos;

import com.gestaofinanceirapessoal.domains.Transacao;
import com.gestaofinanceirapessoal.domains.enums.Status;
import com.gestaofinanceirapessoal.domains.enums.TipoTransacao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class TransacaoDTO {
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 3, max = 100, message = "Descrição deve ter entre 3 e 100 caracteres")
    private String descricao;

    @Positive(message = "Parcela deve ser maior que zero")
    private Double parcela;

    private LocalDate dataTransacao;

    @NotNull(message = "Data de vencimento é obrigatória")
    private LocalDate dataVencimento;

    private LocalDate dataBaixa;

    @NotNull(message = "Valor do documento é obrigatório")
    @Positive(message = "Valor do documento deve ser maior que zero")
    private Double valorDocumento;

    @NotNull(message = "Tipo de transação é obrigatório")
    private TipoTransacao tipoTransacao;

    @NotNull(message = "Status é obrigatório")
    private Status status;

    @NotNull(message = "Conta é obrigatória")
    @Valid
    private ContaDTO conta;

    @Valid
    private CentroCustoDTO centroCusto;

    public TransacaoDTO() {}

    public TransacaoDTO(Long id, String descricao, Double parcela, LocalDate dataTransacao,
                       LocalDate dataVencimento, LocalDate dataBaixa, Double valorDocumento,
                       TipoTransacao tipoTransacao, Status status, ContaDTO conta,
                       CentroCustoDTO centroCusto) {
        this.id = id;
        this.descricao = descricao;
        this.parcela = parcela;
        this.dataTransacao = dataTransacao;
        this.dataVencimento = dataVencimento;
        this.dataBaixa = dataBaixa;
        this.valorDocumento = valorDocumento;
        this.tipoTransacao = tipoTransacao;
        this.status = status;
        this.conta = conta;
        this.centroCusto = centroCusto;
    }

    public TransacaoDTO(Transacao transacao) {
        this.id = transacao.getId();
        this.descricao = transacao.getDescricao();
        this.parcela = transacao.getParcela();
        this.dataTransacao = transacao.getDataTransacao();
        this.dataVencimento = transacao.getDataVencimento();
        this.dataBaixa = transacao.getDataBaixa();
        this.valorDocumento = transacao.getValorDocumento();
        this.tipoTransacao = transacao.getTipoTransacao();
        this.status = transacao.getStatus();
        this.conta = new ContaDTO(transacao.getConta());
        this.centroCusto = transacao.getCentroCusto() != null ? 
            new CentroCustoDTO(transacao.getCentroCusto()) : null;
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

    public Double getParcela() {
        return parcela;
    }

    public void setParcela(Double parcela) {
        this.parcela = parcela;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(LocalDate dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public Double getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(Double valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ContaDTO getConta() {
        return conta;
    }

    public void setConta(ContaDTO conta) {
        this.conta = conta;
    }

    public CentroCustoDTO getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCustoDTO centroCusto) {
        this.centroCusto = centroCusto;
    }
}

package com.gestaofinanceirapessoal.domains.dtos;

import com.gestaofinanceirapessoal.domains.MovimentoFinanceiro;
import com.gestaofinanceirapessoal.domains.enums.Categoria;
import com.gestaofinanceirapessoal.domains.enums.TipoMovimento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class MovimentoFinanceiroDTO {
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 3, max = 100, message = "Descrição deve ter entre 3 e 100 caracteres")
    private String descricao;

    @NotNull(message = "Valor é obrigatório")
    private Double valor;

    private LocalDate dataLancamento;

    @NotNull(message = "Tipo de movimento é obrigatório")
    private Integer tipoMovimento;

    @NotNull(message = "Categoria é obrigatória")
    private Integer categoria;

    @NotNull(message = "Conta é obrigatória")
    @Valid
    private ContaDTO conta;

    @NotNull(message = "Centro de Custo é obrigatório")
    @Valid
    private CentroCustoDTO centroCusto;

    public MovimentoFinanceiroDTO() {}

    public MovimentoFinanceiroDTO(Long id, String descricao, Double valor, LocalDate dataLancamento,
                                  TipoMovimento tipoMovimento, Categoria categoria,
                                  ContaDTO conta, CentroCustoDTO centroCusto) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataLancamento = dataLancamento;
        this.tipoMovimento = tipoMovimento.getCodigo();
        this.categoria = categoria.getCodigo();
        this.conta = conta;
        this.centroCusto = centroCusto;
    }

    public MovimentoFinanceiroDTO(MovimentoFinanceiro movimento) {
        this.id = movimento.getId();
        this.descricao = movimento.getDescricao();
        this.valor = movimento.getValor();
        this.dataLancamento = movimento.getDataLancamento();
        this.tipoMovimento = movimento.getTipoMovimento().getCodigo();
        this.categoria = movimento.getCategoria().getCodigo();
        this.conta = new ContaDTO(movimento.getConta());
        this.centroCusto = new CentroCustoDTO(movimento.getCentroCusto());
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public TipoMovimento getTipoMovimento() {
        return TipoMovimento.toEnum(tipoMovimento);
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento.getCodigo();
    }

    public Categoria getCategoria() {
        return Categoria.toEnum(categoria);
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria.getCodigo();
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

package com.gestaofinanceirapessoal.domains;

import com.gestaofinanceirapessoal.domains.dtos.MovimentoFinanceiroDTO;
import com.gestaofinanceirapessoal.domains.enums.Categoria;
import com.gestaofinanceirapessoal.domains.enums.TipoMovimento;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "movimentos_financeiros")
public class MovimentoFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movimento_financeiro")
    @SequenceGenerator(name = "seq_movimento_financeiro", sequenceName = "seq_movimento_financeiro", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(nullable = false)
    private Double valor;

    @Column(name = "tipo_movimento", nullable = false)
    private Integer tipoMovimento;

    @Column(nullable = false)
    private Integer categoria;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "centro_custo_id")
    private CentroCusto centroCusto;

    public MovimentoFinanceiro() {
    }

    public MovimentoFinanceiro(Long id, String descricao, LocalDate dataLancamento, Double valor,
                               TipoMovimento tipoMovimento, Categoria categoria, Conta conta, CentroCusto centroCusto) {
        this.id = id;
        this.descricao = descricao;
        this.dataLancamento = dataLancamento;
        this.valor = valor;
        this.tipoMovimento = (tipoMovimento == null) ? null : tipoMovimento.getCodigo();
        this.categoria = categoria.getCodigo();
        this.conta = conta;
        this.centroCusto = centroCusto;
    }

    public MovimentoFinanceiro(MovimentoFinanceiroDTO dto) {
        this.id = dto.getId();
        this.descricao = dto.getDescricao();
        this.dataLancamento = dto.getDataLancamento();
        this.valor = dto.getValor();
        this.tipoMovimento = dto.getTipoMovimento().getCodigo();
        this.categoria = dto.getCategoria().getCodigo();
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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }
}

package com.gestaofinanceirapessoal.domains;

import com.gestaofinanceirapessoal.domains.dtos.TransacaoDTO;
import com.gestaofinanceirapessoal.domains.enums.Status;
import com.gestaofinanceirapessoal.domains.enums.TipoTransacao;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transacoes")
public class Transacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transacao")
    @SequenceGenerator(name = "seq_transacao", sequenceName = "seq_transacao", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    private Double parcela;

    @Column(name = "data_transacao")
    private LocalDate dataTransacao;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "data_baixa")
    private LocalDate dataBaixa;

    @Column(name = "valor_documento", nullable = false)
    private Double valorDocumento;

    @Column(name = "tipo_transacao", nullable = false)
    private Integer tipoTransacao;

    @Column(nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "centro_custo_id")
    private CentroCusto centroCusto;

    public Transacao() {}

    public Transacao(Long id, String descricao, Double parcela, LocalDate dataTransacao, 
                    LocalDate dataVencimento, LocalDate dataBaixa, Double valorDocumento,
                    TipoTransacao tipoTransacao, Status status, Conta conta, CentroCusto centroCusto) {
        this.id = id;
        this.descricao = descricao;
        this.parcela = parcela;
        this.dataTransacao = dataTransacao;
        this.dataVencimento = dataVencimento;
        this.dataBaixa = dataBaixa;
        this.valorDocumento = valorDocumento;
        this.tipoTransacao = tipoTransacao.getCodigo();
        this.status = status.getCodigo();
        this.conta = conta;
        this.centroCusto = centroCusto;
    }

    public Transacao(TransacaoDTO dto) {
        this.id = dto.getId();
        this.descricao = dto.getDescricao();
        this.parcela = dto.getParcela();
        this.dataTransacao = dto.getDataTransacao();
        this.dataVencimento = dto.getDataVencimento();
        this.dataBaixa = dto.getDataBaixa();
        this.valorDocumento = dto.getValorDocumento();
        this.tipoTransacao = dto.getTipoTransacao().getCodigo();
        this.status = dto.getStatus().getCodigo();
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
        return TipoTransacao.toEnum(tipoTransacao);
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao.getCodigo();
    }

    public Status getStatus() {
        return Status.toEnum(status);
    }

    public void setStatus(Status status) {
        this.status = status.getCodigo();
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

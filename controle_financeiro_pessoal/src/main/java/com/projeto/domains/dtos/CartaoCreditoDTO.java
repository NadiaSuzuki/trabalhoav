package com.projeto.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class CartaoCreditoDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class, message = "Id não aparece na criação")
    @NotNull(groups = Update.class, message = "Id necessário para atualizar")
    private Long id;
    @NotBlank(message = "O campo Bandeira é obrigatório")
    @Size(max = 80, message = "Bandeira deve ter no maximo 80 caracteres")
    private String bandeira;
    @NotBlank(message = "O campo orgão emissor é obrigatório!")
    @Size(max = 80, message = "Emissor deve ter no maximo 80 caracteres")
    private String emissor;
    @NotBlank(message = "o campo apelido é obrigatório")
    @Size(max = 80, message = "Apelido deve ter no maximo 80 caracteres")
    private String apelido;
    @NotBlank(message = "Dia de fechamento da fatura é obrigatório")
    @JsonFormat(pattern = "dd")
    @Column(nullable = false)
    private LocalDate fechamentoFaturaDia = LocalDate.now();
    @NotBlank(message = "Dia de vencimento da fatura é obrigatório")
    @JsonFormat(pattern = "dd")
    @Column(nullable = false)
    private LocalDate vencimentoFaturaDia = LocalDate.now();
    @Min(value = 1, message = "Status do cartão invalido: utilize 1 (DESBLOQUEADO) ou 0 (BLOQUEADO)")
    @Max(value = 0, message = "Status do Cartão invalido: utilize 1 (DESBLOQUEADO) ou 0 (BLOQUEADO)")
    private int statusCartao;
    @NotNull(message = "Usuário obrigatório!")
    private Integer usuarioId;

    public CartaoCreditoDTO() {
    }

    public CartaoCreditoDTO(Long id, String bandeira, String emissor, String apelido,
                            LocalDate fechamentoFaturaDia, LocalDate vencimentoFaturaDia,
                            int statusCartao, Integer usuarioId) {
        this.id = id;
        this.bandeira = bandeira;
        this.emissor = emissor;
        this.apelido = apelido;
        this.fechamentoFaturaDia = fechamentoFaturaDia;
        this.vencimentoFaturaDia = vencimentoFaturaDia;
        this.statusCartao = statusCartao;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBandeira() {
        return bandeira;
    }
    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
    public String getEmissor() {
        return emissor;
    }
    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }
    public String getApelido() {
        return apelido;
    }
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
    public LocalDate getFechamentoFaturaDia() {
        return fechamentoFaturaDia;
    }
    public void setFechamentoFaturaDia(LocalDate fechamentoFaturaDia) {
        this.fechamentoFaturaDia = fechamentoFaturaDia;
    }

    public LocalDate getVencimentoFaturaDia() {
        return vencimentoFaturaDia;
    }

    public void setVencimentoFaturaDia(LocalDate vencimentoFaturaDia) {
        this.vencimentoFaturaDia = vencimentoFaturaDia;
    }

    public int getStatusCartao() {
        return statusCartao;
    }
    public void setStatusCartao(int statusCartao) {
        this.statusCartao = statusCartao;
    }
    public Integer getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}

package com.gestaofinanceirapessoal.domains.dtos;

import com.gestaofinanceirapessoal.domains.CentroCusto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CentroCustoDTO {
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 3, max = 100, message = "Descrição deve ter entre 3 e 100 caracteres")
    private String descricao;

    @NotNull(message = "Valor limite é obrigatório")
    @Min(value = 0, message = "Valor limite deve ser maior ou igual a zero")
    private Integer valorLimite;

    @NotNull(message = "ID do usuário é obrigatório")
    private Long usuarioId;

    public CentroCustoDTO() {}

    public CentroCustoDTO(Long id, String descricao, Integer valorLimite, Long usuarioId) {
        this.id = id;
        this.descricao = descricao;
        this.valorLimite = valorLimite;
        this.usuarioId = usuarioId;
    }

    public CentroCustoDTO(CentroCusto centroCusto) {
        this.id = centroCusto.getId();
        this.descricao = centroCusto.getDescricao();
        this.valorLimite = centroCusto.getValorLimite();
        this.usuarioId = centroCusto.getUsuario() != null ? centroCusto.getUsuario().getId() : null;
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

    public Integer getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(Integer valorLimite) {
        this.valorLimite = valorLimite;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}

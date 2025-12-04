package com.projeto.domains.dtos;

import jakarta.validation.constraints.*;

public class EntidadeDTO {

    public interface Create {}
    public interface Update {}

    @Null(groups = CartaoCreditoDTO.Create.class, message = "Id não aparece na criação")
    @NotNull(groups = CartaoCreditoDTO.Update.class, message = "Id necessário para atualizar")
    private Long id;

    @NotBlank(message = "Nome da entidade é obrigatória")
    @Size(max = 80, message = "Nome da entidade deve ter no maximo 80 caracteres")
    private String nome;

    @NotBlank(message = "Documento obrigatório")
    @Size(max = 80, message = "Documento deve ter no maximo 80 caracteres")
    private String documento;

    @NotNull(message = "Usuario obrigatório")
    private Integer usuarioId;

    public EntidadeDTO() {
    }

    public EntidadeDTO(Long id, String nome, String documento, Integer usuarioId) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public Integer getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}

package com.projeto.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UsuarioDTO {
    public interface Create {}
    public interface Update {}

    @Null(groups = Create.class, message = "Id não aparece na criação")
    @NotNull(groups = Update.class, message = "Id necessário para atualizar")
    private Long id;
    @NotBlank(message = "Nome necessário")
    @Size(max = 80, message = "Nome pode ter até 80 caracteres")
    private String nome;
    @NotBlank(message = "email necessário")
    @Size(max = 150, message = "email pode ter até 150 caracteres")
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate criadoEm = LocalDate.now();

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String email, LocalDate criadoEm) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.criadoEm = criadoEm;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getCriadoEm() {
        return criadoEm;
    }
    public void setCriadoEm(LocalDate criadoEm) {
        this.criadoEm = criadoEm;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", criadoEm=" + criadoEm +
                '}';
    }
}

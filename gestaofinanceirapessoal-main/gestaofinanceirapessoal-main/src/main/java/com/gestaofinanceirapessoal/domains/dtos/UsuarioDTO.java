package com.gestaofinanceirapessoal.domains.dtos;

import com.gestaofinanceirapessoal.domains.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "E-mail deve ser válido")
    private String email;
    
    private List<ContaDTO> contas = new ArrayList<>();
    private List<CentroCustoDTO> centrosCusto = new ArrayList<>();

    public UsuarioDTO() {}

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();

        this.contas = usuario.getContas().stream()
                .map(ContaDTO::new)
                .collect(Collectors.toList());

        this.centrosCusto = usuario.getCentrosCusto().stream()
                .map(CentroCustoDTO::new)
                .collect(Collectors.toList());
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

    public List<ContaDTO> getContas() {
        return contas;
    }

    public void setContas(List<ContaDTO> contas) {
        this.contas = contas;
    }

    public List<CentroCustoDTO> getCentrosCusto() {
        return centrosCusto;
    }

    public void setCentrosCusto(List<CentroCustoDTO> centrosCusto) {
        this.centrosCusto = centrosCusto;
    }
    
}

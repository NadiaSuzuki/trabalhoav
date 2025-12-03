package com.gestaofinanceirapessoal.domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestaofinanceirapessoal.domains.dtos.UsuarioCreateDTO;
import com.gestaofinanceirapessoal.domains.dtos.UsuarioDTO;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @JsonManagedReference
    @OneToMany(mappedBy = "usuario")
    private List<Conta> contas = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "usuario")
    private List<CentroCusto> centrosCusto = new ArrayList<>();

    public Usuario() {}

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(UsuarioDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.email = dto.getEmail();
    }

    public Usuario(UsuarioCreateDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.senha = dto.getSenha();
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public List<CentroCusto> getCentrosCusto() {
        return centrosCusto;
    }

    public void setCentrosCusto(List<CentroCusto> centrosCusto) {
        this.centrosCusto = centrosCusto;
    }
}

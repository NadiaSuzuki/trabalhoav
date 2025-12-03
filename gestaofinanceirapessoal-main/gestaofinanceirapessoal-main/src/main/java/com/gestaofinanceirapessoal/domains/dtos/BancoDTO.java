package com.gestaofinanceirapessoal.domains.dtos;

import com.gestaofinanceirapessoal.domains.Banco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.ArrayList;
import java.util.List;

public class BancoDTO {
    private Long id;

    @NotBlank(message = "CNPJ é obrigatório")
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    @NotBlank(message = "Razão Social é obrigatória")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,100}$", message = "Razão Social deve ter entre 3 e 100 caracteres alfanuméricos")
    private String razaoSocial;

    private List<Long> contas = new ArrayList<>();

    public BancoDTO() {}

    public BancoDTO(Long id, String cnpj, String razaoSocial) {
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public BancoDTO(Banco banco) {
        this.id = banco.getId();
        this.cnpj = banco.getCnpj();
        this.razaoSocial = banco.getRazaoSocial();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public List<Long> getContas() {
        return contas;
    }

    public void setContas(List<Long> contas) {
        this.contas = contas;
    }
}

package com.gestaofinanceirapessoal.domains.enums;

public enum Categoria {
    // Receitas
    SALARIO(1, "Salário"),
    INVESTIMENTOS(2, "Investimentos"),
    FREELANCER(3, "Freelancer"),
    OUTROS_RECEITAS(4, "Outros - Receitas"),
    
    // Despesas
    ALIMENTACAO(5, "Alimentação"),
    MORADIA(6, "Moradia"),
    TRANSPORTE(7, "Transporte"),
    SAUDE(8, "Saúde"),
    EDUCACAO(9, "Educação"),
    LAZER(10, "Lazer"),
    VESTUARIO(11, "Vestuário"),
    SERVICOS(12, "Serviços"),
    IMPOSTOS(13, "Impostos"),
    OUTROS_DESPESAS(14, "Outros - Despesas");

    private Integer codigo;
    private String descricao;

    Categoria(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Categoria toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (Categoria categoria : Categoria.values()) {
            if (codigo.equals(categoria.getCodigo())) {
                return categoria;
            }
        }

        throw new IllegalArgumentException("Categoria inválida: " + codigo);
    }
}

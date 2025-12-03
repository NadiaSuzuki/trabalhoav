package com.gestaofinanceirapessoal.domains.enums;

public enum TipoMovimento {
    RECEITA(1, "Receita"),
    DESPESA(2, "Despesa");

    private Integer codigo;
    private String descricao;

    TipoMovimento(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoMovimento toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (TipoMovimento tipo : TipoMovimento.values()) {
            if (codigo.equals(tipo.getCodigo())) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Tipo de movimento inválido: " + codigo);
    }
}

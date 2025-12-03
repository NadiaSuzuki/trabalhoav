package com.gestaofinanceirapessoal.domains.enums;

public enum TipoTransacao {
    DEBITO(1, "Débito"),
    CREDITO(2, "Crédito"),
    TRANSFERENCIA(3, "Transferência");

    private Integer codigo;
    private String descricao;

    TipoTransacao(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoTransacao toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (TipoTransacao tipo : TipoTransacao.values()) {
            if (codigo.equals(tipo.getCodigo())) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Tipo de transação inválido: " + codigo);
    }
}

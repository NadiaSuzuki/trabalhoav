package com.gestaofinanceirapessoal.domains.enums;

public enum TipoConta {
    CORRENTE(1, "Conta Corrente"),
    POUPANCA(2, "Conta Poupança"),
    INVESTIMENTO(3, "Conta Investimento"),
    CARTAO_CREDITO(4, "Cartão de Crédito");

    private Integer codigo;
    private String descricao;

    TipoConta(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoConta toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (TipoConta tipo : TipoConta.values()) {
            if (codigo.equals(tipo.getCodigo())) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Tipo de conta inválido: " + codigo);
    }
}

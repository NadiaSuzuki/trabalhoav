package com.gestaofinanceirapessoal.domains.enums;

public enum Status {
    PREVISAO(1, "Previsão"),
    CONFIRMADO(2, "Confirmado"),
    PAGO(3, "Pago"),
    CONCILIADO(4, "Conciliado");

    private Integer codigo;
    private String descricao;

    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (Status status : Status.values()) {
            if (codigo.equals(status.getCodigo())) {
                return status;
            }
        }

        throw new IllegalArgumentException("Status inválido: " + codigo);
    }
}

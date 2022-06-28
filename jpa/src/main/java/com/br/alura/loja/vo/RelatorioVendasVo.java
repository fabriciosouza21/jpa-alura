package com.br.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioVendasVo {
    private final String nomeProduto;
    private final Long  quantidade;
    private final LocalDate data;

public RelatorioVendasVo(String nomeProduto, Long quantidade, LocalDate data) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.data = data;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "RelatorioVendasVo{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }
}

package com.curso.cucumber.entidades;

import java.util.Date;

public class NotaAluguel {

    private Integer preco;
    private Date dataEntrega;
    private int pontuacao;

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }


    public Date getDataEntrega() {
        return dataEntrega;
    }
}

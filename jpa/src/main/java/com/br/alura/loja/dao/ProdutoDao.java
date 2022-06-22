package com.br.alura.loja.dao;

import com.br.alura.loja.model.Produto;

import javax.persistence.EntityManager;
public class ProdutoDao {
    private final EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastra(Produto produto){
        em.persist(produto);
    }
}

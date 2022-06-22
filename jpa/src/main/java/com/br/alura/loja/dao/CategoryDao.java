package com.br.alura.loja.dao;

import com.br.alura.loja.model.produto.Categoria;

import javax.persistence.EntityManager;

public class CategoryDao {
    private final EntityManager em;

    public CategoryDao(EntityManager em) {
        this.em = em;
    }

    public void cadastra(Categoria categoria){
        em.persist(categoria);
    }
}

package com.br.alura.loja.dao;

import com.br.alura.loja.model.produto.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDao {
    private final EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastra(Produto produto){
        em.persist(produto);
    }

    public Produto findById(Long id){
        return em.find(Produto.class,id);
    }

    public List<Produto> findAll(){
        String jpql = "select p from Produto p";
        return  em.createQuery(jpql,Produto.class).getResultList();
    }
    public List<Produto> findAllByNome(String name){
        String jpql = "select p from Produto p where p.name=:name";
        return  em.createQuery(jpql,Produto.class).
                setParameter("name",name).
                getResultList();
    }

    public List<Produto> findAllByNomeCategory(String name){
        String jpql = "select p from Produto p where p.category.name=:name";
        return  em.createQuery(jpql,Produto.class).
                setParameter("name",name).
                getResultList();
    }

}

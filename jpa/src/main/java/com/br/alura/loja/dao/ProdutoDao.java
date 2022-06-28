package com.br.alura.loja.dao;

import com.br.alura.loja.model.produto.Produto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    public BigDecimal findAllByMountByNome(String name){
        String jpql = "select p.amout from Produto p where p.name=:name";
        return  em.createQuery(jpql,BigDecimal.class).
                setParameter("name",name).
                getSingleResult();
    }

    public List<Produto> getProduto(String nome, String preco, LocalDate data){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtroAnd = criteriaBuilder.and();
        if(nome != null && !nome.isEmpty()){
            filtroAnd = criteriaBuilder.and(filtroAnd, criteriaBuilder.equal(from.get("name"), nome));
        }
        if(preco != null && !preco.isEmpty()){
            filtroAnd = criteriaBuilder.and(filtroAnd, criteriaBuilder.equal(from.get("amout"), new BigDecimal(preco)));
        }
        if(data != null){
            filtroAnd = criteriaBuilder.and(filtroAnd, criteriaBuilder.equal(from.get("dataCadastrato"), data));
        }
        query.where(filtroAnd);
        return em.createQuery(query).getResultList();
    }

}

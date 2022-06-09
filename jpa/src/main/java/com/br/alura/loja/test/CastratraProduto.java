package com.br.alura.loja.test;

import com.br.alura.loja.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CastratraProduto {
    public static void main(String[] args) {
        Produto produto = new Produto();
        produto.setName("phone");
        produto.setAmout(new BigDecimal("500"));
        produto.setDescription("xiomi");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}

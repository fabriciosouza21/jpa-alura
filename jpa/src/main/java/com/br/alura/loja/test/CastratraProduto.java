package com.br.alura.loja.test;

import com.br.alura.loja.dao.ProdutoDao;
import com.br.alura.loja.model.Produto;
import com.br.alura.loja.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CastratraProduto {
    public static void main(String[] args) {
        Produto produto = new Produto();
        produto.setName("phone");
        produto.setAmout(new BigDecimal("500"));
        produto.setDescription("xiomi");

        EntityManager entityManager = JpaUtil.getEntityManageProduto();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);

        entityManager.getTransaction().begin();
        produtoDao.cadastra(produto);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}

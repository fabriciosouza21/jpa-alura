package com.br.alura.loja.test;

import com.br.alura.loja.dao.CategoryDao;
import com.br.alura.loja.dao.ProdutoDao;
import com.br.alura.loja.model.produto.Categoria;
import com.br.alura.loja.model.produto.Produto;
import com.br.alura.loja.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CastratraProduto {
    public static void main(String[] args) {
        Categoria phoner = new Categoria("PHONER");
        Produto produto = new Produto("phone", new BigDecimal("500"),"xiomi",phoner );
        EntityManager entityManager = JpaUtil.getEntityManageProduto();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoryDao categoryDao = new CategoryDao(entityManager);
        entityManager.getTransaction().begin();
        categoryDao.cadastra(phoner);
        phoner.setName("Moto g 20");
        entityManager.flush();
        entityManager.clear();
        phoner = entityManager.merge(phoner);
        phoner.setName("xiomi");
        entityManager.flush();
        entityManager.remove(phoner);
        entityManager.getTransaction().commit();
    }
}

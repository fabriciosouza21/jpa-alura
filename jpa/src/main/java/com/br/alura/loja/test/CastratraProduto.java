package com.br.alura.loja.test;

import com.br.alura.loja.dao.CategoryDao;
import com.br.alura.loja.dao.ProdutoDao;
import com.br.alura.loja.model.produto.Categoria;
import com.br.alura.loja.model.produto.Produto;
import com.br.alura.loja.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CastratraProduto {
    public static void main(String[] args) {
        Categoria phoner = new Categoria("PHONER");
        Produto produto = new Produto("phone", new BigDecimal("500"),"xiomi",phoner );
        EntityManager entityManager = JpaUtil.getEntityManageProduto();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoryDao categoria = new CategoryDao(entityManager);
        entityManager.getTransaction().begin();
        categoria.cadastra(phoner);
        entityManager.flush();
        produtoDao.cadastra(produto);

        produto = produtoDao.findById(produto.getId());
        System.out.println(produto.getAmout());
        List<Produto> todos = produtoDao.findAll();
        todos.forEach(x -> System.out.println(x.getName()));

        entityManager.getTransaction().commit();
    }
}

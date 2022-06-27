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
        EntityManager entityManager = JpaUtil.getEntityManageProduto();
        Produto produto = cadastrarCategoria(entityManager);
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        produto = produtoDao.findById(produto.getId());
        System.out.println(produto.getAmout());
        List<Produto> todos = produtoDao.findAllByNomeCategory("PHONER");
        todos.forEach(x -> System.out.println(x.getName()));

    }

    private static Produto cadastrarCategoria(EntityManager entityManager) {
        Categoria phoner = new Categoria("PHONER");
        Produto produto = new Produto("phone", new BigDecimal("500"),"xiomi",phoner );
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoryDao categoria = new CategoryDao(entityManager);
        entityManager.getTransaction().begin();
        categoria.cadastra(phoner);
        entityManager.flush();
        produtoDao.cadastra(produto);

        entityManager.getTransaction().commit();
        return produto;
    }
}

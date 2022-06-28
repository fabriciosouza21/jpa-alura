package com.br.alura.loja.test;

import com.br.alura.loja.dao.CategoryDao;
import com.br.alura.loja.dao.ClienteDao;
import com.br.alura.loja.dao.PedidoDao;
import com.br.alura.loja.dao.ProdutoDao;
import com.br.alura.loja.model.cliente.Cliente;
import com.br.alura.loja.model.pedido.ItemPedido;
import com.br.alura.loja.model.pedido.Pedido;
import com.br.alura.loja.model.produto.Categoria;
import com.br.alura.loja.model.produto.Produto;
import com.br.alura.loja.util.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CastratraPedido {
    public static void main(String[] args) {

        popularBanco();
        EntityManager entityManager = JpaUtil.getEntityManageProduto();
        ClienteDao  clienteDao = new ClienteDao(entityManager);
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        PedidoDao pedidoDao = new PedidoDao(entityManager);
        Cliente cliente = clienteDao.findById(1L);
        Produto produto = produtoDao.findById(1L);
        Pedido pedido = new Pedido(LocalDate.now(),new BigDecimal("100.00"), cliente);
        pedido.adicionarItem(new ItemPedido(10,pedido,produto));
        entityManager.getTransaction().begin();
        pedidoDao.cadastra(pedido);
        entityManager.getTransaction().commit();

        System.out.println(pedidoDao.valorTotalPedido());

    }

    private static void popularBanco() {
        Categoria phoner = new Categoria("PHONER");
        Produto produto = new Produto("phone", new BigDecimal("500"),"xiomi",phoner );
        Cliente cliente = new Cliente("João", "1234568485");
        EntityManager entityManager = JpaUtil.getEntityManageProduto();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoryDao categoria = new CategoryDao(entityManager);
        ClienteDao  clienteDao = new ClienteDao(entityManager);
        entityManager.getTransaction().begin();
        categoria.cadastra(phoner);

        entityManager.flush();
        clienteDao.cadastra(cliente);
        produtoDao.cadastra(produto);
        entityManager.getTransaction().commit();
    }
}

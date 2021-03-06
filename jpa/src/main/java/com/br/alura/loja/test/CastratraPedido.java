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
import com.br.alura.loja.vo.RelatorioVendasVo;

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
        Produto produto1 = produtoDao.findById(1L);
        Produto produto2 = produtoDao.findById(2L);
        Produto produto3 = produtoDao.findById(3L);

        Pedido pedido = new Pedido(LocalDate.now(),new BigDecimal("100.00"), cliente);
        pedido.adicionarItem(new ItemPedido(10,pedido,produto1));
        pedido.adicionarItem(new ItemPedido(10,pedido,produto2));
        pedido.adicionarItem(new ItemPedido(10,pedido,produto3));


        entityManager.getTransaction().begin();
        pedidoDao.cadastra(pedido);
        entityManager.getTransaction().commit();

        System.out.println(pedidoDao.valorTotalPedido());

        List<RelatorioVendasVo> relatorio = pedidoDao.relatorioVendas();

        relatorio.forEach(System.out::println);

    }

    private static void popularBanco() {
        Categoria categoria1 = new Categoria("PHONER");
        Categoria categoria2 = new Categoria("INFORMATICA");
        Categoria categoria3 = new Categoria("ELETRONICA");
        Produto produto1 = new Produto("telefone", new BigDecimal("500"),"xiomi",categoria1 );
        Produto produto2 = new Produto("notbook cce", new BigDecimal("500"),"xiomi",categoria2 );
        Produto produto3 = new Produto("caixa de som", new BigDecimal("500"),"xiomi",categoria3 );
        Cliente cliente = new Cliente("Jo??o", "1234568485");
        EntityManager entityManager = JpaUtil.getEntityManageProduto();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoryDao categoria = new CategoryDao(entityManager);
        ClienteDao  clienteDao = new ClienteDao(entityManager);
        entityManager.getTransaction().begin();
        categoria.cadastra(categoria1);
        categoria.cadastra(categoria2);
        categoria.cadastra(categoria3);

        entityManager.flush();
        clienteDao.cadastra(cliente);
        produtoDao.cadastra(produto1);
        produtoDao.cadastra(produto2);
        produtoDao.cadastra(produto3);
        entityManager.getTransaction().commit();
    }
}

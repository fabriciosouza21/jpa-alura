package com.br.alura.loja.dao;

import com.br.alura.loja.model.pedido.Pedido;
import com.br.alura.loja.model.produto.Categoria;
import com.br.alura.loja.vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {
    private final EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastra(Pedido pedido) {
        em.persist(pedido);
    }

    public Pedido atualizar(Pedido pedido) {
        return em.merge(pedido);
    }

    public void remover(Pedido pedido) {
        pedido = em.merge(pedido);
        em.remove(pedido);
    }

    public BigDecimal valorTotalPedido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<RelatorioVendasVo> relatorioVendas() {
        String jpql = new StringBuilder().
                append("SELECT new com.br.alura.loja.vo.RelatorioVendasVo (produto.name, SUM(item.quantidade), MAX(pedido.data) ) ").
                append("FROM Pedido pedido ").
                append("JOIN pedido.items item ").
                append("JOIN item.produto produto ").
                append("GROUP BY produto.name ").
                append("ORDER BY SUM(item.quantidade) DESC").toString();
        return em.createQuery(jpql, RelatorioVendasVo.class).getResultList();
    }

    public Pedido findById(Long id) {
        return em.find(Pedido.class, id);
    }

    public Pedido buscaPorIdComCliente(Long id){
        String jpql = "SELECT pedido FROM Pedido pedido JOIN FETCH pedido.cliente WHERE pedido.id = :id";
        return em.createQuery(jpql, Pedido.class).setParameter("id", id).getSingleResult();
    }

}

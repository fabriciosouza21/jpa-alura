package com.br.alura.loja.dao;

import com.br.alura.loja.model.pedido.Pedido;
import com.br.alura.loja.model.produto.Categoria;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PedidoDao {
    private final EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastra(Pedido pedido){
        em.persist(pedido);
    }

    public Pedido atualizar (Pedido pedido){
        return em.merge(pedido);
    }

    public void remover(Pedido pedido){
        pedido = em.merge(pedido);
        em.remove(pedido);
    }

    public BigDecimal valorTotalPedido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql,BigDecimal.class).getSingleResult();
    }
}
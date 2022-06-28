package com.br.alura.loja.dao;

import com.br.alura.loja.model.cliente.Cliente;
import com.br.alura.loja.model.pedido.Pedido;

import javax.persistence.EntityManager;

public class ClienteDao {
    private final EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastra(Cliente cliente){
        em.persist(cliente);
    }

    public Cliente atualizar (Cliente cliente){
        return em.merge(cliente);
    }

    public void remover(Cliente cliente){
        cliente = em.merge(cliente);
        em.remove(cliente);
    }

    public Cliente findById(long id) {
        return em.find(Cliente.class, id);
    }
}

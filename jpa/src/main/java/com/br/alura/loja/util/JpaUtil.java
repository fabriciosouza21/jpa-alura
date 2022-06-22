package com.br.alura.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory  FACTORY = Persistence.createEntityManagerFactory("loja");

    private JpaUtil(){
    }

    public static EntityManager getEntityManageProduto(){
        return FACTORY.createEntityManager();
    }

}

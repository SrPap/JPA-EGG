package com.egg.persistencia;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMF {
    private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("LibreriaPU");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emfInstance;
    }
}

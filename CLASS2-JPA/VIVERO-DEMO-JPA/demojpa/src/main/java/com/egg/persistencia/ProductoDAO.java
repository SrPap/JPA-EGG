package com.egg.persistencia;

import com.egg.entidades.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ProductoDAO {

    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardaProducto(Producto producto) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
        em.close();
    }

    public Producto buscarProducto(int id) {
        EntityManager em = emf.createEntityManager();
        Producto producto = em.find(Producto.class, id);
        em.close();
        return producto;
    }

    public void actualizarProducto(Producto producto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarProducto(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Producto producto = em.find(Producto.class, id);
            if (producto != null) {
                em.remove(producto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}

package com.egg.persistencia;

import com.egg.entidades.DetallePedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class DetallePedidoDAO {

    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardaDetallePedido(DetallePedido detallePedido) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(detallePedido);
        em.getTransaction().commit();
        em.close();
    }

    public DetallePedido buscarDetallePedido(int id) {
        EntityManager em = emf.createEntityManager();
        DetallePedido detallePedido = em.find(DetallePedido.class, id);
        em.close();
        return detallePedido;
    }

    public void actualizarDetallePedido(DetallePedido detallePedido) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(detallePedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarDetallePedido(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            DetallePedido detallePedido = em.find(DetallePedido.class, id);
            if (detallePedido != null) {
                em.remove(detallePedido);
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

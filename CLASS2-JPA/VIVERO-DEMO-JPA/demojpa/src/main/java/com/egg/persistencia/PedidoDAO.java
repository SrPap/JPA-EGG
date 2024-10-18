package com.egg.persistencia;

import com.egg.entidades.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PedidoDAO {

    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardaPedido(Pedido pedido) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
        em.close();
    }

    public Pedido buscarPedido(int id) {
        EntityManager em = emf.createEntityManager();
        Pedido pedido = em.find(Pedido.class, id);
        em.close();
        return pedido;
    }

    public void actualizarPedido(Pedido pedido) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarPedido(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Pedido pedido = em.find(Pedido.class, id);
            if (pedido != null) {
                em.remove(pedido);
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

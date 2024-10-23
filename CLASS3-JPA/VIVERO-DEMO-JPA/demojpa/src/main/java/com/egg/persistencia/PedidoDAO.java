package com.egg.persistencia;

import java.util.Date;
import java.util.List;

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

    public List<Pedido> listarPedidosPorFechaEntrega() throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT p FROM Pedido p WHERE p.fechaEntrega > p.fechaEsperada", Pedido.class)
                 .getResultList();
    }

    public List<Pedido> listarPedidosDeClientePorRango(String nombreCliente, Date fechaInicio, Date fechaFin) throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.createQuery(
            "SELECT p FROM Pedido p JOIN p.cliente c WHERE c.nombreContacto = :nombreCliente AND p.fechaPedido BETWEEN :fechaInicio AND :fechaFin", Pedido.class)
            .setParameter("nombreCliente", nombreCliente)
            .setParameter("fechaInicio", fechaInicio)
            .setParameter("fechaFin", fechaFin)
            .getResultList();
    }
}

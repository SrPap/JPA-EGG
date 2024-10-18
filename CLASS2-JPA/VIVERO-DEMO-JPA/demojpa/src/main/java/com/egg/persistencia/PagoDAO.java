package com.egg.persistencia;
import com.egg.entidades.Cliente;
import com.egg.entidades.Pago;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PagoDAO {

    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardaPago(Pago pago) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pago);
        em.getTransaction().commit();
        em.close();
    }

    public Pago buscarPago(int id) {
        EntityManager em = emf.createEntityManager();
        Pago pago = em.find(Pago.class, id);
        em.close();
        return pago;
    }

    public void actualizarPago(Pago pago) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pago);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarPago(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Pago pago = em.find(Pago.class, id);
            if (pago != null) {
                em.remove(pago);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Cliente buscarCliente(int id) {
        EntityManager em = emf.createEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        em.close();
        return cliente;
    }
}

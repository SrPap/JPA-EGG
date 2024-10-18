package com.egg.persistencia;

import com.egg.entidades.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ClienteDAO {

    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardaCliente(Cliente cliente) throws Exception {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al guardar el cliente: " + e.getMessage());
        }
        em.close();
    }

    public Cliente buscarCliente(int id) {
        EntityManager em = emf.createEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        em.close();
        return cliente;
    }

    public void actualizarCliente(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarCliente(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.remove(cliente);
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

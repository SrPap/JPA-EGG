package com.egg.persistencia;

import java.util.List;

import com.egg.entidades.Editorial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EditorialDAO {
    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();
    
    public void guardaEditorial(Editorial editorial) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(editorial);
        em.getTransaction().commit();
        em.close();
    }

    public Editorial buscarEditorial(int id) {
        EntityManager em = emf.createEntityManager();
        Editorial editorial = em.find(Editorial.class, id);
        em.close();
        return editorial;
    }

    public void actualizarEditorial(Editorial editorial) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarEditorial(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Editorial editorial = em.find(Editorial.class, id);
            if (editorial != null) {
                em.remove(editorial);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Editorial> listarTodos() throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT e FROM Editorial e", Editorial.class).getResultList();
    }
}

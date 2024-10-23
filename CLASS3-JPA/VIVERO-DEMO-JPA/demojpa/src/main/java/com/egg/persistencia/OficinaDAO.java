package com.egg.persistencia;

import java.util.List;

import com.egg.entidades.Oficina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class OficinaDAO {

    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardaOficina(Oficina oficina) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(oficina);
        em.getTransaction().commit();
        em.close();
    }

    public Oficina buscarOficina(int id) {
        EntityManager em = emf.createEntityManager();
        Oficina oficina = em.find(Oficina.class, id);
        em.close();
        return oficina;
    }

    public void actualizarOficina(Oficina oficina) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(oficina);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarOficina(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Oficina oficina = em.find(Oficina.class, id);
            if (oficina != null) {
                em.remove(oficina);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }


    public List<Oficina> listarTodas() throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT o FROM Oficina o", Oficina.class)
        .getResultList();
    }
}

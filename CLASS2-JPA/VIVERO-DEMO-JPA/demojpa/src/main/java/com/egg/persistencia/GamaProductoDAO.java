package com.egg.persistencia;

import com.egg.entidades.GamaProducto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class GamaProductoDAO {

    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardaGamaProducto(GamaProducto gamaProducto) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(gamaProducto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al guardar la gama producto: " + e.getMessage());
        }
        em.close();
    }

    public GamaProducto buscarGamaProducto(int id) {
        EntityManager em = emf.createEntityManager();
        GamaProducto gamaProducto = em.find(GamaProducto.class, id);
        em.close();
        return gamaProducto;
    }

    public void actualizarGamaProducto(GamaProducto gamaProducto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(gamaProducto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarGamaProducto(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            GamaProducto gamaProducto = em.find(GamaProducto.class, id);
            if (gamaProducto != null) {
                em.remove(gamaProducto);
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

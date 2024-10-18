package com.egg.persistencia;
//
import com.egg.entidades.Empleado;
import com.egg.entidades.Oficina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EmpleadoDAO {

    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardaEmpleado(Empleado empleado) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(empleado);
        em.getTransaction().commit();
        em.close();
    }

    public Empleado buscarEmpleado(int id) {
        EntityManager em = emf.createEntityManager();
        Empleado empleado = em.find(Empleado.class, id);
        em.close();
        return empleado;
    }

    public void actualizarEmpleado(Empleado empleado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(empleado);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarEmpleado(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Empleado empleado = em.find(Empleado.class, id);
            if (empleado != null) {
                em.remove(empleado);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    
    public Oficina buscarOficina(int id) {
        EntityManager em = emf.createEntityManager();
        Oficina oficina = em.find(Oficina.class, id);
        em.close();
        return oficina;
    }

}

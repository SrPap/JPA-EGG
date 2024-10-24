package com.egg.persistencia;

import java.util.List;

import com.egg.entidades.Libro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class LibroDAO {

    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardaLibro(Libro libro) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizarLibro(Libro libro) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarLibro(long isbn) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Libro libro = em.find(Libro.class, isbn);
            if (libro != null) {
                em.remove(libro);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    
    public Libro buscarLibro(long isbn) {
        EntityManager em = emf.createEntityManager();
        Libro libro = em.find(Libro.class, isbn);
        em.close();
        return libro;
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        EntityManager em = emf.createEntityManager();
        Libro libro = em.find(Libro.class, titulo);
        em.close();
        return libro;
    }

    public List<Libro> buscarLibrosPorAutor(String nombreAutor) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Libro> query = em.createQuery(
            "SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre = :nombreAutor", 
            Libro.class);
    query.setParameter("nombreAutor", nombreAutor);
    return query.getResultList();
    }

    public List<Libro> buscarLibrosPorEditorial(String nombreEditorial) {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Libro> query = em.createQuery(
            "SELECT l FROM Libro l JOIN l.editorial e WHERE e.nombre = :nombreEditorial", 
            Libro.class);
        query.setParameter("nombreEditorial", nombreEditorial);
        return query.getResultList();
    }


    public List<Libro> listarTodos() throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
    }

}

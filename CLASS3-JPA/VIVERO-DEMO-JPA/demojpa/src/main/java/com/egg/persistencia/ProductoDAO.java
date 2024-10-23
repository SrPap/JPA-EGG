package com.egg.persistencia;

import java.util.List;

import com.egg.entidades.GamaProducto;
import com.egg.entidades.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ProductoDAO {

    private final EntityManagerFactory emf = EMF.getEntityManagerFactory();

    public void guardarProducto(Producto producto) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
        em.close();
    }

    public Producto buscarProducto(int id) {
        EntityManager em = emf.createEntityManager();
        Producto producto = em.find(Producto.class, id);
        em.close();
        return producto;
    }

    public void actualizarProducto(Producto producto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void eliminarProducto(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Producto producto = em.find(Producto.class, id);
            if (producto != null) {
                em.remove(producto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public GamaProducto buscarGamaProducto(int id) {
        EntityManager em = emf.createEntityManager();
        GamaProducto gamaProducto = em.find(GamaProducto.class, id);
        em.close();
        return gamaProducto;
    }

    public List<Producto> listarTodos() throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT p FROM Producto p", Producto.class)
        .getResultList();
    }

    public List<Producto> listarProductosPorNombre(String nombreProducto) throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.createQuery(
            "SELECT p FROM Producto p JOIN p.detallesPedido dp WHERE p.nombre LIKE :nombre", Producto.class)
            .setParameter("nombre", "%" + nombreProducto + "%")
            .getResultList();
    }

    public List<Object[]> listarProductosMasVendidos() throws Exception {
        EntityManager em = emf.createEntityManager();
        return em.createQuery(
            "SELECT p.nombre, SUM(dp.cantidad) AS totalVendidos " +
            "FROM Producto p JOIN p.detallesPedido dp " +
            "GROUP BY p.idProducto " +
            "ORDER BY totalVendidos DESC", Object[].class)
            .setMaxResults(10)
            .getResultList();
    }

}



package com.egg.servicios;

import java.util.List;

import com.egg.entidades.Autor;
import com.egg.persistencia.AutorDAO;

public class AutorServicio {

    private final AutorDAO autorDAO;

    public AutorServicio() {
        this.autorDAO = new AutorDAO();
    }

    public void guardarAutor(String nombre, boolean alta) {
        try {

            if (nombre == null || nombre.isEmpty()) {
                throw new Exception("El nombre del autor es obligatorio.");
            }
            if (autorDAO.existeAutorPorNombre(nombre)) {
                throw new Exception("Ya existe un autor con este nombre.");
            }

            Autor nuevoAutor = new Autor();

            nuevoAutor.setNombre(nombre);
            nuevoAutor.setAlta(alta);

            autorDAO.guardaAutor(nuevoAutor);

            System.out.println("El Autor se ha guardado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - No se guardó el nuevo Autor de manera correcta.");
        }
    }


    public void listarAutor() throws Exception {
        List<Autor> todosAutor = autorDAO.listarTodos();
        imprimirLista(todosAutor);
    }

    public void imprimirLista(List<Autor> listaRecibida) {
        for (Autor cliente : listaRecibida) {
            System.out.println("ID: "+cliente.getIdAutor() + " - Autor:" 
                               + cliente.getNombre() + " - Alta:"
                               + cliente.getAlta());
        }
    }

    public Autor buscarAutor(int id) {
        return autorDAO.buscarAutor(id);
    }

    public Autor buscarAutor(String nombre) {
        return autorDAO.buscarAutorPorNombre(nombre);
    }

    public void actualizarAutor(int idAutor,String nombre, boolean alta) {
        try {
            Autor autor = autorDAO.buscarAutor(idAutor);
            if (nombre == null || nombre.isEmpty()) {
                throw new Exception("El nombre del autor es obligatorio.");
            }

            if (autor != null) {
                autor.setIdAutor(idAutor);
                autor.setNombre(nombre);
                autor.setAlta(alta);

                autorDAO.actualizarAutor(autor);
            } else {
                if (autor == null) {
                    System.out.println("Autor no encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el Autor: " + e.getMessage());
        }
    }

    public void eliminarAutor(int id) {
        try {
            Autor autor = autorDAO.buscarAutor(id);
            if (autor != null) {
                autorDAO.eliminarAutor(id);
            } else {
                System.out.println("Autor no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el Autor: " + e.getMessage());
        }
    }

}

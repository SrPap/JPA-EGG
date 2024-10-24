package com.egg.servicios;

import java.util.List;

import com.egg.entidades.Editorial;
import com.egg.persistencia.EditorialDAO;

public class EditorialServicio  {

    private final EditorialDAO editorialDAO;

    public EditorialServicio () {
        this.editorialDAO = new EditorialDAO();
    }

    public void guardarEditorial(String nombre, boolean alta) {
        try {
            Editorial nuevoEditorial = new Editorial();

            nuevoEditorial.setNombre(nombre);
            nuevoEditorial.setAlta(alta);

            editorialDAO.guardaEditorial(nuevoEditorial);

            System.out.println("El Editorial se ha guardado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - No se guardó el nuevo Editorial de manera correcta.");
        }
    }


    public void listarEditorial() throws Exception {
        List<Editorial> todoseditorial = editorialDAO.listarTodos();
        imprimirLista(todoseditorial);
    }

    public void imprimirLista(List<Editorial> listaRecibida) {
        for (Editorial editorial : listaRecibida) {
            System.out.println("ID: "+editorial.getIdEditorial() + " - Editorial:" 
                               + editorial.getNombre() + " - Alta:"
                               + editorial.getAlta());
        }
    }

    public Editorial buscarEditorial(int id) {
        return editorialDAO.buscarEditorial(id);
    }

    public void actualizarEditorial(int idEditorial,String nombre, boolean alta) {
        try {
            Editorial editorial = editorialDAO.buscarEditorial(idEditorial);

            if (editorial != null) {
                editorial.setIdEditorial(idEditorial);
                editorial.setNombre(nombre);
                editorial.setAlta(alta);

                editorialDAO.actualizarEditorial(editorial);
            } else {
                if (editorial == null) {
                    System.out.println("Editorial no encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el Editorial: " + e.getMessage());
        }
    }

    public void eliminarEditorial(int id) {
        try {
            Editorial editorial = editorialDAO.buscarEditorial(id);
            if (editorial != null) {
                editorialDAO.eliminarEditorial(id);
            } else {
                System.out.println("Editorial no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el Editorial: " + e.getMessage());
        }
    }

}

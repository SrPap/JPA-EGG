package com.egg.servicios;

import java.util.List;

import com.egg.entidades.Libro;
import com.egg.persistencia.LibroDAO;

public class LibroServicio  {

    private final LibroDAO libroDAO;

    public LibroServicio () {
        this.libroDAO = new LibroDAO();
    }

    public void guardarLibro(String titulo,int anio, int ejemplares, boolean alta) {
        try {
            Libro nuevoLibro = new Libro();


            nuevoLibro.setTitulo(titulo);
            nuevoLibro.setAnio(anio);
            nuevoLibro.setEjemplares(ejemplares);
            nuevoLibro.setAlta(alta);

            libroDAO.guardaLibro(nuevoLibro);

            System.out.println("El Libro se ha guardado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - No se guardó el nuevo Libro de manera correcta.");
        }
    }


    public void listarLibro() throws Exception {
        List<Libro> todosLibro = libroDAO.listarTodos();
        imprimirLista(todosLibro);
    }

    public void imprimirLista(List<Libro> listaRecibida) {
        for (Libro libro : listaRecibida) {
            System.out.println("ISBN: "+libro.getIsbn() + " - Libro:" 
                               + libro.getTitulo() + " - Alta:"
                               + libro.getAlta());
        }
    }

    public Libro buscarLibro(int id) {
        return libroDAO.buscarLibro(id);
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        return libroDAO.buscarLibroPorTitulo(titulo);
    }

    public List<Libro> buscarLibrosPorAutor(String nombreAutor) {
        return libroDAO.buscarLibrosPorAutor(nombreAutor);
    }

    public List<Libro> buscarLibrosPorEditorial(String nombreEditorial) {
        return libroDAO.buscarLibrosPorEditorial(nombreEditorial);
    }


    public void actualizarLibro(long isbn, String titulo,int anio, int ejemplares, boolean alta) {
        try {
            Libro libro = libroDAO.buscarLibro(isbn);

            if (libro != null) {
                libro.setTitulo(titulo);
                libro.setAnio(anio);
                libro.setEjemplares(ejemplares);
                libro.setAlta(alta);

                libroDAO.actualizarLibro(libro);
            } else {
                if (libro == null) {
                    System.out.println("Libro no encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el Libro: " + e.getMessage());
        }
    }

    public void eliminarLibro(long isbn) {
        try {
            Libro libro = libroDAO.buscarLibro(isbn);
            if (libro != null) {
                libroDAO.eliminarLibro(isbn);
            } else {
                System.out.println("Libro no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el Libro: " + e.getMessage());
        }
    }

}

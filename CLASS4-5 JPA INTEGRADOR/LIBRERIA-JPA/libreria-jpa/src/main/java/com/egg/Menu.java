package com.egg;
import java.util.List;
import java.util.Scanner;

import com.egg.entidades.Autor;
import com.egg.entidades.Editorial;
import com.egg.entidades.Libro;
import com.egg.servicios.AutorServicio;
import com.egg.servicios.EditorialServicio;
import com.egg.servicios.LibroServicio;

public class Menu {

    private final AutorServicio autorServicio = new AutorServicio();
    private final EditorialServicio editorialServicio = new EditorialServicio();
    private final LibroServicio libroServicio = new LibroServicio();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n---- Menú Principal ----");
            System.out.println("1. Gestionar Autores");
            System.out.println("2. Gestionar Editoriales");
            System.out.println("3. Gestionar Libros");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> menuAutores();
                case 2 -> menuEditoriales();
                case 3 -> menuLibros();
                case 4 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    // ---- Menú Autores ----
    private void menuAutores() {
        int opcion;

        do {
            System.out.println("\n---- Menú Autores ----");
            System.out.println("1. Agregar Autor");
            System.out.println("2. Listar Autores");
            System.out.println("3. Buscar Autor por ID");
            System.out.println("4. Buscar Autor por ID");
            System.out.println("5. Actualizar Autor");
            System.out.println("6. Eliminar Autor");
            System.out.println("7. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> agregarAutor();
                case 2 -> listarAutores();
                case 3 -> buscarAutor();
                case 4 -> buscarAutorPorNombre();
                case 5 -> actualizarAutor();
                case 6 -> eliminarAutor();
                case 7 -> System.out.println("Volviendo al Menú Principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    private void agregarAutor() {
        System.out.print("Ingrese el nombre del autor: ");
        String nombre = scanner.next();
        System.out.print("¿El autor está activo? (true/false): ");
        boolean alta = scanner.nextBoolean();
        autorServicio.guardarAutor(nombre, alta);
    }

    private void listarAutores() {
        try {
            autorServicio.listarAutor();
        } catch (Exception e) {
            System.out.println("Error al listar autores: " + e.getMessage());
        }
    }

    private void buscarAutor() {
        System.out.print("Ingrese el ID del autor: ");
        int id = scanner.nextInt();
        Autor autor = autorServicio.buscarAutor(id);
        if (autor != null) {
            System.out.println("Autor encontrado: " + autor.getNombre());
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    private void buscarAutorPorNombre() {
        System.out.print("Ingrese el nombre del autor: ");
        String nombre = scanner.nextLine();
        Autor autor = autorServicio.buscarAutor(nombre);
        if (autor != null) {
            System.out.println("Autor encontrado: " + autor.getNombre());
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    private void actualizarAutor() {
        System.out.print("Ingrese el ID del autor: ");
        int id = scanner.nextInt();
        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = scanner.next();
        System.out.print("¿El autor está activo? (true/false): ");
        boolean alta = scanner.nextBoolean();
        autorServicio.actualizarAutor(id, nombre, alta);
    }

    private void eliminarAutor() {
        System.out.print("Ingrese el ID del autor: ");
        int id = scanner.nextInt();
        autorServicio.eliminarAutor(id);
    }

    // ---- Menú Editoriales ----
    private void menuEditoriales() {
        int opcion;

        do {
            System.out.println("\n---- Menú Editoriales ----");
            System.out.println("1. Agregar Editorial");
            System.out.println("2. Listar Editoriales");
            System.out.println("3. Buscar Editorial por ID");
            System.out.println("4. Actualizar Editorial");
            System.out.println("5. Eliminar Editorial");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> agregarEditorial();
                case 2 -> listarEditoriales();
                case 3 -> buscarEditorial();
                case 4 -> actualizarEditorial();
                case 5 -> eliminarEditorial();
                case 6 -> System.out.println("Volviendo al Menú Principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    private void agregarEditorial() {
        System.out.print("Ingrese el nombre de la editorial: ");
        String nombre = scanner.next();
        System.out.print("¿La editorial está activa? (true/false): ");
        boolean alta = scanner.nextBoolean();
        editorialServicio.guardarEditorial(nombre, alta);
    }

    private void listarEditoriales() {
        try {
            editorialServicio.listarEditorial();
        } catch (Exception e) {
            System.out.println("Error al listar editoriales: " + e.getMessage());
        }
    }

    private void buscarEditorial() {
        System.out.print("Ingrese el ID de la editorial: ");
        int id = scanner.nextInt();
        Editorial editorial = editorialServicio.buscarEditorial(id);
        if (editorial != null) {
            System.out.println("Editorial encontrada: " + editorial.getNombre());
        } else {
            System.out.println("Editorial no encontrada.");
        }
    }

    private void actualizarEditorial() {
        System.out.print("Ingrese el ID de la editorial: ");
        int id = scanner.nextInt();
        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = scanner.next();
        System.out.print("¿La editorial está activa? (true/false): ");
        boolean alta = scanner.nextBoolean();
        editorialServicio.actualizarEditorial(id, nombre, alta);
    }

    private void eliminarEditorial() {
        System.out.print("Ingrese el ID de la editorial: ");
        int id = scanner.nextInt();
        editorialServicio.eliminarEditorial(id);
    }

    // ---- Menú Libros ----
    private void menuLibros() {
        int opcion;

        do {
            System.out.println("\n---- Menú Libros ----");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Listar Libros");
            System.out.println("3. Buscar Libro por ISBN");
            System.out.println("4. Buscar Libro por Titulo");
            System.out.println("5. Buscar Libro por Autor");
            System.out.println("6. Buscar Libro por Editorial");
            System.out.println("7. Actualizar Libro");
            System.out.println("8. Eliminar Libro");
            System.out.println("9. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> agregarLibro();
                case 2 -> listarLibros();
                case 3 -> buscarLibro();
                case 4 -> buscarLibroPorTitulo();
                case 5 -> buscarLibroPorAutor();
                case 6 -> buscarLibroPorEditorial();
                case 7 -> actualizarLibro();
                case 8 -> eliminarLibro();
                case 9 -> System.out.println("Volviendo al Menú Principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    private void agregarLibro() {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.next();
        System.out.print("Ingrese el año de publicación: ");
        int anio = scanner.nextInt();
        System.out.print("Ingrese el número de ejemplares: ");
        int ejemplares = scanner.nextInt();
        System.out.print("¿El libro está activo? (true/false): ");
        boolean alta = scanner.nextBoolean();
        libroServicio.guardarLibro(titulo, anio, ejemplares, alta);
    }

    private void listarLibros() {
        try {
            libroServicio.listarLibro();
        } catch (Exception e) {
            System.out.println("Error al listar libros: " + e.getMessage());
        }
    }

    private void buscarLibro() {
        System.out.print("Ingrese el ISBN del libro: ");
        int isbn = scanner.nextInt();
        Libro libro = libroServicio.buscarLibro(isbn);
        if (libro != null) {
            System.out.println("Libro encontrado: " + libro.getTitulo());
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.next();
        Libro libro = libroServicio.buscarLibroPorTitulo(titulo);
        if (libro != null) {
            System.out.println("Libro encontrado: " + libro.getTitulo());
        } else {
            System.out.println("Libro no encontrado.");
        }
    }
    
    private void buscarLibroPorAutor() {
        System.out.print("Ingrese el nombre del autor: ");
        String nombreAutor = scanner.next();
        List<Libro> libros = libroServicio.buscarLibrosPorAutor(nombreAutor);
        if (!libros.isEmpty()) {
            System.out.println("Libros encontrados:");
            for (Libro libro : libros) {
                System.out.println(libro.getTitulo());
            }
        } else {
            System.out.println("No se encontraron libros para este autor.");
        }
    }

    void buscarLibroPorEditorial() {
        System.out.print("Ingrese el nombre de la editorial: ");
        String nombreEditorial = scanner.next();
        List<Libro> libros = libroServicio.buscarLibrosPorEditorial(nombreEditorial);
            if (!libros.isEmpty()) {
            System.out.println("Libros encontrados:");
            for (Libro libro : libros) {
                System.out.println(libro.getTitulo());
            }
        } else {
            System.out.println("No se encontraron libros para esta editorial.");
        }
    }

    private void actualizarLibro() {
        System.out.print("Ingrese el ISBN del libro: ");
        long isbn = scanner.nextLong();
        System.out.print("Ingrese el nuevo título: ");
        String titulo = scanner.next();
        System.out.print("Ingrese el nuevo año de publicación: ");
        int anio = scanner.nextInt();
        System.out.print("Ingrese el número de ejemplares: ");
        int ejemplares = scanner.nextInt();
        System.out.print("¿El libro está activo? (true/false): ");
        boolean alta = scanner.nextBoolean();
        libroServicio.actualizarLibro(isbn, titulo, anio, ejemplares, alta);
    }

    private void eliminarLibro() {
        System.out.print("Ingrese el ISBN del libro: ");
        long isbn = scanner.nextLong();
        libroServicio.eliminarLibro(isbn);
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mostrarMenu();
    }
}


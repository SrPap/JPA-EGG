package com.egg.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "isbn")
    private long isbn;

    @Column(name = "titulo", length = 255)
    private String titulo;

    @Column(name = "anio")
    private int anio;

    @Column(name = "ejemplares", length = 10)
    private int ejemplares;

    @Column(name = "alta", length = 100)
    private boolean alta;


    @ManyToOne
    @JoinColumn(name = "autor")
    private Autor autor;
    
    @ManyToOne
    @JoinColumn(name = "editorial")
    private Editorial editorial;

    public Libro() {
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public boolean getAlta() {
        return alta;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    
}

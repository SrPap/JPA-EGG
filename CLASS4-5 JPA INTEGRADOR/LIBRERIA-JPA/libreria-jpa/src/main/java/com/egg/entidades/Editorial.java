package com.egg.entidades;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "editorial")
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_editorial")
    private int idEditorial;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "alta")
    private boolean alta;

    public Editorial() {
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public boolean getAlta() {
        return alta;
    }

}

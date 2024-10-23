package com.egg.entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    @Column(name = "cantidad_en_stock")
    private int cantidadEnStock;

    @Column(name = "codigo_producto", length = 255)
    private String codigoProducto;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "precio_venta")
    private double precioVenta;

    @Column(name = "precio_proveedor")
    private double precioProveedor;

    @Column(name = "proveedor", length = 255)
    private String proveedor;

    @Column(name = "dimensiones", length = 255)
    private String dimensiones;


    @ManyToOne
    @JoinColumn(name = "id_gama")
    private GamaProducto gamaProducto;

    @OneToMany(mappedBy = "producto")
    private List<DetallePedido> detallesPedido;

    public Producto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcionHtml() {
        return descripcion;
    }

    public void setDescripcionHtml(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getNombreProducto() {
        return nombre;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombre = nombreProducto;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioProveedor() {
        return precioProveedor;
    }

    public void setPrecioProveedor(double precioProveedor) {
        this.precioProveedor = precioProveedor;
    }

    public GamaProducto getGamaProducto() {
        return gamaProducto;
    }

    public void setGamaProducto(GamaProducto gamaProducto) {
        this.gamaProducto = gamaProducto;
    }

    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }
}

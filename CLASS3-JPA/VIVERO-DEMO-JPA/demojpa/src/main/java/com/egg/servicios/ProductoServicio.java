package com.egg.servicios;

import java.util.List;

import com.egg.entidades.Producto;
import com.egg.persistencia.ProductoDAO;

public class ProductoServicio {

    private final ProductoDAO daoProducto;

    public ProductoServicio() {
        this.daoProducto = new ProductoDAO();
    }

    public void crearProducto(String nombre, double precio, String descripcion, int idGamaProducto) {
        try {
            Producto productoNuevo = new Producto();
            productoNuevo.setNombreProducto(nombre);
            productoNuevo.setPrecioProveedor(precio);
            productoNuevo.setDescripcionHtml(descripcion);
            productoNuevo.setGamaProducto(daoProducto.buscarGamaProducto(idGamaProducto)); 

            daoProducto.guardarProducto(productoNuevo);
        } catch (Exception e) {
            System.out.println("Error al crear el producto: " + e.getMessage());
        }
    }

    public Producto buscarProducto(int idProducto) {
        return daoProducto.buscarProducto(idProducto);
    }

    public void actualizarProducto(int idProducto, String nombre, double precio, String descripcion, int idGamaProducto) {
        try {
            Producto producto = daoProducto.buscarProducto(idProducto);
            if (producto != null) {
                producto.setNombreProducto(nombre);
                producto.setPrecioProveedor(precio);
                producto.setDescripcionHtml(descripcion);
                producto.setGamaProducto(daoProducto.buscarGamaProducto(idGamaProducto)); // Asignar gama de producto

                daoProducto.actualizarProducto(producto);
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    public void eliminarProducto(int idProducto) {
        try {
            Producto producto = daoProducto.buscarProducto(idProducto);
            if (producto != null) {
                daoProducto.eliminarProducto(idProducto);
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }

    public void listarProductos() throws Exception {
        List<Producto> todosProductos = daoProducto.listarTodos();
        imprimirLista(todosProductos);
    }

    public void listarProductos(String nombreProducto) throws Exception {
        List<Producto> todosProductos = daoProducto.listarProductosPorNombre(nombreProducto);
        imprimirLista(todosProductos);
    }

    public void imprimirLista(List<Producto> listaRecibida) {
        for (Producto unitarioProducto : listaRecibida) {
            System.out.println(unitarioProducto.getIdProducto() + " - " + unitarioProducto.getNombreProducto() + " - " + unitarioProducto.getPrecioVenta());
        }
    }

    public void imprimirListaObjetos(List<Object[]> productosMasVendidos) {
        for (Object unitarioProducto : productosMasVendidos) {
            System.out.println(((Producto) unitarioProducto).getIdProducto() + " - " + ((Producto) unitarioProducto).getNombreProducto() + " - " + ((Producto) unitarioProducto).getPrecioVenta());
        }
    }

    public void listarProductosMasVendidos() throws Exception {
        List<Object[]> ProductosMasVendidos = daoProducto.listarProductosMasVendidos();
        imprimirListaObjetos(ProductosMasVendidos);
    }
}

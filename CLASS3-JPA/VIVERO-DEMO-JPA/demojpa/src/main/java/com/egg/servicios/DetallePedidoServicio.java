package com.egg.servicios;

import com.egg.entidades.DetallePedido;
import com.egg.entidades.Pedido;
import com.egg.entidades.Producto;
import com.egg.persistencia.DetallePedidoDAO;
import com.egg.persistencia.PedidoDAO;
import com.egg.persistencia.ProductoDAO;

public class DetallePedidoServicio {

    private final DetallePedidoDAO daoDetallePedido;
    private final ProductoDAO daoProducto;
    private final PedidoDAO daoPedido;

    public DetallePedidoServicio() {
        this.daoDetallePedido = new DetallePedidoDAO();
        this.daoProducto = new ProductoDAO();
        this.daoPedido = new PedidoDAO();
    }

    public void crearDetallePedido(int idProducto, int idPedido, int cantidad, int numeroLinea, double precioUnidad) {
        try {
            Producto producto = daoProducto.buscarProducto(idProducto);
            Pedido pedido = daoPedido.buscarPedido(idPedido);

            if (producto != null && pedido != null) {
                DetallePedido detallePedido = new DetallePedido();
                detallePedido.setProducto(producto);
                detallePedido.setPedido(pedido);
                detallePedido.setCantidad(cantidad);
                detallePedido.setNumeroLinea(numeroLinea);
                detallePedido.setPrecioUnidad(precioUnidad);

                daoDetallePedido.guardaDetallePedido(detallePedido);
            } else {
                if (producto == null) {
                    System.out.println("Producto no encontrado.");
                }
                if (pedido == null) {
                    System.out.println("Pedido no encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al crear el detalle del pedido: " + e.getMessage());
        }
    }

    public DetallePedido buscarDetallePedido(int idDetallePedido) {
        return daoDetallePedido.buscarDetallePedido(idDetallePedido);
    }

    public void actualizarDetallePedido(int idDetallePedido, int idProducto, int idPedido, int cantidad, int numeroLinea, double precioUnidad) {
        try {
            DetallePedido detallePedido = daoDetallePedido.buscarDetallePedido(idDetallePedido);
            Producto producto = daoProducto.buscarProducto(idProducto);
            Pedido pedido = daoPedido.buscarPedido(idPedido);

            if (detallePedido != null && producto != null && pedido != null) {
                detallePedido.setProducto(producto);
                detallePedido.setPedido(pedido);
                detallePedido.setCantidad(cantidad);
                detallePedido.setNumeroLinea(numeroLinea);
                detallePedido.setPrecioUnidad(precioUnidad);

                daoDetallePedido.actualizarDetallePedido(detallePedido);
            } else {
                if (detallePedido == null) {
                    System.out.println("Detalle de pedido no encontrado.");
                }
                if (producto == null) {
                    System.out.println("Producto no encontrado.");
                }
                if (pedido == null) {
                    System.out.println("Pedido no encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el detalle del pedido: " + e.getMessage());
        }
    }

    public void eliminarDetallePedido(int idDetallePedido) {
        try {
            DetallePedido detallePedido = daoDetallePedido.buscarDetallePedido(idDetallePedido);
            if (detallePedido != null) {
                daoDetallePedido.eliminarDetallePedido(idDetallePedido);
            } else {
                System.out.println("Detalle de pedido no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el detalle del pedido: " + e.getMessage());
        }
    }
}

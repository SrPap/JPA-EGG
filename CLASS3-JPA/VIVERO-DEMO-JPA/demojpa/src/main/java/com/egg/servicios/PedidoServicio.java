package com.egg.servicios;

import java.util.Date;
import java.util.List;

import com.egg.entidades.Cliente;
import com.egg.entidades.Pedido;
import com.egg.persistencia.ClienteDAO;
import com.egg.persistencia.PedidoDAO;

public class PedidoServicio {

    private final PedidoDAO daoPedido;
    private final ClienteDAO daoCliente;

    public PedidoServicio() {
        this.daoPedido = new PedidoDAO();
        this.daoCliente = new ClienteDAO();
    }

    public void crearPedido(String codigoPedido, String comentarios, String estado,
                            Date fechaEntrega, Date fechaEsperada, Date fechaPedido, int idCliente) {
        try {
            Cliente cliente = daoCliente.buscarCliente(idCliente);

            if (cliente != null) {
                Pedido nuevoPedido = new Pedido();
                nuevoPedido.setCodigoPedido(codigoPedido);
                nuevoPedido.setComentarios(comentarios);
                nuevoPedido.setEstado(estado);
                nuevoPedido.setFechaEntrega(fechaEntrega);
                nuevoPedido.setFechaEsperada(fechaEsperada);
                nuevoPedido.setFechaPedido(fechaPedido);
                nuevoPedido.setCliente(cliente);

                daoPedido.guardaPedido(nuevoPedido);
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al crear el pedido: " + e.getMessage());
        }
    }

    public Pedido buscarPedido(int idPedido) {
        return daoPedido.buscarPedido(idPedido);
    }

    public void actualizarPedido(int idPedido, String codigoPedido, String comentarios, String estado,
                                 Date fechaEntrega, Date fechaEsperada, Date fechaPedido, int idCliente) {
        try {
            Pedido pedido = daoPedido.buscarPedido(idPedido);
            Cliente cliente = daoCliente.buscarCliente(idCliente);

            if (pedido != null && cliente != null) {
                pedido.setCodigoPedido(codigoPedido);
                pedido.setComentarios(comentarios);
                pedido.setEstado(estado);
                pedido.setFechaEntrega(fechaEntrega);
                pedido.setFechaEsperada(fechaEsperada);
                pedido.setFechaPedido(fechaPedido);
                pedido.setCliente(cliente);

                daoPedido.actualizarPedido(pedido);
            } else {
                if (pedido == null) {
                    System.out.println("Pedido no encontrado.");
                }
                if (cliente == null) {
                    System.out.println("Cliente no encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el pedido: " + e.getMessage());
        }
    }

    public void eliminarPedido(int idPedido) {
        try {
            Pedido pedido = daoPedido.buscarPedido(idPedido);
            if (pedido != null) {
                daoPedido.eliminarPedido(idPedido);
            } else {
                System.out.println("Pedido no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el pedido: " + e.getMessage());
        }
    }

    public void imprimirLista(List<Pedido> listaRecibida) {
        for (Pedido pedido : listaRecibida) {
            System.out.println(pedido.getIdPedido() + " - " 
                               + pedido.getCodigoPedido() + " - " 
                               + pedido.getFechaEntrega());
        }
    }
    
    public void listarPedidosPorFechaEntrega() throws Exception {
        List<Pedido> pedidos = daoPedido.listarPedidosPorFechaEntrega();
        imprimirLista(pedidos);
    }

    public void listarPedidosDeClientePorRango(String nombreCliente, Date fechaInicio, Date fechaFin) throws Exception {
        List<Pedido> pedidos = daoPedido.listarPedidosDeClientePorRango( nombreCliente,  fechaInicio,  fechaFin);
        imprimirLista(pedidos);
    }

     
}
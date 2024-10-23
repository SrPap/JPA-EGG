package com.egg.servicios;

import java.util.Date;
import java.util.List;

import com.egg.entidades.Cliente;
import com.egg.entidades.Pago;
import com.egg.persistencia.PagoDAO;

public class PagoServicio {

    private final PagoDAO daoPago;

    public PagoServicio() {
        this.daoPago = new PagoDAO();
    }

    public void crearPago(Date fechaPago, String formaPago, String idTransaction,
                         double total, int idCliente) {
        try {
            Pago pagoNuevo = new Pago();
            pagoNuevo.setFechaPago(fechaPago);
            pagoNuevo.setFormaPago(formaPago);
            pagoNuevo.setIdTransaccion(idTransaction);
            pagoNuevo.setTotal(total);

            Cliente cliente = daoPago.buscarCliente(idCliente);
            if (cliente != null) {
                pagoNuevo.setCliente(cliente);
                daoPago.guardaPago(pagoNuevo);
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al crear el pago: " + e.getMessage());
        }
    }

    public Pago buscarPago(int idPago) {
        return daoPago.buscarPago(idPago);
    }

    public void actualizarPago(int idPago, Date fechaPago, String formaPago, String idTransaction, double total, int idCliente) {
        try {
            Pago pago = daoPago.buscarPago(idPago);
            if (pago != null) {
                pago.setFechaPago(fechaPago);
                pago.setFormaPago(formaPago);
                pago.setIdTransaccion(idTransaction);
                pago.setTotal(total);

                Cliente cliente = daoPago.buscarCliente(idCliente);
                if (cliente != null) {
                    pago.setCliente(cliente);
                } else {
                    System.out.println("Cliente no encontrado.");
                }

                daoPago.actualizarPago(pago);
            } else {
                System.out.println("Pago no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el pago: " + e.getMessage());
        }
    }

    public void eliminarPago(int idPago) {
        try {
            Pago pago = daoPago.buscarPago(idPago);
            if (pago != null) {
                daoPago.eliminarPago(idPago);
            } else {
                System.out.println("Pago no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el pago: " + e.getMessage());
        }
    }

    
    public void imprimirLista(List<Pago> listaRecibida) {
        for (Pago unitarioPago : listaRecibida) {
            System.out.println(unitarioPago.getIdTransaccion() + " - cliente: " + unitarioPago.getCliente() + " - total:" + unitarioPago.getTotal() + " - forma de pago:" + unitarioPago.getFormaPago());
        }
    }

    public void listarPagosPorRangoFechas(Date fechaInicio, Date fechaFin) throws Exception {
        List<Pago> todoPagos = daoPago.listarPagosPorRangoFechas(fechaInicio , fechaFin);
        imprimirLista(todoPagos);
    }

    
}

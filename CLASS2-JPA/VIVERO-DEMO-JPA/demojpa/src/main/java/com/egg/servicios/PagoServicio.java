package com.egg.servicios;

import java.util.Date;

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
            pagoNuevo.setIdTransaction(idTransaction);
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
                pago.setIdTransaction(idTransaction);
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
}

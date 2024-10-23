package com.egg;

import com.egg.servicios.EmpleadoServicio;
import com.egg.servicios.PagoServicio;
import com.egg.servicios.PedidoServicio;
import com.egg.servicios.ProductoServicio;

public class Application {

    public static void main(String[] args) throws Exception {
       
    EmpleadoServicio empleadoServicio = new EmpleadoServicio();
    PedidoServicio pedidoServicio = new PedidoServicio();
    ProductoServicio productoServicio = new ProductoServicio();
    PagoServicio pagoServicio = new PagoServicio();

    try {
       

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}


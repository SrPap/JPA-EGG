package com.egg.servicios;

import com.egg.entidades.GamaProducto;
import com.egg.persistencia.GamaProductoDAO;

public class GamaProductoServicio {

    private final GamaProductoDAO daoGamaProducto;

    public GamaProductoServicio() {
        this.daoGamaProducto = new GamaProductoDAO();
    }

    public void guardarGamaProducto(String descripcionHtml, String descripcionTexto, 
                                     String gama, String imagen) {
        try {
            GamaProducto nuevaGama = new GamaProducto();

            nuevaGama.setDescripcionHtml(descripcionHtml);
            nuevaGama.setDescripcionTexto(descripcionTexto);
            nuevaGama.setGama(gama);
            nuevaGama.setImagen(imagen);

            daoGamaProducto.guardaGamaProducto(nuevaGama);

            System.out.println("La gama de productos se ha guardado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - No se guardó la nueva gama de productos de manera correcta.");
        }
    }
}

package com.egg.servicios;

import java.util.List;

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

     public GamaProducto buscarGamaProducto(int idGamaProducto) {
        return daoGamaProducto.buscarGamaProducto(idGamaProducto);
    }

    public void actualizarGamaProducto(int idGamaProducto, String descripcionHtml, String descripcionTexto, 
    String gama, String imagen) {
        try {
            GamaProducto gamaProducto = daoGamaProducto.buscarGamaProducto(idGamaProducto);
            if (gamaProducto != null) {
                gamaProducto.setDescripcionHtml(descripcionHtml);
                gamaProducto.setDescripcionTexto(descripcionTexto);
                gamaProducto.setGama(gama);
                gamaProducto.setImagen(imagen);                
                daoGamaProducto.actualizarGamaProducto(gamaProducto);
            } else {
                System.out.println("Gama de productos no encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la gama de productos: " + e.getMessage());
        }
    }

    public void eliminarGamaProducto(int idGamaProducto) {
        try {
            GamaProducto gamaProducto = daoGamaProducto.buscarGamaProducto(idGamaProducto);
            if (gamaProducto != null) {
                daoGamaProducto.eliminarGamaProducto(idGamaProducto);
            } else {
                System.out.println("Gama de productos no encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar la gama de productos: " + e.getMessage());
        }
    }

    public void listarGamasProducto() throws Exception {
        List<GamaProducto> todasGamasProducto = daoGamaProducto.listarTodos();
        imprimirLista(todasGamasProducto);
    }

    public void imprimirLista(List<GamaProducto> listaRecibida) {
        for (GamaProducto unitarioGamaProducto : listaRecibida) {
            System.out.println(unitarioGamaProducto.getIdGama() + " - " + unitarioGamaProducto.getGama());
        }
    }
}

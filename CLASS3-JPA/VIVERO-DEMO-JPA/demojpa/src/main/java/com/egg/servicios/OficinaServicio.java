package com.egg.servicios;

import java.util.List;

import com.egg.entidades.Oficina;
import com.egg.persistencia.OficinaDAO;

public class OficinaServicio {

    private final OficinaDAO daoOficina;

    public OficinaServicio() {
        this.daoOficina = new OficinaDAO();
    }

    public void crearOficna(String codigoOficina, String ciudad, String pais,
                            String region, String telefono, String codigoPostal) {
        try {
            Oficina oficinaNueva = new Oficina();

            oficinaNueva.setCodigoOficina(codigoOficina);
            oficinaNueva.setCiudad(ciudad);
            oficinaNueva.setPais(pais);
            oficinaNueva.setRegion(region);
            oficinaNueva.setTelefono(telefono);
            oficinaNueva.setCodigoPostal(codigoPostal);

            daoOficina.guardaOficina(oficinaNueva);

            System.out.println("La oficina se ha guardado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar la oficina: " + e.getMessage());
        }
    }
    
    public Oficina buscarOficina(int id) {
        return daoOficina.buscarOficina(id);
    }

    public void actualizarOficina(int id, String codigodOficina, String ciudad,
                                   String pais, String region, String telefono, String codigoPostal) {
        try {
            Oficina oficinaExistente = daoOficina.buscarOficina(id);
            if (oficinaExistente != null) {
                oficinaExistente.setCodigoOficina(codigodOficina);
                oficinaExistente.setCiudad(ciudad);
                oficinaExistente.setPais(pais);
                oficinaExistente.setRegion(region);
                oficinaExistente.setTelefono(telefono);
                oficinaExistente.setCodigoPostal(codigoPostal);
                daoOficina.actualizarOficina(oficinaExistente);
            } else {
                System.out.println("Oficina no encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la oficina: " + e.getMessage());
        }
    }

    public void eliminarOficina(int id) {
        try {
            daoOficina.eliminarOficina(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar la oficina: " + e.getMessage());
        }
    }


    public void listarOficinas() throws Exception {
        List<Oficina> todasOficinas = daoOficina.listarTodas();
        imprimirLista(todasOficinas);
    }

    public void imprimirLista(List<Oficina> listaRecibida) {
        if (listaRecibida != null && !listaRecibida.isEmpty()) {
            for (Oficina unitariaOficina : listaRecibida) {
                System.out.println(unitariaOficina.getCodigoOficina() + " - Ciudad: " 
                                   + unitariaOficina.getCiudad() + " - Pais: " 
                                   + unitariaOficina.getPais());
            }
        } else {
        System.out.println("No hay oficinas disponibles.");
        }
    }

    
}

package com.egg.servicios;

import com.egg.entidades.Cliente;
import com.egg.persistencia.ClienteDAO;

public class ClienteServicio {

    private final ClienteDAO daoCliente;

    public ClienteServicio() {
        this.daoCliente = new ClienteDAO();
    }

    public void guardarCliente(String apellidoContacto, String ciudad, String codigoPostal,
                               String fax, String nombreContacto, String region, String telefono) {
        try {
            Cliente nuevoCliente = new Cliente();

            nuevoCliente.setApellidoContacto(apellidoContacto);
            nuevoCliente.setCiudad(ciudad);
            nuevoCliente.setCodigoPostal(codigoPostal);
            nuevoCliente.setFax(fax);
            nuevoCliente.setNombreContacto(nombreContacto);
            nuevoCliente.setRegion(region);
            nuevoCliente.setTelefono(telefono);

            daoCliente.guardaCliente(nuevoCliente);

            System.out.println("El cliente se ha guardado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - No se guardó el nuevo cliente de manera correcta.");
        }
    }
}

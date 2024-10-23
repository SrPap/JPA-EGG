package com.egg.servicios;

import java.util.List;

import com.egg.entidades.Cliente;
import com.egg.persistencia.ClienteDAO;

public class ClienteServicio {

    private final ClienteDAO daoCliente;

    public ClienteServicio() {
        this.daoCliente = new ClienteDAO();
    }

    public void guardarCliente(String apellidoContacto, String ciudad, String codigoPostal,
                               String fax, String nombreContacto, int codigoCliente, String telefono) {
        try {
            Cliente nuevoCliente = new Cliente();

            nuevoCliente.setApellidoContacto(apellidoContacto);
            nuevoCliente.setCiudad(ciudad);
            nuevoCliente.setCodigoPostal(codigoPostal);
            nuevoCliente.setFax(fax);
            nuevoCliente.setNombreContacto(nombreContacto);
            nuevoCliente.setCodigoCliente(codigoCliente);
            nuevoCliente.setTelefono(telefono);

            daoCliente.guardaCliente(nuevoCliente);

            System.out.println("El cliente se ha guardado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - No se guardó el nuevo cliente de manera correcta.");
        }
    }


    public void listarClientes() throws Exception {
        List<Cliente> todosClientes = daoCliente.listarTodos();
        imprimirLista(todosClientes);
    }

    public void imprimirLista(List<Cliente> listaRecibida) {
        for (Cliente cliente : listaRecibida) {
            System.out.println(cliente.getIdCliente() + " - Cliente:" 
                               + cliente.getNombreCliente() + " - Contacto:"
                               + cliente.getNombreContacto() + " - Ciudad:" 
                               + cliente.getCiudad());
        }
    }

    public void listarClientesPorCiudad(String ciudadRecibida) throws Exception {
        List<Cliente> clientesCiudad = daoCliente.listarClientesPorCiudad(ciudadRecibida);
        imprimirLista(clientesCiudad);
    }
    
    public void listarClientesPorEmpleado(String nombreEmpleado) throws Exception {
        List<Cliente> clientesCiudad = daoCliente.listarClientesPorEmpleado(nombreEmpleado);
        imprimirLista(clientesCiudad);
    }
}

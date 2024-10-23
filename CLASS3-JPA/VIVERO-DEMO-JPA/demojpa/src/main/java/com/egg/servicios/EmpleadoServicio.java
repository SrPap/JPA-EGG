package com.egg.servicios;
//
import java.util.List;

import com.egg.entidades.Empleado;
import com.egg.entidades.Oficina;
import com.egg.persistencia.EmpleadoDAO;

public class EmpleadoServicio {

    private final EmpleadoDAO empleadoDAO;

    public EmpleadoServicio() {
        this.empleadoDAO = new EmpleadoDAO();
    }

    public void crearEmpleado(String apellido, String codigoEmpleado, String extension,
                              String email, Integer idJefe, String nombre, int idOficina) {
        try {
            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setApellido(apellido);
            nuevoEmpleado.setCodigoEmpleado(codigoEmpleado);
            nuevoEmpleado.setExtension(extension);
            nuevoEmpleado.setEmail(email);
            nuevoEmpleado.setIdJefe(idJefe);
            nuevoEmpleado.setNombre(nombre);
            
            Oficina oficina = new Oficina();
            oficina.setIdOficina(idOficina); 
            nuevoEmpleado.setOficina(oficina);

            empleadoDAO.guardaEmpleado(nuevoEmpleado);
        } catch (Exception e) {
            System.out.println("No se pudo guardar el nuevo empleado: " + e.getMessage());
        }
    }

    public Empleado buscarEmpleado(int id) {
        return empleadoDAO.buscarEmpleado(id);
    }

    public void actualizarEmpleado(Empleado empleado) {
        empleadoDAO.actualizarEmpleado(empleado);
    }

    public void actualizarEmpleado(int idEmpleado, String apellido, String codigoEmpleado, String extension, String email, Integer idJefe, String nombre, int idOficina) {
        try {
            Empleado empleado = empleadoDAO.buscarEmpleado(idEmpleado);
            if (empleado != null) {
                empleado.setApellido(apellido);
                empleado.setCodigoEmpleado(codigoEmpleado);
                empleado.setExtension(extension);
                empleado.setEmail(email);
                empleado.setIdJefe(idJefe);
                empleado.setNombre(nombre);
                empleado.setOficina(empleadoDAO.buscarOficina(idOficina));

                empleadoDAO.actualizarEmpleado(empleado);
            } else {
                System.out.println("Empleado no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el empleado: " + e.getMessage());
        }
    }

    public void eliminarEmpleado(int id) {
        empleadoDAO.eliminarEmpleado(id);
    }

    public void listarEmpleados() throws Exception {
        List<Empleado> todosEmpleados = empleadoDAO.listarTodos();
        imprimirLista(todosEmpleados);
    }

    public void imprimirLista(List<Empleado> listaRecibida) {
        for (Empleado empleado : listaRecibida) {
            System.out.println(empleado.getIdEmpleado() + " - " 
                               + empleado.getNombre() + " - " 
                               + empleado.getApellido());
        }
    }

    public void listarEmpleadosPorOficina(int idOficina) throws Exception {
        List<Empleado> empleadosPorOficina = empleadoDAO.listarEmpleadosPorOficina(idOficina);
        imprimirLista(empleadosPorOficina);
    }

    public void listarEmpleadosPorOficina(String codigoOficina) throws Exception {
        List<Empleado> empleadosPorOficina = empleadoDAO.listarEmpleadosPorOficina(codigoOficina);
        imprimirLista(empleadosPorOficina);
    }

    public void listarEmpleadosQueSonJefes() throws Exception {
        List<Empleado> empleadosPorOficina = empleadoDAO.listarEmpleadosQueSonJefes();
        imprimirLista(empleadosPorOficina);
    }

}

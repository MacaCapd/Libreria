package libreria.servicios;

import java.util.ArrayList;
import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.persistencia.Controladora;

public class ClienteService {

    Controladora controladora = new Controladora();

    public Cliente cargarCliente() {

        try {
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Ingrese ID del cliente:");
            String id = leer.next();
            System.out.println("Ingrese el DNI del cliente");
            Long documento = leer.nextLong();
            System.out.println("Ingrese nombre del cliente:");
            String nombre = leer.next();
            System.out.println("Ingrese el apellido del cliente:");
            String apellido = leer.next();
            System.out.println("Ingrese telefono del cliente: ");
            String telefono = leer.next();

            Cliente nuevoCliente = new Cliente(id, documento, nombre, apellido, telefono);

            //se lo paso al metodo CrearCLIENTE para que lo cargue en el mysql
            crearCliente(nuevoCliente);
            //y lo retorno para el metodo PRESTAMO...
            return nuevoCliente;
        } catch (Exception e) {
            throw e;
        }

    }

    public void crearCliente(Cliente cliente) {
        controladora.crearCliente(cliente);
    }

    public void eliminarCliente(String id) {
        controladora.eliminarcliente(id);
    }

    public void editarCliente(Cliente cliente) {
        controladora.editarCliente(cliente);
    }

    public Cliente traerCliente(String id) {
        return controladora.traerCliente(id);
    }

    public ArrayList<Cliente> traerListaClientes() {
        return controladora.traerListaClientes();
    }
}

package libreria.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.Controladora;

public class PrestamoService {

    Controladora controladora = new Controladora();

    public void cargarPrestamo() {

        try {
            Scanner leer = new Scanner(System.in).useDelimiter("\n");

            Prestamo prestamo = new Prestamo();

            System.out.println("Ingrese el ID del prestamo:");
            String id = leer.next();
            prestamo.setId(id);
            System.out.println("Cargando fecha del prestamo...");
            Date fechaPrestamo = new Date(); //tomo la fecha del prestamo como la actual !
            prestamo.setFechaPrestamo(fechaPrestamo);
            System.out.println("Ingrese fecha del devolución:");
            System.out.println("Ingrese el dia:");
            int dia = leer.nextInt();
            System.out.println("Ingrese el mes:");
            int mes = leer.nextInt();
            System.out.println("Ingrese el año:");
            int anio = leer.nextInt();
            Date fechaDevolucion = new Date(anio - 1900, mes - 1, dia); //AÑO-MES-DIA
            prestamo.setFechaDevolucion(fechaDevolucion);

            System.out.println("Ingrese el ID del libro que solicita");
            String idLibro = leer.next();
            LibroService ls = new LibroService();
            Libro libroParaPrestamo = ls.traerLibro(idLibro);

            if (libroParaPrestamo == null) {
                System.out.println("No existe libro para el ID indicado:");
                System.out.println("Ingrese otro ID:");
                //traigo la lista de libros existentes
                ArrayList<Libro> traerListaLibros = ls.traerListaLibros();
                //y la muestro para que el usuario elija un libro
                System.out.println(traerListaLibros);
                idLibro = leer.next();
                libroParaPrestamo = ls.traerLibro(idLibro);
            }

            //acá verifico que queden ejemplares disponibles para prestar
            if (libroParaPrestamo.getEjemplaresRestantes() > 1) {
                prestamo.setLibro(libroParaPrestamo); //seteo

                libroParaPrestamo.setEjemplaresPrestados(libroParaPrestamo.getEjemplaresPrestados() + 1); //aumento cantidad de prestados
                libroParaPrestamo.setEjemplaresRestantes(libroParaPrestamo.getEjemplaresRestantes() - 1); //disminuyo la cantidad disponible
                ls.editarLibro(libroParaPrestamo);//mando el método editar para que me guarde los cambios del libro
            } else {
                System.out.println("No quedan ejemplares del libro solicitado.");
                System.out.println("Elija otro:");
                //traigo la lista de libros existentes
                ArrayList<Libro> traerListaLibros = ls.traerListaLibros();
                //y la muestro para que el usuario elija un libro
                System.out.println(traerListaLibros);
                System.out.println("Ingrese el ID del libro que solicita");
                idLibro = leer.next();
                libroParaPrestamo = ls.traerLibro(idLibro);

                if (libroParaPrestamo.getEjemplaresRestantes() > 1) {
                    prestamo.setLibro(libroParaPrestamo); //seteo
                    libroParaPrestamo.setEjemplaresPrestados(libroParaPrestamo.getEjemplaresPrestados() + 1); //aumento cantidad de prestados
                    libroParaPrestamo.setEjemplaresRestantes(libroParaPrestamo.getEjemplaresRestantes() - 1); //disminuyo la cantidad disponible) {
                    ls.editarLibro(libroParaPrestamo); //mando el método editar para que me guarde los cambios del libro
                }
            }

            System.out.println("Ingrese el cliente:");
            System.out.println("¿El cliente ya está registrado? [SI/NO]");
            String respuesta = leer.next();

            ClienteService cs = new ClienteService();

            if (respuesta.equalsIgnoreCase("si")) {
                System.out.println("Ingrese ID del cliente:");
                String idcliente = leer.next();
                Cliente clienteRegistrado = cs.traerCliente(idcliente);
                prestamo.setCliente(clienteRegistrado);
            } else {
                System.out.println("Cree un nuevo cliente:");
                Cliente clienteNuevo = cs.cargarCliente();
                prestamo.setCliente(clienteNuevo);
            }
            //acá lo mando a crearPrestamo para que lo cargue en el mysql
            crearPrestamo(prestamo);
            System.out.println("SE CARGÓ EL PRESTAMO CORRECTAMENTE...");
        } catch (Exception e) {
        }
    }

    public void crearPrestamo(Prestamo prestamo) {
        controladora.crearPrestamo(prestamo);
    }

    public void eliminarPrestamo(String id) {
        controladora.eliminarPrestamo(id);
    }

    public void editarPrestamo(Prestamo prestamo) {
        controladora.editarPrestamo(prestamo);
    }

    public Prestamo traerPrestamo(String id) {
        return controladora.traerPrestamo(id);
    }

    public ArrayList<Prestamo> traerListaPrestamos() {
        return controladora.traerListaPrestamos();
    }

    public void devolucionLibro() {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Ingrese el ID del prestamo:");
        String idPrestamo = leer.next();

        Prestamo traerPrestamo = traerPrestamo(idPrestamo);

        String idLibro = traerPrestamo.getLibro().getId();

        LibroService ls = new LibroService();
        Libro libroDevuelto = ls.traerLibro(idLibro);

        //acá le resto a los prestados y le sumo a los restantes:
        libroDevuelto.setEjemplaresPrestados(libroDevuelto.getEjemplaresPrestados() - 1);
        libroDevuelto.setEjemplaresRestantes(libroDevuelto.getEjemplaresRestantes() + 1);
        //lo mando al mysql
        ls.editarLibro(libroDevuelto);

        //finalmente llamo al metodo eliminarPrestamo(); para borrar de la tabla el prestamo.
        eliminarPrestamo(idPrestamo);
    }

}

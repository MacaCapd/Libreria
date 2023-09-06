package libreria.servicios;

import java.util.ArrayList;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.persistencia.Controladora;

public class AutorService {

    Controladora controladora = new Controladora();

    public Autor cargarAutor() {
        try {
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Ingrese ID del autor:");
            String ID = leer.next();
            System.out.println("Ingrese nombre del autor:");
            String nombre = leer.next();

            Autor nuevoAutor = new Autor(ID, nombre, Boolean.TRUE);

            //se lo paso al metodo CrearAutor para que lo cargue en el mysql
            crearAutor(nuevoAutor);
            //y lo retorno para el metodo de crearLibro
            return nuevoAutor;
        } catch (Exception e) {
            throw e;
        }
    }

    public void crearAutor(Autor autor) {
        controladora.crearAutor(autor);
    }

    public void eliminarAutor(String id) {
        controladora.eliminarAutor(id);
    }

    public void editarAutor(Autor autor) {
        controladora.editarAutor(autor);
    }

    public Autor traerAutor(String id) {
        return controladora.traerAutor(id);
    }

    public ArrayList<Autor> traerListaAutores() {
        return controladora.traerListaAutores();
    }

    public void darBajaUnAutor(Autor autor) {
        try {
            if (autor.getAlta() == true) {
                autor.setAlta(Boolean.FALSE);
            }
        } catch (Exception e) {
        }
    }

    public void darAltaUnAutor(Autor autor) {
        try {
            if (autor.getAlta() == false) {
                autor.setAlta(Boolean.TRUE);
            }
        } catch (Exception e) {
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    public Autor buscarAutorPorNombre(String nombre) {
        try {
            ArrayList<Autor> traerListaAutores = traerListaAutores();

            Autor autorBuscado = new Autor();

            for (Autor autor : traerListaAutores) {
                if (autor.getNombre().equalsIgnoreCase(nombre)) {
                    autorBuscado = autor;
                    break;
                } else {
                    autorBuscado = null;
                }
            }
            if (autorBuscado==null) {
                System.out.println("No se encontró autor buscado.");
            }
            return autorBuscado;
        } catch (Exception e) {
            throw e;
        }
    }
    ////////////////////////////////////////////////////////////////////////////
}

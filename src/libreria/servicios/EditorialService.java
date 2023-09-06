package libreria.servicios;

import java.util.ArrayList;
import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.Controladora;

public class EditorialService {

    Controladora controladora = new Controladora();

    public Editorial cargarEditorial() {
        try {
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Ingrese ID de la editorial:");
            String ID = leer.next();
            System.out.println("Ingrese nombre de la editorial:");
            String nombre = leer.next();

            Editorial nuevaEditorial = new Editorial(ID, nombre, Boolean.TRUE);

            //se lo paso al metodo CrearAutor para que lo cargue en el mysql
            crearEditorial(nuevaEditorial);
            //y lo retorno para el metodo de crearLibro
            return nuevaEditorial;
        } catch (Exception e) {
            throw e;
        }

    }

    public void crearEditorial(Editorial editorial) {
        controladora.crearEditorial(editorial);
    }

    public void eliminarEditorial(String id) {
        controladora.eliminarEditorial(id);
    }

    public void editarEditorial(Editorial editorial) {
        controladora.editarEditorial(editorial);
    }

    public Editorial traerEditorial(String id) {
        return controladora.traerEditorial(id);
    }

    public ArrayList<Editorial> traerListaEditoriales() {
        return controladora.traerListaEditoriales();
    }

    public void darBajaUnaEditorial(Editorial editorial) {
        try {
            if (editorial.getAlta() == true) {
                editorial.setAlta(Boolean.FALSE);
            }
        } catch (Exception e) {
        }
    }

    public void darAltaUnaEditorial(Editorial editorial) {
        try {
            if (editorial.getAlta() == false) {
                editorial.setAlta(Boolean.TRUE);
            }
        } catch (Exception e) {
        }

    }
}

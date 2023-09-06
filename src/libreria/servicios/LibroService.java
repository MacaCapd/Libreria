package libreria.servicios;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.Controladora;

public class LibroService {

    Controladora controladora = new Controladora();

    public void cargarLibro() {

        try {
            Scanner leer = new Scanner(System.in).useDelimiter("\n");

            Libro nuevoLibro = new Libro();

            System.out.println("Ingrese el ID del libro: ");
            String id = leer.next();
            nuevoLibro.setId(id);
            System.out.println("Ingrese el ISBN del libro:");
            Long isbn = leer.nextLong();
            nuevoLibro.setIsbn(isbn);
            System.out.println("Ingrese el título del libro:");
            String titulo = leer.next();
            nuevoLibro.setTitulo(titulo);
            System.out.println("Ingrese el año del libro: ");
            Integer anio = leer.nextInt();
            nuevoLibro.setAnio(anio);
            System.out.println("Ingrese número de ejemplares:");
            Integer ejemplares = leer.nextInt();
            nuevoLibro.setEjemplares(ejemplares);
            System.out.println("Ingrese número de ejemplares prestados:");
            Integer ejemplaresPrestados = leer.nextInt();
            nuevoLibro.setEjemplaresPrestados(ejemplaresPrestados);
            System.out.println("Ingrese número de ejemplares restantes:");
            Integer ejemplaresRestantes = leer.nextInt();
            nuevoLibro.setEjemplaresRestantes(ejemplaresRestantes);
            //alta:
            nuevoLibro.setAlta(Boolean.TRUE);
            //autor
            System.out.println("Ingrese el Autor del libro:");
            System.out.println("¿El autor ya esta cargado?");
            String rta = leer.next();
            AutorService as = new AutorService();
            if (rta.equalsIgnoreCase("si")) {
                System.out.println("Ingrese ID del autor");
                String idAutor = leer.next();
                Autor autorExistente = as.traerAutor(idAutor);
                nuevoLibro.setAutor(autorExistente);
            } else {
                System.out.println("Crear autor:");
                Autor autorNuevo = as.cargarAutor();
                nuevoLibro.setAutor(autorNuevo);
            }
            //editorial
            System.out.println("Ingrese la Editorial del libro:");
            System.out.println("¿La editorial ya esta cargada?");
            rta = leer.next();
            EditorialService es = new EditorialService();
            if (rta.equalsIgnoreCase("si")) {
                System.out.println("Ingrese ID de la editorial");
                String idEditorial = leer.next();
                Editorial editorialExistente = es.traerEditorial(idEditorial);
                nuevoLibro.setEditorial(editorialExistente);
            } else {
                System.out.println("Crear editorial:");
                Editorial editorialNueva = es.cargarEditorial();
                nuevoLibro.setEditorial(editorialNueva);
            }
            //se lo mando al metodo de crearLibro para que lo cargue en el mysql
            crearLibro(nuevoLibro);
        } catch (Exception e) {
        }

    }

    public void modificarLibro() {

        try {
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Ingrese ID del libro a modificar");
            String id = leer.next();

            Libro libroParaModificar = traerLibro(id); //llamo al método para que me traiga el libro !

            boolean salir = true;

            //acá le doy las opciones al usuario para que elija que quiere modificar especificamente del libro
            do {
                System.out.println("Elija opción a modificar del libro:");
                System.out.println("1) ISBN");
                System.out.println("2) Titulo");
                System.out.println("3) Año");
                System.out.println("4) Ejemplares");
                System.out.println("5) Ejemplares Prestados");
                System.out.println("6) Ejemplares Restantes");
                System.out.println("7) Alta/Baja");
                System.out.println("8) Autor");
                System.out.println("9) Editorial");
                System.out.println("10) SALIR ");

                String opcion = leer.next();

                switch (opcion) {
                    case "1":
                        System.out.println("Ingrese nuevo ISBN:");
                        Long isbn = leer.nextLong();
                        libroParaModificar.setIsbn(isbn); //según la opción que elija se setea la modificacion
                        break;
                    case "2":
                        System.out.println("Ingrese nuevo Titulo:");
                        String titulo = leer.next();
                        libroParaModificar.setTitulo(titulo);
                        break;
                    case "3":
                        System.out.println("Ingrese nuevo año:");
                        Integer anio = leer.nextInt();
                        libroParaModificar.setAnio(anio);
                        break;
                    case "4":
                        System.out.println("Ingrese nueva cantidad de ejemplares:");
                        Integer ejemplares = leer.nextInt();
                        libroParaModificar.setEjemplares(ejemplares);
                        break;
                    case "5":
                        System.out.println("Ingrese nueva cantidad de ejemplares prestados:");
                        Integer ejemplaresPrestados = leer.nextInt();
                        libroParaModificar.setEjemplaresPrestados(ejemplaresPrestados);
                        break;
                    case "6":
                        System.out.println("Ingrese nueva cantidad de ejemplares restantes:");
                        Integer ejemplaresRestantes = leer.nextInt();
                        libroParaModificar.setEjemplaresRestantes(ejemplaresRestantes);
                        break;
                    case "7":
                        System.out.println("Elija:");
                        System.out.println("A) Dar de baja");
                        System.out.println("B) Dar de alta");
                        String respuesta = leer.next();
                        if (respuesta.equalsIgnoreCase("a")) {
                            darBajaUnLibro(libroParaModificar);
                        } else {
                            darAltaUnLibro(libroParaModificar);
                        }
                        break;
                    case "8":
                        System.out.println("Ingrese nuevo AUTOR:");
                        AutorService as = new AutorService();
                        Autor autorNuevo = as.cargarAutor();
                        libroParaModificar.setAutor(autorNuevo);
                        break;
                    case "9":
                        System.out.println("Ingrese nueva EDITORIAL:");
                        EditorialService es = new EditorialService();
                        Editorial editorialNueva = es.cargarEditorial();
                        libroParaModificar.setEditorial(editorialNueva);
                        break;
                    case "10":
                        salir = false;
                }

            } while (salir);
            //le mando a editarLibro el libro ya modificado para cargar en mysql
            editarLibro(libroParaModificar);
        } catch (Exception e) {
        }

    }

    public void crearLibro(Libro libro) {
        controladora.crearLibro(libro);
    }

    public void eliminarLibro(String id) {
        controladora.eliminarLibro(id);
    }

    public void editarLibro(Libro libro) {
        controladora.editarLibro(libro);
    }

    public Libro traerLibro(String id) {
        return controladora.traerLibro(id);
    }

    public ArrayList<Libro> traerListaLibros() {
        return controladora.traerListaLibros();
    }

    public Libro buscarLibroPorISBN(Long isbn) {
        try {
            ArrayList<Libro> traerListaLibros = traerListaLibros();

            Libro libroPorISBN = new Libro();

            for (Libro libro : traerListaLibros) {
                if (Objects.equals(libro.getIsbn(), isbn)) {
                    libroPorISBN = libro;
                    break;
                } else {
                    libroPorISBN = null;
                }
            }
            if (libroPorISBN == null) {
                System.out.println("No se encontró libro por ISBN.");
            }
            return libroPorISBN;
        } catch (Exception e) {
            throw e;
        }
    }

    public Libro buscarLibroPorTitulo(String nombre) {
        try {
            ArrayList<Libro> traerListaLibros = traerListaLibros();

            Libro libroPorTitulo = new Libro();

            for (Libro libro : traerListaLibros) {
                if (libro.getTitulo().equalsIgnoreCase(nombre)) {
                    libroPorTitulo = libro;
                    break;
                } else {
                    libroPorTitulo = null;
                }
            }
            if (libroPorTitulo == null) {
                System.out.println("No se encontró libro por título.");
            }
            return libroPorTitulo;
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Libro> buscarLibroPorNombreAutor(String nombre) throws Exception {
        try {
            //traigo todos los libros
            ArrayList<Libro> traerListaLibros = traerListaLibros();
            //creo un nuevo arraylist para cargarlos libros del autor que busca el usuario
            ArrayList<Libro> librosPorNombreAutor = new ArrayList();
            //registro la lista de libro
            for (Libro libro : traerListaLibros) {
                if (libro.getAutor().getNombre().equalsIgnoreCase(nombre)) {
                    librosPorNombreAutor.add(libro); //si encuentra el autor lo guarda en el arraylist
                }
            }
            //si no encuentra nada y el arraylist queda vacío le aviso al usuario
            if (librosPorNombreAutor.isEmpty()) {
                System.out.println("No se enconró ningún libro para el autor especificado.");
            }
            return librosPorNombreAutor;
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Libro> buscarLibroPorNombreEditorial(String nombre) {
        try {
            ArrayList<Libro> traerListaLibros = traerListaLibros();

            ArrayList<Libro> librosPorNombreEditorial = new ArrayList<Libro>();

            for (Libro libro : traerListaLibros) {
                if (libro.getEditorial().getNombre().equalsIgnoreCase(nombre)) {
                    librosPorNombreEditorial.add(libro);
                }
            }
            if (librosPorNombreEditorial.isEmpty()) {
                System.out.println("No se encontraron libros para la editorial indicada.");
            }
            return librosPorNombreEditorial;
        } catch (Exception e) {
            throw e;
        }
    }

    public void darBajaUnLibro(Libro libro) {
        try {
            if (libro.getAlta() == true) {
                libro.setAlta(Boolean.FALSE);
            }
        } catch (Exception e) {
        }

    }

    public void darAltaUnLibro(Libro libro) {
        try {
            if (libro.getAlta() == false) {
                libro.setAlta(Boolean.TRUE);
            }
        } catch (Exception e) {
        }
    }
}

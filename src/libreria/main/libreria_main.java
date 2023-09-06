package libreria.main;

//import libreria.persistencia.Controladora;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Cliente;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.servicios.AutorService;
import libreria.servicios.ClienteService;
import libreria.servicios.EditorialService;
import libreria.servicios.LibroService;
import libreria.servicios.PrestamoService;

public class libreria_main {

    public static void main(String[] args) throws Exception {

        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        //Controladora control = new Controladora();
        AutorService as = new AutorService();
        EditorialService es = new EditorialService();
        LibroService ls = new LibroService();
        ///////////////////////////////////////////////////////////
        ClienteService cs = new ClienteService();
        PrestamoService ps = new PrestamoService();

        //Creamos los objetos manualmente
        Autor autor = new Autor("12", "Jane Austen", Boolean.TRUE);

        Editorial editorial = new Editorial("2", "Editorial Unica", Boolean.TRUE);

        Libro libro = new Libro("2", 123123123l, "Orgullo y prejuicio", 1813, 100, 25, 75, Boolean.TRUE, autor, editorial);

//        es.crearEditorial(editorial);
//        as.crearAutor(autor);
//        ls.crearLibro(libro);
        
        Cliente cliente = new Cliente("1", 38885003l, "Macarena", "Capdevila", "3549432221");
//        cs.crearCliente(cliente);
        //Crear el LIBRO mediante el usuario:        
        //ls.cargarLibro();
        
        
        Libro traerLibro = ls.traerLibro("1");
        System.out.println(traerLibro);
        
        
        
        //ACTIVIDADES:
//        Autor buscarAutorPorNombre = as.buscarAutorPorNombre("Pablo");
//        System.out.println("Autor por nombre: " + buscarAutorPorNombre);
//        
//        System.out.println("-----------");
//        
//        ArrayList<Autor> traerListaAutores = as.traerListaAutores();
//        System.out.println("Lista de autores: " + traerListaAutores);
//        
//        System.out.println("-----------------");
//        
//        Long isbn = 12345678l;
//        Libro buscarLibroPorISBN = ls.buscarLibroPorISBN(isbn);
//        System.out.println("Libro por isbn " + buscarLibroPorISBN);
//        System.out.println("-------------");
//        
//        ArrayList<Libro> buscarLibroPorNombreAutor = ls.buscarLibroPorNombreAutor("Jane Austen");
//        System.out.println("Libro por autor: " );
//        for (Libro libro1 : buscarLibroPorNombreAutor) {
//            System.out.println(libro1);
//        }
//       
//        System.out.println("----------");
//       
//        Libro buscarLibroPorTitulo = ls.buscarLibroPorTitulo("El principito");
//        System.out.println("Libro por nombre:" + buscarLibroPorTitulo);
//        
//        System.out.println("------------");
        //ls.darAltaUnLibro(traerLibro);
        //System.out.println(libro.toString());
        //PARA MODIFICAR UN LIBRO: 
        //ls.modificarLibro();
//        ps.cargarPrestamo();
//        ArrayList<Prestamo> traerListaPrestamos = ps.traerListaPrestamos();
//        System.out.println(traerListaPrestamos);
        //ps.cargarPrestamo();
        //ps.devolucionLibro();
        
        
        
    }
}

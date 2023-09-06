package libreria.persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import libreria.entidades.Autor;
import libreria.entidades.Cliente;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.exceptions.NonexistentEntityException;

//ControladoraPersistencia
public class Controladora {

    LibroJpaController LibroJpa = new LibroJpaController();
    AutorJpaController AutorJpa = new AutorJpaController();
    EditorialJpaController EditorialJpa = new EditorialJpaController();
    ////////////////////////////////////////////////////////////////////////////
    ClienteJpaController ClienteJpa = new ClienteJpaController();
    PrestamoJpaController PrestamoJpa = new PrestamoJpaController();

    //---------------------------------------------------------------------------LIBRO
    public void crearLibro(Libro libro) {
        LibroJpa.create(libro);
    }

    public void eliminarLibro(String id) {
        try {
            LibroJpa.destroy(id);
        } catch (libreria.entidades.exceptions.NonexistentEntityException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarLibro(Libro libro) {
        try {
            LibroJpa.edit(libro);
        } catch (Exception ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Libro traerLibro(String id) {
        return LibroJpa.findLibro(id);
    }

    public ArrayList<Libro> traerListaLibros() {
        List<Libro> libros = LibroJpa.findLibroEntities();
        ArrayList<Libro> listaLibros = new ArrayList<Libro>(libros);
        return listaLibros;
    }

    //---------------------------------------------------------------------------AUTOR
    public void crearAutor(Autor autor) {
        AutorJpa.create(autor);
    }

    public void eliminarAutor(String id) {
        try {
            AutorJpa.destroy(id);
        } catch (libreria.entidades.exceptions.NonexistentEntityException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarAutor(Autor autor) {
        try {
            AutorJpa.edit(autor);
        } catch (Exception ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Autor traerAutor(String id) {
        return AutorJpa.findAutor(id);
    }

    public ArrayList<Autor> traerListaAutores() {
        List<Autor> autores = AutorJpa.findAutorEntities();
        ArrayList<Autor> listaAutores = new ArrayList<Autor>(autores);
        return listaAutores;
    }

    //---------------------------------------------------------------------------EDITORIAL
    public void crearEditorial(Editorial editorial) {
        EditorialJpa.create(editorial);
    }

    public void eliminarEditorial(String id) {
        try {
            EditorialJpa.destroy(id);
        } catch (libreria.entidades.exceptions.NonexistentEntityException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarEditorial(Editorial editorial) {
        try {
            EditorialJpa.edit(editorial);
        } catch (Exception ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Editorial traerEditorial(String id) {
        return EditorialJpa.findEditorial(id);
    }

    public ArrayList<Editorial> traerListaEditoriales() {
        List<Editorial> editoriales = EditorialJpa.findEditorialEntities();
        ArrayList<Editorial> listaEditoriales = new ArrayList<Editorial>(editoriales);
        return listaEditoriales;
    }

    //---------------------------------------------------------------------------CLIENTE
    public void crearCliente(Cliente cliente) {
        ClienteJpa.create(cliente);
    }

    public void eliminarcliente(String id) {
        try {
            ClienteJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarCliente(Cliente cliente) {
        try {
            ClienteJpa.edit(cliente);
        } catch (Exception ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente traerCliente(String id) {       
        return ClienteJpa.findCliente(id);
    }

    public ArrayList<Cliente> traerListaClientes() {
        List<Cliente> clientes = ClienteJpa.findClienteEntities();
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>(clientes);
        return listaClientes;
    }
    
    //---------------------------------------------------------------------------PRESTAMO

    public void crearPrestamo(Prestamo prestamo) {
        PrestamoJpa.create(prestamo);
    }

    public void eliminarPrestamo(String id) {
        try {
            PrestamoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarPrestamo(Prestamo prestamo) {
        try {
            PrestamoJpa.edit(prestamo);
        } catch (Exception ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Prestamo traerPrestamo(String id) {
        return PrestamoJpa.findPrestamo(id);
    }

    public ArrayList<Prestamo> traerListaPrestamos() {
        List<Prestamo> prestamos = PrestamoJpa.findPrestamoEntities();
        ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>(prestamos);
        return listaPrestamos;
    }

}

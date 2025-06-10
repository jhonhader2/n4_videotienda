/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: InterfazVideotienda.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_videotienda
 * Autor: Katalina Marcos - Diciembre 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.videotienda.interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import uniandes.cupi2.videotienda.mundo.Cliente;
import uniandes.cupi2.videotienda.mundo.Pelicula;
import uniandes.cupi2.videotienda.mundo.VideoTienda;

/**
 * Ventana principal de la aplicación de la videotienda
 */
public class InterfazVideotienda extends JFrame {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Tarifa inicial de la videotienda
     */
    private static final int TARIFA_INICIAL = 5000;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Videotienda
     */
    private VideoTienda videotienda;

    /**
     * Panel para el manejo de los clientes
     */
    private PanelClientes panelClientes;

    /**
     * Panel para el manejo de las películas
     */
    private PanelPeliculas panelPeliculas;

    /**
     * Panel para el manejo de la tarifa
     */
    private PanelTarifa panelTarifa;

    /**
     * Panel para el manejo de las extensiones
     */
    private PanelOpciones panelOpciones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea la interfaz para una videotienda <br>
     * <b>Post: </b> Se crea la nueva interfaz con los paneles en los tabs
     * correspondientes
     */
    public InterfazVideotienda() {
        setLayout(new BorderLayout());

        panelTarifa = new PanelTarifa(this);
        panelTarifa.asignarTarifa(TARIFA_INICIAL);
        add(panelTarifa, BorderLayout.NORTH);
        panelPeliculas = new PanelPeliculas(this);
        panelClientes = new PanelClientes(this);
        JPanel panelMedio = new JPanel(new BorderLayout());
        panelMedio.add(panelPeliculas, BorderLayout.CENTER);
        panelMedio.add(panelClientes, BorderLayout.SOUTH);
        add(panelMedio, BorderLayout.CENTER);
        panelOpciones = new PanelOpciones(this);
        add(panelOpciones, BorderLayout.SOUTH);

        // Inicializa la videotienda con valor diario por defecto
        videotienda = new VideoTienda(TARIFA_INICIAL);
        String archivo = "./data/peliculas.txt";
        try {
            videotienda.cargarPeliculas(archivo);
            panelPeliculas.actualizarPeliculas(videotienda.darCatalogo());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo cargar la información de las películas del archivo " + archivo, "Videotienda UniAndes",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        pack();
        setTitle("Videotienda UniAndes");
        setResizable(true);
        centrarVentana(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia la tarifa de alquiler de la videotienda
     * 
     * @param tarifa Nueva tarifa.
     */
    public void cambiarTarifa(int tarifa) {
        try {
            videotienda.modificarTarifa(tarifa);
            JOptionPane.showMessageDialog(this, "La tarifa fue actualizada con éxito", "Cambio de Tarifa",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Cambio de Tarifa", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Retorna el catálogo de películas
     * 
     * @return catálogo de películas de la videotienda
     */
    public ArrayList<Pelicula> darCatalogo() {
        return videotienda.darCatalogo();
    }

    /**
     * Busca y muestra la información de la película.
     * 
     * @param titulo Título de la película. titulo != null.
     */
    public void mostrarPelicula(String titulo) {
        Pelicula p = videotienda.buscarPelicula(titulo);
        if (p == null)
            JOptionPane.showMessageDialog(this, "La película no existe", "Ver Información Película",
                    JOptionPane.ERROR_MESSAGE);
        DialogoPelicula dialogo = new DialogoPelicula(p);
        centrarVentana(dialogo);
        dialogo.setVisible(true);
    }

    /**
     * Agrega una copia a la película del título dado
     * 
     * @param titulo Título de la película. titulo != null.
     */
    public void agregarCopia(String titulo) {
        try {
            videotienda.agregarCopiaPelicula(titulo);
            Pelicula p = videotienda.buscarPelicula(titulo);
            JOptionPane.showMessageDialog(this,
                    "La película \"" + titulo + "\" tiene " + p.darTotalCopias() + " copias", "Agregar Copia",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Agregar Copia", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Busca y muestra la información de un cliente.
     * 
     * @param cedula Cédula del cliente a mostrar. cedula != null.
     */
    public void mostrarCliente(String cedula) {
        Cliente c = videotienda.buscarCliente(cedula);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "El cliente no está registrado", "Ver Información Cliente",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        DialogoConsultaCliente dialogo = new DialogoConsultaCliente(c, this);
        centrarVentana(dialogo);
        dialogo.setVisible(true);
    }

    /**
     * Inicia el proceso de registro de un cliente
     */
    public void afiliarCliente() {
        DialogoRegistroCliente dialogo = new DialogoRegistroCliente(this);
        centrarVentana(dialogo);
        dialogo.setVisible(true);
    }

    /**
     * Afilia un nuevo cliente a la videotienda.
     * 
     * @param nombre    Nombre del cliente. nombre != null.
     * @param cedula    Cédula del cliente. cedula != null.
     * @param direccion Dirección del cliente. direccion != null.
     * @param saldo     Saldo inicial del cliente. saldo >0.
     */
    public void afiliarCliente(String nombre, String cedula, String direccion, int saldo) {
        try {
            videotienda.afiliarCliente(cedula, nombre, direccion);
            videotienda.cargarSaldoCliente(cedula, saldo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Afiliación de Cliente", JOptionPane.ERROR_MESSAGE);
            return;
        }

        panelClientes.actualizarClientes(videotienda.darListaClientes());
    }

    /**
     * Retorna la lista de clientes de la videotienda
     * 
     * @return ArrayList con los clientes de la videotienda
     */
    public ArrayList<Cliente> darListaClientes() {
        return videotienda.darListaClientes();
    }

    /**
     * Recarga el saldo del cliente con la cantidad dada
     * 
     * @param cedula  Cédula del cliente. cedula != null.
     * @param recarga Monto de la recarga.
     */
    public void cargarSaldo(String cedula, int recarga) {
        try {
            videotienda.cargarSaldoCliente(cedula, recarga);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Recarga Saldo Cliente", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    /**
     * Inicia el alquiler de una película
     */
    public void alquilarPelicula() {
        String pelicula = panelPeliculas.darPeliculaSeleccionada();
        if (pelicula == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una película", "Alquilar Película",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String cliente = panelClientes.darClienteSeleccionado();
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente", "Alquilar Película",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int copia = videotienda.alquilarPelicula(pelicula, cliente);
            JOptionPane.showMessageDialog(this, "Se alquiló la copia " + copia + " de la película \"" + pelicula + "\"",
                    "Alquilar Película", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Alquilar Película", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Devuelve la copia alquilada por un cliente
     * 
     * @param cedula   Cédula del cliente. cliente != null.
     * @param pelicula Título de la película a devolver. pelicula != null.
     * @param copia    Número de la copia a devolver.
     */
    public void devolverCopia(String cedula, String pelicula, int copia) {
        try {
            videotienda.devolverCopia(pelicula, copia, cedula);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Devolver Película", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Centra una ventana en la pantalla
     * 
     * @param ventana La ventana que se va a centrar. ventana != null.
     */
    private void centrarVentana(Component ventana) {
        Dimension dPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dVentana = ventana.getSize();

        int xEsquina = (dPantalla.width / 2) - (dVentana.width / 2);
        int yEsquina = (dPantalla.height / 2) - (dVentana.height / 2);

        ventana.setLocation(xEsquina, yEsquina);
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método de extensión 1
     */
    public void reqFuncOpcion1() {
        String respuesta = videotienda.metodo1();
        JOptionPane.showMessageDialog(this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método de extensión 2
     */
    public void reqFuncOpcion2() {
        String respuesta = videotienda.metodo2();
        JOptionPane.showMessageDialog(this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    // -----------------------------------------------------------------
    // Ejecución
    // -----------------------------------------------------------------
    /**
     * Método para la ejecución del programa
     * 
     * @param args Argumentos para la ejecución. No se requiere ninguno.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Ignora el look & feel
        }
        InterfazVideotienda i = new InterfazVideotienda();
        i.setVisible(true);
    }

}

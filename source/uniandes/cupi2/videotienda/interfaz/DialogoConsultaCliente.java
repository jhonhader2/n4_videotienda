/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: DialogoConsultaCliente.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import uniandes.cupi2.videotienda.mundo.Cliente;

/**
 * Diálogo para mostrar los datos de un cliente
 */
public class DialogoConsultaCliente extends JDialog {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazVideotienda ventanaPrincipal;

    /**
     * Cliente a mostrar en el panel
     */
    private Cliente cliente;

    /**
     * Panel de datos del cliente
     */
    private PanelDatosCliente panelDatos;

    /**
     * Panel con los datos de las películas alquiladas del cliente
     */
    private PanelAlquiladasCliente panelAlquiladas;

    /**
     * Panel con las opciones del diálogo
     */
    private PanelBotonesCliente panelBotones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Crea el diálogo para mostrar la información del cliente dado
     * 
     * @param elCliente Cliente a mostrar. elCliente != null.
     * @param ventana   Ventana principal de la aplicación. ventana != null.
     */
    public DialogoConsultaCliente(Cliente elCliente, InterfazVideotienda ventana) {
        cliente = elCliente;
        ventanaPrincipal = ventana;

        setLayout(new BorderLayout());
        panelDatos = new PanelDatosCliente(this);
        add(panelDatos, BorderLayout.NORTH);
        panelAlquiladas = new PanelAlquiladasCliente(this);
        panelAlquiladas.actualizarAlquiladas(cliente.darAlquiladas());
        add(panelAlquiladas, BorderLayout.CENTER);
        panelBotones = new PanelBotonesCliente(this);
        add(panelBotones, BorderLayout.SOUTH);

        pack();
        setTitle("Información del Cliente");
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del cliente
     * 
     * @return nombre
     */
    public String darNombre() {
        return cliente.darNombre();
    }

    /**
     * Retorna la cédula del cliente
     * 
     * @return cédula
     */
    public String darCedula() {
        return cliente.darCedula();
    }

    /**
     * Retorna la dirección del cliente
     * 
     * @return dirección
     */
    public String darDireccion() {
        return cliente.darDireccion();
    }

    /**
     * Retorna el saldo del cliente
     * 
     * @return saldo
     */
    public int darSaldo() {
        return cliente.darSaldo();
    }

    /**
     * incrementa el saldo del cliente
     * 
     * @param incremento para el saldo
     */
    public void incrementarSaldo(int incremento) {
        ventanaPrincipal.cargarSaldo(cliente.darCedula(), incremento);
    }

    /**
     * Devuelve la película seleccionada
     * 
     * @param pelicula Título de la película. pelicula != null.
     * @param copia    Copia de la película.
     */
    public void devolver(String pelicula, int copia) {
        try {
            ventanaPrincipal.devolverCopia(cliente.darCedula(), pelicula, copia);
            panelAlquiladas.actualizarAlquiladas(cliente.darAlquiladas());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Devolver Película", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Sale del diálogo
     */
    public void aceptar() {
        dispose();
    }
}

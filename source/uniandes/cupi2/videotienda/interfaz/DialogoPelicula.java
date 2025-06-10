/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: DialogoPelicula.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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

import uniandes.cupi2.videotienda.mundo.Pelicula;

/**
 * Diálogo para mostrar los datos de una película
 */
public class DialogoPelicula extends JDialog {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Película a mostrar en el panel
     */
    private Pelicula pelicula;

    /**
     * Panel de datos de la película
     */
    private PanelDatosPelicula panelDatos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Crea el diálogo para mostrar la información de la película dada
     * 
     * @param laPelicula Película a mostrar. laPelicula != null.
     */
    public DialogoPelicula(Pelicula laPelicula) {
        pelicula = laPelicula;
        panelDatos = new PanelDatosPelicula(this);
        setLayout(new BorderLayout());
        add(panelDatos, BorderLayout.CENTER);
        pack();
        setTitle("Información de la Película");
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el título de la película
     * 
     * @return título de película
     */
    public String darTitulo() {
        return pelicula.darTitulo();
    }

    /**
     * Retorna el número de copias disponibles de la película
     * 
     * @return número de copias disponibles
     */
    public int darNumeroDisponibles() {
        return pelicula.darTotalCopias();
    }

    /**
     * Retorna el número de copias prestadas de la película
     * 
     * @return número de copias prestadas
     */
    public int darNumeroPrestadas() {
        return pelicula.darTotalCopias() - pelicula.darNumeroDisponibles();
    }

    /**
     * Sale del diálogo
     */
    public void aceptar() {
        dispose();
    }
}

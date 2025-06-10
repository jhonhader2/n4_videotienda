/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelPeliculas.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uniandes.cupi2.videotienda.mundo.Pelicula;

/**
 * Panel para el manejo de las películas
 */
public class PanelPeliculas extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando buscar película
     */
    private static final String BUSCAR = "buscar";

    /**
     * Comando agregar copia de película
     */
    private static final String AGREGAR_COPIA = "agregar_copia";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazVideotienda ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Lista de películas
     */
    private JList<String> listaPeliculas;

    /**
     * Botón para buscar una película
     */
    private JButton botonBuscar;

    /**
     * Botón para agregar una copia de una película
     */
    private JButton botonCopia;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel para el manejo de las películas
     * 
     * @param ventana Ventana principal de la aplicación. ventana != null.
     */
    public PanelPeliculas(InterfazVideotienda ventana) {
        ventanaPrincipal = ventana;

        setBorder(BorderFactory.createTitledBorder("Catálogo de Películas"));
        setLayout(new BorderLayout());

        listaPeliculas = new JList<String>();
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(listaPeliculas);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll, BorderLayout.CENTER);

        botonBuscar = new JButton();
        botonBuscar.setText("Ver");
        botonBuscar.setActionCommand(BUSCAR);
        botonBuscar.addActionListener(this);

        botonCopia = new JButton();
        botonCopia.setText("Agregar Copia");
        botonCopia.setActionCommand(AGREGAR_COPIA);
        botonCopia.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(botonBuscar);
        panel.add(botonCopia);
        add(panel, BorderLayout.SOUTH);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el listado de películas
     * 
     * @param peliculas Listado de las películas. peliculas != null.
     */
    public void actualizarPeliculas(ArrayList<Pelicula> peliculas) {
        String[] lista = new String[peliculas.size()];

        for (int i = 0; i < peliculas.size(); i++)
            lista[i] = ((Pelicula) peliculas.get(i)).darTitulo();

        listaPeliculas.removeAll();
        listaPeliculas.setListData(lista);
    }

    /**
     * Retorna el título de la película seleccionada en la lista
     * 
     * @return título de la película seleccionada. Si no hay ninguna seleccionada
     *         retorna null.
     */
    public String darPeliculaSeleccionada() {
        int p = listaPeliculas.getSelectedIndex();
        if (p < 0)
            return null;
        return (String) listaPeliculas.getSelectedValue();
    }

    /**
     * Responde a los eventos de los botones del panel
     * 
     * @param evento Evento generado por un botán. evento != null.
     */
    public void actionPerformed(ActionEvent evento) {
        String comando = evento.getActionCommand();
        String titulo = darPeliculaSeleccionada();
        if (comando.equals(BUSCAR)) {
            if (titulo == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una película", "Mostrar Pelicula",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            ventanaPrincipal.mostrarPelicula(titulo);
        } else if (comando.equals(AGREGAR_COPIA)) {
            if (titulo == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una película", "Agregar Copia Pelicula",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            ventanaPrincipal.agregarCopia(titulo);
        }
    }
}

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelClientes.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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

import uniandes.cupi2.videotienda.mundo.Cliente;

/**
 * Panel para el manejo de los clientes
 */
public class PanelClientes extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando buscar cliente
     */
    private static final String BUSCAR = "buscar";

    /**
     * Comando alquilar una película a un cliente
     */
    private static final String ALQUILAR = "alquilar";

    /**
     * Comando afiliar un cliente
     */
    private static final String AFILIAR = "afiliar";

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
     * Lista de clientes
     */
    private JList<String> listaClientes;

    /**
     * Botón para buscar un cliente
     */
    private JButton botonBuscar;

    /**
     * Botón para alquilar una película a un cliente
     */
    private JButton botonAlquilar;

    /**
     * Botón para afiliar un cliente
     */
    private JButton botonAfiliar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel para el manejo de clientes
     * 
     * @param ventana Ventana principal de la aplicación. ventana != null.
     */
    public PanelClientes(InterfazVideotienda ventana) {
        ventanaPrincipal = ventana;

        setBorder(BorderFactory.createTitledBorder("Listado de Clientes"));
        setLayout(new BorderLayout());

        listaClientes = new JList<String>();
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(listaClientes);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll, BorderLayout.CENTER);

        botonBuscar = new JButton();
        botonBuscar.setText("Ver");
        botonBuscar.setActionCommand(BUSCAR);
        botonBuscar.addActionListener(this);

        botonAfiliar = new JButton();
        botonAfiliar.setText("Afiliar");
        botonAfiliar.setActionCommand(AFILIAR);
        botonAfiliar.addActionListener(this);

        botonAlquilar = new JButton();
        botonAlquilar.setText("Alquilar");
        botonAlquilar.setActionCommand(ALQUILAR);
        botonAlquilar.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(botonBuscar);
        panel.add(botonAfiliar);
        panel.add(botonAlquilar);
        add(panel, BorderLayout.SOUTH);

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el listado de clientes
     * 
     * @param clientes Listado de clientes. clientes != null.
     */
    public void actualizarClientes(ArrayList<Cliente> clientes) {
        String[] lista = new String[clientes.size()];
        Cliente c;

        for (int i = 0; i < clientes.size(); i++) {
            c = (Cliente) clientes.get(i);
            lista[i] = (c.darCedula() + "-" + c.darNombre());
        }

        listaClientes.removeAll();
        listaClientes.setListData(lista);
    }

    /**
     * Retorna la cdula del cliente seleccionado en la lista
     * 
     * @return cdula del cliente seleccionado. Si no hay ninguno seleccionado
     *         retorna null.
     */
    public String darClienteSeleccionado() {
        int c = listaClientes.getSelectedIndex();
        if (c < 0)
            return null;
        String cedula = (String) listaClientes.getSelectedValue();
        return cedula.substring(0, cedula.indexOf("-"));
    }

    /**
     * Responde a los eventos de los botones del panel
     * 
     * @param evento Evento generado por un botn. evento != null.
     */
    public void actionPerformed(ActionEvent evento) {
        String comando = evento.getActionCommand();
        String cedula = darClienteSeleccionado();

        if (comando.equals(BUSCAR)) {
            if (cedula == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente", "Mostrar Cliente",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            ventanaPrincipal.mostrarCliente(cedula);
        } else if (comando.equals(AFILIAR)) {
            ventanaPrincipal.afiliarCliente();
        } else if (comando.equals(ALQUILAR)) {
            ventanaPrincipal.alquilarPelicula();
        }
    }
}

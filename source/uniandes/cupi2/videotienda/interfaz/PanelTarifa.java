/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelTarifa.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel para el manejo de la tarifa de la videotienda
 */
public class PanelTarifa extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando de cambiar Tarifa
     */
    public final static String CAMBIAR = "CAMBIAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazVideotienda ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta tarifa
     */
    private JLabel etiquetaTarifa;

    /**
     * Campo para la tarifa
     */
    private JTextField campoTarifa;

    /**
     * Botón para cambiar la tarifa
     */
    private JButton botonTarifa;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea el panel para el manejo de la tarifa
     * 
     * @param interfaz Ventana principal de la aplicación. interfaz != null.
     */
    public PanelTarifa(InterfazVideotienda interfaz) {
        ventanaPrincipal = interfaz;

        setBorder(BorderFactory.createTitledBorder("Tarifa de Alquiler"));

        etiquetaTarifa = new JLabel("Valor de tarifa");
        add(etiquetaTarifa);

        campoTarifa = new JTextField(10);
        add(campoTarifa);

        botonTarifa = new JButton();
        botonTarifa.setText("Cambiar");
        botonTarifa.setActionCommand(CAMBIAR);
        botonTarifa.addActionListener(this);
        add(botonTarifa);
    }

    /**
     * Asigna el valor de la tarifa
     * 
     * @param tarifa Valor de tarifa.
     */
    public void asignarTarifa(int tarifa) {
        campoTarifa.setText("" + tarifa);
    }

    /**
     * Responde a los eventos de los botones del panel
     * 
     * @param evento Evento generado por un botón. evento != null.
     */
    public void actionPerformed(ActionEvent evento) {
        String comando = evento.getActionCommand();

        if (comando.equals(CAMBIAR)) {
            try {
                int tarifa = Integer.parseInt(campoTarifa.getText());
                ventanaPrincipal.cambiarTarifa(tarifa);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La tarifa debe ser un valor entero", "Cambio de Tarifa",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}

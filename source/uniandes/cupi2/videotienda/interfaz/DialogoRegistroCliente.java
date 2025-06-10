/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: DialogoRegistroCliente.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $ 
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

/**
 * Diálogo para registrar un nuevo cliente
 */
public class DialogoRegistroCliente extends JDialog {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazVideotienda ventanaPrincipal;

    /**
     * Panel de datos de la película
     */
    private PanelRegistroCliente panelRegistro;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Crea el diálogo para capturar la información de un nuevo cliente
     * 
     * @param ventana Ventana principal. ventana != null.
     */
    public DialogoRegistroCliente(InterfazVideotienda ventana) {
        ventanaPrincipal = ventana;
        panelRegistro = new PanelRegistroCliente(this);
        setLayout(new BorderLayout());
        add(panelRegistro, BorderLayout.CENTER);
        pack();
        setTitle("Afiliación de Cliente");
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Registra el nuevo cliente
     */
    public void aceptar() {
        String nombre = panelRegistro.darNombre();
        String cedula = panelRegistro.darCedula();
        String direccion = panelRegistro.darDireccion();
        String sSaldo = panelRegistro.darSaldo();
        if (nombre.equals("") || cedula.equals("") || direccion.equals("") || sSaldo.equals("")) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los datos", "Afiliación de Cliente",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int saldo = 0;
        try {
            saldo = Integer.parseInt(sSaldo);
            if (saldo < 0) {
                JOptionPane.showMessageDialog(this, "El saldo inicial debe ser mayor a cero", "Afiliación de Cliente",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "El saldo debe ser un valor entero", "Afiliación de Cliente",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        ventanaPrincipal.afiliarCliente(nombre, cedula, direccion, saldo);
        dispose();
    }

    /**
     * Cancela la operación de afiliación de cliente
     */
    public void cancelar() {
        dispose();
    }
}

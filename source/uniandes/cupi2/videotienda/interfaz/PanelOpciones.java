/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelOpciones.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $  
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
import javax.swing.JPanel;

/**
 * Panel para el manejo de las extensiones del ejercicio
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Constante OPCION_1, Usada para realizar la opción de la extensión 1
     */
    private final static String OPCION_1 = "OPCION_1";

    /**
     * Constante OPCION_2, Usada para realizar la opción de la extensión 2
     */
    private final static String OPCION_2 = "OPCION_2";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazVideotienda ventanaPrincipal;

    //-----------------------------------------------------------------
    // Atributos de Interfaz
    //-----------------------------------------------------------------

    /**
     * Botón para la opción 1
     */
    private JButton botonOpcion1;

    /**
     * Botón para la opción 2
     */
    private JButton botonOpcion2;

    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Crea un nuevo panel con los botones para realizar las extensiones
     * @param interfaz Ventana principal. interfaz != null.
     */
    public PanelOpciones( InterfazVideotienda interfaz )
    {
        ventanaPrincipal = interfaz;

        setBorder( BorderFactory.createTitledBorder( "Extensiones" ) );

        botonOpcion1 = new JButton( );
        botonOpcion1.setText( "Opción 1" );
        botonOpcion1.setActionCommand( OPCION_1 );
        botonOpcion1.addActionListener( this );
        add( botonOpcion1 );

        botonOpcion2 = new JButton( );
        botonOpcion2.setText( "Opción 2" );
        botonOpcion2.setActionCommand( OPCION_2 );
        botonOpcion2.addActionListener( this );
        add( botonOpcion2 );

    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Responde a los eventos de los botones del panel
     * @param evento Evento generado por un botón. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( comando.equals( OPCION_1 ) )
        {
            ventanaPrincipal.reqFuncOpcion1( );
        }
        else if( comando.equals( OPCION_2 ) )
        {
            ventanaPrincipal.reqFuncOpcion2( );
        }
    }
}

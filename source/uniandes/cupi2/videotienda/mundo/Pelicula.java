/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: Pelicula.java,v 1.1 2005/12/16 15:13:33 k-marcos Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_videotienda
 * Autor: Katalina Marcos - Diciembre 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.videotienda.mundo;

import java.util.ArrayList;

/**
 * Esta clase representa una película que se encuentra en la videotienda y
 * de la cual puede haber copias disponibles o prestadas.
 */
public class Pelicula {

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Título de la película
     */
    private String titulo;

    /**
     * Nmero de la siguiente copia a adicionar
     */
    private int codigoSiguienteCopia;

    /**
     * Lista de copias disponibles
     */
    private ArrayList<Copia> disponibles;

    /**
     * Lista de copias prestadas
     */
    private ArrayList<Copia> prestadas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una película de la videotienda con el título dado. <br>
     * <b>post: </b> La película se crea sin copias disponibles ni prestadas.
     * 
     * @param unTitulo Título de la película. unTitulo != null.
     */
    public Pelicula(String unTitulo) {
        titulo = unTitulo;
        disponibles = new ArrayList<Copia>();
        prestadas = new ArrayList<Copia>();
        codigoSiguienteCopia = 1;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Adiciona una nueva copia de la película. <br>
     * <b>post: </b>La lista de películas disponibles tiene una nueva copia.
     * 
     * @return código de la copia creada. código >= 1;
     */
    public int agregarCopia() {
        Copia copia = new Copia(this, codigoSiguienteCopia);
        disponibles.add(copia);
        codigoSiguienteCopia++;
        return copia.darCodigo();
    }

    /**
     * Retorna una copia de película para alquilar si hay disponibles. <br>
     * <b>post: </b> la copia queda en la lista de prestadas.
     * 
     * @return Copia que ha sido alquilada o null si no hay disponibles.
     */
    public Copia alquilarCopia() {
        if (disponibles.size() > 0) {
            Copia copia = disponibles.remove(0);
            prestadas.add(copia);
            return copia;
        }
        return null;
    }

    /**
     * Devuelve una copia de la película y la coloca como disponible. <br>
     * <b>post: </b> regresa la copia a la lista de disponibles, sólo si está
     * prestada.
     * 
     * @param codigoCopia Código de la copia que se quiere devolver.
     * @throws Exception Si la copia a devolver no está prestada.
     */
    public void devolverCopia(int codigoCopia) throws Exception {
        Copia copiaADevolver = null;
        for (Copia c : prestadas) {
            if (c.darCodigo() == codigoCopia) {
                copiaADevolver = c;
                break;
            }
        }
        if (copiaADevolver == null) {
            throw new Exception("La copia " + codigoCopia + " no está prestada");
        }
        prestadas.remove(copiaADevolver);
        disponibles.add(copiaADevolver);
    }

    /**
     * Retorna el título de la película.
     * 
     * @return título de la película.
     */
    public String darTitulo() {
        return titulo;
    }

    /**
     * Retorna la cantidad total de copias que existen de la película en la
     * videotienda
     * 
     * @return entero con la cantidad de copias que existen en la tienda
     */
    public int darTotalCopias() {
        return disponibles.size() + prestadas.size();
    }

    /**
     * Retorna el número de copias disponibles
     * 
     * @return número de copias disponibles
     */
    public int darNumeroDisponibles() {
        return disponibles.size();
    }
}
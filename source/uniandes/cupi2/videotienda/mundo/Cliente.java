package uniandes.cupi2.videotienda.mundo;

import java.util.ArrayList;

/**
 * Representa un cliente de la videotienda.
 * Esta clase mantiene la información personal del cliente y gestiona sus
 * alquileres.
 */
public class Cliente {

    // Atributos

    /**
     * Cédula del cliente
     */
    private String cedula;

    /**
     * Nombre del cliente
     */
    private String nombre;

    /**
     * Dirección del cliente
     */
    private String direccion;

    /**
     * Saldo del cliente
     */
    private int saldo;

    /**
     * Copias alquiladas por el cliente
     */
    private ArrayList<Copia> alquiladas;

    /**
     * Constructor de la clase Cliente
     * 
     * @param laCedula    La cédula del cliente
     * @param elNombre    El nombre del cliente
     * @param laDireccion La dirección del cliente
     */
    public Cliente(String laCedula, String elNombre, String laDireccion) {
        cedula = laCedula;
        nombre = elNombre;
        direccion = laDireccion;
        saldo = 0;
        alquiladas = new ArrayList<Copia>();
    }

    /**
     * Retorna la cédula del cliente
     * 
     * @return La cédula del cliente
     */
    public String darCedula() {
        return cedula;
    }

    /**
     * Retorna el saldo actual del cliente
     * 
     * @return El saldo del cliente
     */
    public int darSaldo() {
        return saldo;
    }

    /**
     * Retorna el nombre del cliente
     * 
     * @return El nombre del cliente
     */
    public String darNombre() {
        return nombre;
    }

    /**
     * Retorna la dirección del cliente
     * 
     * @return La dirección del cliente
     */
    public String darDireccion() {
        return direccion;
    }

    /**
     * Registra una copia como alquilada por el cliente
     * 
     * @param copia La copia que se está alquilando
     */
    public void alquilarCopia(Copia copia) {
        alquiladas.add(copia);
    }

    /**
     * Aumenta el saldo del cliente
     * 
     * @param monto El monto a cargar al saldo
     */
    public void cargarSaldo(int monto) {
        saldo += monto;
    }

    /**
     * Disminuye el saldo del cliente
     * 
     * @param monto El monto a descontar del saldo
     */
    public void descargarSaldo(int monto) {
        saldo -= monto;
    }

    /**
     * Retorna el número de copias que tiene alquiladas el cliente
     * 
     * @return Cantidad de copias alquiladas
     */
    public int darNumeroAlquiladas() {
        return alquiladas.size();
    }

    /**
     * Retorna la lista de copias alquiladas por el cliente
     * 
     * @return Lista de copias alquiladas
     */
    public ArrayList<Copia> darAlquiladas() {
        return alquiladas;
    }

    /**
     * Busca una copia específica en la lista de alquiladas
     * 
     * @param pelicula La película a buscar
     * @param codigo   El código de la copia a buscar
     * @return La copia si está alquilada, null en caso contrario
     */
    public Copia buscarPeliculaAlquilada(String pelicula, int codigo) {
        for (Copia copia : alquiladas) {
            if (copia.darTituloPelicula().equals(pelicula) && copia.darCodigo() == codigo) {
                return copia;
            }
        }
        return null;
    }

    /**
     * Registra la devolución de una copia por parte del cliente
     * 
     * @param pelicula La película de la copia a devolver
     * @param codigo   El código de la copia a devolver
     */
    public void devolverCopia(String pelicula, int codigo) {
        for (int i = 0; i < alquiladas.size(); i++) {
            Copia copia = alquiladas.get(i);
            if (copia.darTituloPelicula().equals(pelicula) && copia.darCodigo() == codigo) {
                alquiladas.remove(i);
                break;
            }
        }
    }
}

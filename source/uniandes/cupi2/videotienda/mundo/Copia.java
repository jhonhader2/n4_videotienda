package uniandes.cupi2.videotienda.mundo;

/**
 * Esta clase representa una copia de una película en la videotienda.
 */
public class Copia {

    /**
     * Película a la que pertenece la copia
     */
    private Pelicula pelicula;

    /**
     * Código único de la copia
     */
    private int codigo;

    /**
     * Crea una nueva copia de una película
     * 
     * @param codigoCopia Código de la copia
     * @param unaPelicula Película a la que pertenece
     */
    public Copia(Pelicula unaPelicula, int codigoCopia) {
        pelicula = unaPelicula;
        codigo = codigoCopia;
    }

    /**
     * Retorna el código de la copia
     * 
     * @return código de la copia
     */
    public int darCodigo() {
        return codigo;
    }

    /**
     * Retorna el título de la película
     * 
     * @return título de la película
     */
    public String darTituloPelicula() {
        return pelicula.darTitulo();
    }

    /**
     * Verifica si la copia es igual a otra copia
     * 
     * @param unaCopia Copia a comparar
     * @return true si la copia es igual a la otra copia, false en caso contrario
     */
    public boolean esIgualA(Copia unaCopia) {
        return codigo == unaCopia.codigo && pelicula.darTitulo().equals(unaCopia.pelicula.darTitulo());
    }

}
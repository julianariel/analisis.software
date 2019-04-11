package libros;

/**
 * Interface utilizada para la impresi�n por pantalla y por archivo.
 * 
 * @author Grupo6.	
 *
 * @param <T>
 */
interface Funcion<T extends Comparable<T>> {
	    void funcion(T dato, Object parametros);
}

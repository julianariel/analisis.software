package libros;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Funciones del menú.
 * 
 * @author Grupo6
 *
 */
public class Main {
	//Funciones del menú.
	
    public static Scanner teclado = new Scanner(System.in);  
    public static PrintStream out = System.out;

    public static void pausar(String mensage) {
    	//Función que pausa hasta recibir una respuesta por teclado.
        out.print(mensage + "\nPresione <ENTER> para continuar . . . ");
        teclado.nextLine();
        out.println();
    }

    /**
     * Función que lee cadenas por teclado.
     * 
     * @param mensaje
     * @author Grupo6
     * 
     * @return
     */
    public static String leer_cadena(String mensaje) {
    	//Función que lee cadenas por teclado.
        out.print(mensaje + ": ");
        return teclado.nextLine();
    }

    /**
     * Función que lee enteros por teclado.
     * 
     * @param mensaje
     * @author Grupo6
     * 
     * @return
     */
    public static int leer_entero(String mensaje) {
    	//Función que lee enteros por teclado.
        try {
            return Integer.parseInt(leer_cadena(mensaje));
        } catch (NumberFormatException e) {
            out.print("N\u00FAmero incorrecto.");
            return leer_entero(mensaje);
        }
    }
}

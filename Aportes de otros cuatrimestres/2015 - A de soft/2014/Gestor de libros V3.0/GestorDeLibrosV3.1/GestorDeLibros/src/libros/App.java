package libros;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class App extends Main {

	// Parámetros de configuración.
	public static String ruta = "libros.tsv";
	public static String username = "grupo6";
	public static String password = "password";

	/**
	 * Gestor de libros.
	 * 
	 * @param args
	 * @author Grupo6
	 */
	public static void main(String[] args) {

		// Verifica compatibilidad con el sistema operativo en que se ejecuta el
		// programa.
		if (!System.getProperties().get("os.name").equals("Linux") && System.console() != null)
			try {
				out = new PrintStream(System.out, true, "CP850");
				teclado = new Scanner(System.in, "CP850");
			} catch (UnsupportedEncodingException e) {

			}

		// Declaración de variables.
		Vector<Libro> vector = new Vector<Libro>();
		int i, n;
		Libro dato = null, libro;
		int[] contador = { 0 };
		int opcion, subopcion;
		String[] campos;

		// Autentica el usuario
		login();
		
		// Baja a memoria(vector) los registros del archivo.
		try {
			Scanner entrada = new Scanner(new FileReader(ruta));

			while (entrada.hasNextLine()) {
				
				campos = entrada.nextLine().split("\t");
				libro = new Libro();
				libro.setISBN(campos[0]);
				libro.setTitulo(campos[1]);
				libro.setAutor(campos[2]);
				libro.setEditorial(campos[3]);
				libro.setEdicion(Integer.parseInt(campos[4]));
				libro.setAnno_de_publicacion(Integer.parseInt(campos[5]));
				vector.add(libro);
			}
			entrada.close();
		} catch (FileNotFoundException e) {	}


		libro = new Libro();

		// Ejectuta el menú.
		do {
			out.println("MEN\u00DA");
			out.println("1.- Altas");
			out.println("2.- Consultas");
			out.println("3.- Actualizaciones");
			out.println("4.- Bajas");
			out.println("5.- Ordenar registros");
			out.println("6.- Listar registros");
			out.println("7.- Salir");

			do {
				opcion = leer_entero("Seleccione una opci\u00F3n");
				if (opcion < 1 || opcion > 7)
					out.println("Opci\u00F3nn no v\u00E1lida.");
			} while (opcion < 1 || opcion > 7);

			out.println();

			if (vector.isEmpty() && opcion != 1 && opcion != 7) {
				pausar("No hay registros.\n");
				continue;
			}

			if (opcion < 5) {

				// Validación del ISBN, debe constar de 13 dígitos de longitud.
				String ISBN = leer_cadena("Ingrese el ISBN del libro");
				while (ISBN.length() != 13)
					ISBN = leer_cadena("ISBN incorrecto. Ingréselo nuevamente:");

				libro.setISBN(ISBN);
				i = vector.indexOf(libro);
				dato = i < 0 ? null : vector.get(i);
				if (dato != null) {
					out.println();
					imprimir.funcion(dato, contador);
				}
			}

			if (opcion == 1 && dato != null)
				out.println("El registro ya existe.");

			else if (opcion >= 2 && opcion <= 4 && dato == null)
				out.println("\nRegistro no encontrado.");

			else
				switch (opcion) {

				// Nuevo registro.
				case 1:
					libro.setTitulo(leer_cadena("Ingrese el titulo"));
					libro.setAutor(leer_cadena("Ingrese el autor"));
					libro.setEditorial(leer_cadena("Ingrese el editorial"));
					libro.setEdicion(leer_entero("Ingrese el edicion"));
					libro.setAnno_de_publicacion(leer_entero("Ingrese el anno de publicacion"));
					vector.add(libro);
					libro = new Libro();
					out.println("\nRegistro agregado correctamente.");
					actualizarArchivo(vector);
					break;
				// Actualización de registro.
				case 3:
					out.println("Men\u00FA de modificaci\u00F3n de campos");
					out.println("1.- titulo");
					out.println("2.- autor");
					out.println("3.- editorial");
					out.println("4.- edicion");
					out.println("5.- a&nacute;o de publicacion");

					do {
						subopcion = leer_entero("Seleccione un n\u00FAmero de campo a modificar");
						if (subopcion < 1 || subopcion > 5)
							out.println("Opci\u00F3n no v\u00E1lida.");
					} while (subopcion < 1 || subopcion > 5);

					switch (subopcion) {
					case 1:
						dato.setTitulo(leer_cadena("Ingrese el nuevo titulo"));
						break;
					case 2:
						dato.setAutor(leer_cadena("Ingrese el nuevo autor"));
						break;
					case 3:
						dato.setEditorial(leer_cadena("Ingrese el nuevo editorial"));
						break;
					case 4:
						dato.setEdicion(leer_entero("Ingrese el nuevo edicion"));
						break;
					case 5:
						dato.setAnno_de_publicacion(leer_entero("Ingrese el nuevo anno de publicacion"));
						break;
					}
					out.println("\nRegistro actualizado correctamente.");
					actualizarArchivo(vector);
					break;
				// Baja de registro.
				case 4:
					vector.remove(dato);
					out.println("Registro borrado correctamente.");
					actualizarArchivo(vector);
					break;
				// Ordenamiento de los registros.
				case 5:
					Collections.sort(vector);
					out.println("Registros ordenados correctamente.");
					break;
				// Impresión de registros.
				case 6:
					n = vector.size();
					contador[0] = 0;
					for (i = 0; i < n; i++)
						imprimir.funcion(vector.get(i), contador);
					out.println("Total de registros: " + contador[0] + ".");
					break;
				}
			if (opcion < 7 && opcion >= 1)
				pausar("");
		} while (opcion != 7);

		actualizarArchivo(vector);
	}

	/**
	 * Grabado de registros en el archivo.
	 * 
	 * @author Grupo6
	 * 
	 * @param vector
	 */
	private static void actualizarArchivo(Vector<Libro> vector) {
		// Grabado de registros en el archivo.
		int i, n;

		try {
			PrintStream salida = new PrintStream(ruta);
			n = vector.size();
			for (i = 0; i < n; i++)
				imprimirEnArchivo.funcion(vector.get(i), salida);
			salida.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error al guardar archivo: " + e.getMessage());
		}
	}

	/**
	 * Validación de usuario y contraseña
	 * 
	 * @author Grupo6
	 */
	private static void login() {
		// Validación de usuario
		String loginUsuario = leer_cadena("Ingrese el nombre usuario");

		while (!loginUsuario.equals(username))
			loginUsuario = leer_cadena("Usuario incorrecto, intente nuevamente");

		// Validación de contraseña
		String loginPassword = leer_cadena("Ingrese la contraseña");

		while (!loginPassword.equals(password))
			loginPassword = leer_cadena("Contraseña incorrecta, intente nuevamente");
	}

	/**
	 * Declaración de la función de impresión por pantalla.
	 * 
	 * @author Grupo6
	 */
	private static Funcion<Libro> imprimir = new Funcion<Libro>() {

		public void funcion(Libro libro, Object parametros) {
			// Implementación de la función declarada en la interface Funcion
			// para imprimir por pantalla.
			out.println(libro);
			int[] contador = (int[]) parametros;
			contador[0]++;
		}
	};

	/**
	 * Declaración de la función de impresión en archivo.
	 * 
	 * @author Grupo6
	 */
	private static Funcion<Libro> imprimirEnArchivo = new Funcion<Libro>() {

		// Declaración de la función de impresión en archivo.

		public void funcion(Libro libro, Object parametros) {
			// Implementación de la función declarada en la interface Funcion
			// para imprimir en archivo.
			PrintStream archivo = (PrintStream) parametros;
			archivo.print(libro.getISBN() + "\t");
			archivo.print(libro.getTitulo() + "\t");
			archivo.print(libro.getAutor() + "\t");
			archivo.print(libro.getEditorial() + "\t");
			archivo.print(libro.getEdicion() + "\t");
			archivo.print(libro.getAnno_de_publicacion() + "\n");
		}
	};
}
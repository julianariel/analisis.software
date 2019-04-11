package triangulo;

import java.util.Scanner;

public class Triangulo {

	private static Scanner entradaEscaner;

	public static void main(String[] args) {

		char[] lados = { 'A', 'B', 'C' };
		String[] cadena = new String[3];
		double[] numeros = new double[3];
		int i = 0;

		entradaEscaner = new Scanner(System.in);

		// Ingreso los valores

		do {
			System.out.println("Ingrese la longitud del lado: " + lados[i]);
			cadena[i] = entradaEscaner.nextLine();

			if (esNumeroValido(cadena[i])) {
				numeros[i] = Double.parseDouble(cadena[i]);
				i++;
			} else {
				System.out.println("No es un numero valido.");
			}

		} while (i < 3);

		validarTriangulo(numeros[0], numeros[1], numeros[2]);
		

	}

	// Funcion para validar que un string es un numero
	private static boolean esNumeroValido(String cadena) {
		try {
			Double valor = Double.parseDouble(cadena);

			if (valor <= 0)
				return false;

		} catch (Exception e) {
			return false;
		}

		return true;
	}

//	Funcion para validar que los valores ingresados forman un triángulo
	private static void validarTriangulo(double a, double b, double c) { //funcion que determina cual es la situacion del triangulo
		if (a >= b + c || b >= c + a || c >= b + a) //si cualquiera de los lados es mayor a la suma de sus otros dos lados
			System.out.println("No puede haber un triangulo con esas medidas"); //Se debe ingresar de nuevo los valores porque no es triangulo
		else if (a == b && b == c) //valido si es equilatero.
			System.out.println("El triangulo es equilatero");
		else if (a != b && b != c && c != a) //valido si es escaleno
			System.out.println("El triangulo es escaleno");
		else								//si no es equilatero ni escaleno es isosceles
			System.out.println("El triangulo es isosceles");
	}

}
package Triangulos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

	Triangulo triangulo = new Triangulo();

	Scanner sc = new Scanner(System.in);
	Boolean isOk = true;

	do {
	    try {
		isOk = true;
		sc = new Scanner(System.in);
		System.out.println("Ingrese el lado 1: ");
		triangulo.setL1(sc.nextInt());
	    } catch (InputMismatchException e) {
		isOk = false;
	    }
	} while (!isOk);

	do {
	    try {
		isOk = true;
		sc = new Scanner(System.in);
		System.out.println("Ingrese el lado 2: ");
		triangulo.setL2(sc.nextInt());
	    } catch (InputMismatchException e) {
		isOk = false;
	    }
	} while (!isOk);

	do {
	    try {
		isOk = true;
		sc = new Scanner(System.in);
		System.out.println("Ingrese el lado 3: ");
		triangulo.setL3(sc.nextInt());
	    } catch (InputMismatchException e) {
		isOk = false;
	    }
	} while (!isOk);

	System.out.println(triangulo.getL1() + ", " + triangulo.getL2() + ", " + triangulo.getL3());

	if (!triangulo.esTriangulo()) {
	    System.out.println("No es posible armar el triangulo con esos lados.");
	    // throw new Exception("No es posible armar el triangulo con esos lados.");
	} else {
	    System.out.println("Triángulo válido");
	}

	if (triangulo.esEquilatero()) {
	    System.out.println("El triángulo es equilatero.");
	} else {
	    if (triangulo.esEscaleno()) {
		System.out.println("El triángulo es escaleno.");
	    } else {
		System.out.println("El triángulo es isóceles.");
	    }
	}
    }
}

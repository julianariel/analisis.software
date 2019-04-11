package Triangulos;

import java.util.Scanner;

public class Main {

public static void main(String[] args) throws Exception {
		
		Triangulo triangulo = new Triangulo();
		
		System.out.println("Ingrese el lado 1: ");
		Scanner sc = new Scanner(System.in);
		triangulo.setL1(sc.nextInt());
		
		System.out.println("Ingrese el lado 2: ");
		sc = new Scanner(System.in);
		triangulo.setL2(sc.nextInt());
		
		System.out.println("Ingrese el lado 3: ");
		sc = new Scanner(System.in);
		triangulo.setL3(sc.nextInt());

		System.out.println(triangulo.getL1() + ", " + triangulo.getL2() + ", " + triangulo.getL3());
		
		if(!triangulo.esTriangulo()) {
			throw new Exception("No es posible armar el triangulo con esos lados.");
		} else {
			System.out.println("Triángulo válido");
		}
		
		if(triangulo.esEquilatero()) {
			System.out.println("El triángulo es equilatero.");
		} else {
			if(triangulo.esEscaleno()) {
				System.out.println("El triángulo es escaleno.");
			} else {
				System.out.println("El triángulo es isóceles.");
			}
		}
		
	}

}

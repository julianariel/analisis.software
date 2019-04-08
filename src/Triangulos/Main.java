package Triangulos;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int l1, l2, l3 = 0;

		System.out.println("Ingrese el primer lado:\n");
		l1 = in.nextInt();

		System.out.println("Ingrese el segundo lado:\n");
		l2 = in.nextInt();

		System.out.println("Ingrese el tercer lado:\n");
		l3 = in.nextInt();

		System.out.println(l1);
		System.out.println(l2);
		System.out.println(l3);
	}

}

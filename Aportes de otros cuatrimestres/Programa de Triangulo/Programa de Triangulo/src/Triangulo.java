import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Triangulo {

	int ladoA;
	int ladoB;
	int ladoC;

	public Triangulo(String path){
		try{
			FileReader input = new FileReader(path);
			BufferedReader bufInput = new BufferedReader(input);
			
			String line;
			
			line = bufInput.readLine();
			String [] datos;
			datos = line.split(" ");			
			ladoA = Integer.parseInt(datos[0]);
			ladoB = Integer.parseInt(datos[1]);
			ladoC = Integer.parseInt(datos[2]);
			bufInput.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public Triangulo(int ladoA, int ladoB, int ladoC) {
		this.ladoA = ladoA;
		this.ladoB = ladoB;
		this.ladoC = ladoC;

	}

	public boolean verificar() {
		if (ladoA + ladoB > ladoC && ladoB + ladoC > ladoA
				&& ladoA + ladoC > ladoB) {
			return true;
		} else {
			System.out.println("No es un triangulo valido");
			return false;
		}
	}

	public void identificar() {
		if (ladoA == ladoB && ladoB == ladoC) {
			System.out.println("Es un equilatero");
		} else {
			if (ladoA != ladoB && ladoB != ladoC && ladoA != ladoC) {
				System.out.println("Es un escaleno");
			} else {
				System.out.println("Es un isosceles");
			}
		}
	}
}
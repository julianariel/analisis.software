package paquete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
	private static Integer a=null;
	private static Integer b=null;
	private static Integer c=null;
	private static int i=0;

	/**

	 * Este método se encarga de validar si el directorio donde trabajamos es un directorio vacío.

	 * @author: @rubendhk

	 * @version: 1.1.2

	 * @param: directorio Es el directorio donde trabajamos.

	 * @return: true si el directorio es vacío o false si no lo es.

	 */
	private static boolean directorioVacio(final Path directory) throws IOException {
	    try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
	        return !dirStream.iterator().hasNext();
	    }
	}
	private static boolean noEsTriangulo(int x, int y, int z ){

		 return x+y <= z || x+z <= y || z+y <= x?true:false;
	}
	private static boolean esEscaleno(int x, int y, int z){
			return ((x!=y && y!=z) || (z!=x && x!=y))?true:false;

			}
	private static boolean esIsoceles(int x, int y, int z){
		return ((x==y && z!=x && z!=y))?true:false;
		}
	private static boolean esEquilatero(int x, int y, int z){
		return (x==y && y==z || z==x && x==y)?true:false;
		}
	private static void checkeoSiNoHayNrosIngresados(String path){
		File dir = new File(path);
		try {
			if (directorioVacio(dir.toPath())) {
				System.out.println("Directorio vacío. No se puede procesar.");
				System.exit(1);
			}
		} catch (IOException e1) {

			e1.printStackTrace();
		}
	}
	private static void lecturaDeLadosTriangulo(File f){

		Scanner sc=null;
		try {
			sc = new Scanner(f);
			String linea=sc.next();
			a=Integer.parseInt(linea);

	    	linea=sc.next();
	    	b=Integer.parseInt(linea);

	    	linea=sc.next();
	    	c=Integer.parseInt(linea);
	    	sc.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}
	private static void validacionCantDeParametros(File f){
		  Scanner sc=null;

			try {
				sc = new Scanner(f);
				//Primero valido que la entrada tenga los parametros necesarios para procesarlos
				while (sc.hasNext()) {
					sc.next();
					i++;
				}

				sc.close();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}


	}
	private static void procesarTriangulo(File f){

		if(i==0)
			System.out.println(f.getName()+" | No se puede procesar porque no tiene ningun parámetro");
		else if(i==1)
			System.out.println(f.getName()+" | tiene 1 parametro. Ingrese los otros 2.");
		else if(i==2)
			System.out.println(f.getName()+" | tiene 2 parametros. Ingrese el otro.");
		else if(i==3) {
			lecturaDeLadosTriangulo(f);
	        if(noEsTriangulo(a, b, c)){
	        	System.out.println(f.getName()+" | "+"NO ES TRIANGULO");
	       	}
	        else if (esEscaleno(a,b,c)) {
				System.out.println(f.getName()+" | "+"ESCALENO");
			}
	        else if (esEquilatero(a, b, c)) {
				System.out.println(f.getName()+" | "+"EQUILATERO");
			}
	        else if(esIsoceles(a, b, c)) {
				System.out.println(f.getName()+" | "+"ISÓCELES");
				}

			}
		i=0;
	}

	public static void main(String[] args) {

		checkeoSiNoHayNrosIngresados(".\\casos");//1

		for (File file : new File(".\\casos").listFiles()) {//2

		    if (file.isFile()) {//2
		      validacionCantDeParametros(file);//3
		      procesarTriangulo(file);	//3
		    }
		}
	}//4
}

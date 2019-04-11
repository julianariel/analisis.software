package paquete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
	private static boolean isDirEmpty(final Path directory) throws IOException {
	    try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
	        return !dirStream.iterator().hasNext();
	    }
	}
	public static void main(String[] args) {
		//File dir = new File("D:\\Users\\neong\\workspace\\Triangulo\\casos");
		File dir = new File(".\\casos");
		try {
			if (isDirEmpty(dir.toPath())) {
				System.out.println("Directorio vacío. No se puede procesar.");
				System.exit(1);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File[] filesList = dir.listFiles();
		
		
		for (File file : filesList) {
			
		    if (file.isFile()) {
		        
		        Scanner sc=null;
				try {
					sc = new Scanner(file);
					int i=0;
					//Primero valido que la entrada tenga los parametros necesarios para procesarlos
					while (sc.hasNext()) {
						sc.next();
						i++;
					}
					
					sc.close();
					
					switch (i) {
					case 0:
						System.out.println(file.getName()+" | No se puede procesar porque no tiene ningun parámetro");
						break;
					case 1:
						System.out.println(file.getName()+" | tiene 1 parametro. Ingrese los otros 2.");
						break;
					case 2:
						System.out.println(file.getName()+" | tiene 2 parametros. Ingrese el otro.");
						break;
					case 3:
						sc=new Scanner(file);
						Integer a=null;
						Integer b=null;
						Integer c=null;
						
						String linea=sc.next();
						a=Integer.parseInt(linea);
			        	
			        	linea=sc.next();
			        	b=Integer.parseInt(linea);
			        	
			        	linea=sc.next();
			        	c=Integer.parseInt(linea);
						
				        
				        sc.close();
				        //System.out.println(a+" "+b+" "+c);
				        
				        if (a+b <= c || a+c <= c || c+b <= a){
							System.out.println(file.getName()+" | "+"NO ES TRIANGULO");
							continue;
						}
				        	
			        	if (((a!=b && b!=c) || (c!=a && a!=b))) {
							System.out.println(file.getName()+" | "+"ESCALENO");
						}
						if (a==b && b==c || c==a && a==b) {
							System.out.println(file.getName()+" | "+"EQUILATERO");
						}
						if (a==b && c!=a && c!=b) {
							System.out.println(file.getName()+" | "+"ISÓCELES");
						}
						break;
					default:
						break;
					}
							
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		    }
		}

	}

}

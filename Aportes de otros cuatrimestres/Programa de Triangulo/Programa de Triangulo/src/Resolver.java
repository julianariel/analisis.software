
public class Resolver {

	public static void main(String[] args) {
		
		Triangulo triangulo = new Triangulo("triangulo.in");
		if(triangulo.verificar())
			triangulo.identificar();
	}
	
}

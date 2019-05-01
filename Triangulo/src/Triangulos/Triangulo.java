package Triangulos;

public class Triangulo {
	private Integer l1;
	private Integer l2;
	private Integer l3;
	
	public boolean esTriangulo() {		
		if( l1 <= 0 || l2 <= 0 || l3 <= 0) {
			return false;
		}		
		if (l1 + l2 <= l3 || l1 + l3 <= l2 || l2 + l3 <= l1) {
			return false; 
		} 
		return true; 
	}
	
	public boolean esEscaleno() {
		if(l1 != l2 && l1 != l3 && l3 != l2) {
			return true;
		}
		return false;
	}
	
	public boolean esEquilatero() {
		if(l1 == l2 && l1 == l3) {
			return true;
		}
		return false;
	}
	
	public boolean esIsoceles() {
		if(l1 == l2 && l1 != l3) {
			return true;
		}
		return false;
	}
	
	public Integer getL1() {
		return l1;
	}
	public void setL1(Integer l1) {
		this.l1 = l1;
	}
	public Integer getL2() {
		return l2;
	}
	public void setL2(Integer l2) {
		this.l2 = l2;
	}
	public Integer getL3() {
		return l3;
	}
	public void setL3(Integer l3) {
		this.l3 = l3;
	}	
}

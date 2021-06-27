package it.polito.tdp.food.model;

public class Congiunti implements Comparable<Congiunti>{
	Food f;
	double peso;
	
	public Congiunti(Food f, double peso) {
		super();
		this.f = f;
		this.peso = peso;
	}

	public Food getF() {
		return f;
	}

	public void setF(Food f) {
		this.f = f;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Congiunti o) {
		if(this.getPeso()-o.getPeso()<0)
			return -1;
			else if(this.getPeso()-o.getPeso()>0)
				return 1;
			else
				return 0;
	}

	
	
	
	
}

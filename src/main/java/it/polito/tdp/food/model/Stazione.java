package it.polito.tdp.food.model;

public class Stazione {

	boolean occupata;
	Food f;

	public Stazione(boolean occupata) {
		super();
		this.occupata = occupata;
	}
	
	

	public Food getF() {
		return f;
	}



	public void setF(Food f) {
		this.f = f;
	}



	public boolean isOccupata() {
		return occupata;
	}

	public void setOccupata(boolean occupata) {
		this.occupata = occupata;
	}
	
	
}

package it.polito.tdp.food.model;

public class Event implements Comparable<Event>{

	public enum EventType{
		INIZIOPREPARAZIONE,
		FINEPREPARAZIONE,
	};
	Food f;
	Stazione s;
	Double tempo;
	EventType tipo;
	
	public Event(Food f, Stazione s, Double tempo, EventType tipo) {
		super();
		this.f = f;
		this.s = s;
		this.tempo = tempo;
		this.tipo = tipo;
	}

	public Food getF() {
		return f;
	}

	public void setF(Food f) {
		this.f = f;
	}

	public Stazione getS() {
		return s;
	}

	public void setS(Stazione s) {
		this.s = s;
	}

	public Double getTempo() {
		return tempo;
	}

	public void setTempo(Double tempo) {
		this.tempo = tempo;
	}

	public EventType getTipo() {
		return tipo;
	}

	public void setTipo(EventType tipo) {
		this.tipo = tipo;
	}

	@Override
	public int compareTo(Event o) {
		if(this.getTempo()-o.getTempo()<0)
			return -1;
		else if(this.getTempo()-o.getTempo()>0) {
			return 1;
		}else {
			return 0;
		}
	}
	
	
	
}

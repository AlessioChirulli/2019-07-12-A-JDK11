package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.food.model.Event.EventType;
import it.polito.tdp.food.model.Food.StatoPreparazione;

public class Simulator {

	PriorityQueue<Event> queue;
	
	//modello del mondo
	Graph<Food,DefaultWeightedEdge> grafo;
	Model model;
	List<Stazione> stazioni;
	
	//parametri di input
	int K;
	
	//valori calcolati
	double tempoDiPreparazione;
	int cibiPreparati;
	
	public Simulator(int K,Graph<Food,DefaultWeightedEdge> grafo,Model model,Food f) {
		queue=new PriorityQueue<Event>();
		this.grafo=grafo;
		this.model=model;
		this.K=K;
		stazioni=new ArrayList<Stazione>();
		//stazioni tutte libere
		for(int i=0;i<K;i++) {
			stazioni.add(new Stazione(false));
		}
		this.tempoDiPreparazione=0;
		this.cibiPreparati=0;
		
		for(Food food:grafo.vertexSet())
		food.setStato(StatoPreparazione.DAPREPARARE);
		
		List<Congiunti> cibi=model.getCongiunti(f, K);
		//riempio le stazioni
		for(int i=0;i<cibi.size();i++) {
			stazioni.get(i).setF(cibi.get(i).getF());
			stazioni.get(i).setOccupata(true);
			cibi.get(i).getF().setStato(StatoPreparazione.INCORSO);
			//inizializzo la coda
			queue.add(new Event(f,stazioni.get(i),this.grafo.getEdgeWeight(grafo.getEdge(cibi.get(i).getF(), f)),EventType.FINEPREPARAZIONE));
		}
		
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			Event e = queue.poll() ;
			processEvent(e) ;
		}
	}

	private void processEvent(Event e) {
		switch(e.getTipo()) {
		case INIZIOPREPARAZIONE:
			for(Congiunti c:model.getCongiuntiTot(e.getF())) {
				if(c.getF().getStato()==StatoPreparazione.DAPREPARARE) {
					queue.add(new Event(c.getF(),e.getS(),grafo.getEdgeWeight(grafo.getEdge(c.getF(), e.getF())),EventType.FINEPREPARAZIONE));
					c.getF().setStato(StatoPreparazione.INCORSO);
					e.getS().setOccupata(true);
					e.getS().setF(c.getF());
					break;
				}
			}
			break;
		case FINEPREPARAZIONE:
			this.cibiPreparati++;
			this.tempoDiPreparazione+=e.getTempo();
			e.getS().setOccupata(false);
			e.getS().setF(null);
			e.getF().setStato(StatoPreparazione.PREPARATO);
			queue.add(new Event(e.getF(),e.getS(),e.getTempo(),EventType.INIZIOPREPARAZIONE));
		break;
		}
		
	}
	
	public int getCibiPreparati() {
		return this.cibiPreparati;
	}
	public double getTempo() {
		return this.tempoDiPreparazione;
	}
}

package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {

	Graph<Food,DefaultWeightedEdge> grafo;
	Map<Integer,Food> idMap;
	FoodDao dao;
	
	public Model() {
		dao=new FoodDao();
	}
	
	public String creaGrafo(int porzioni) {
		idMap=new HashMap<>();
		grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		dao.listAllFoodsByPortions(porzioni, idMap);
		Graphs.addAllVertices(grafo, idMap.values());
		for(Adiacenza a: dao.getAdiacenze(idMap)) {
			if(grafo.getEdge(a.getF1(), a.getF2())==null) {
				Graphs.addEdge(grafo, a.getF1(), a.getF2(), a.getPeso());
			}
		}
		return "GRAFO CREATO!\n#VERTICI: "+grafo.vertexSet().size()+"\n#ARCHI: "+grafo.edgeSet().size();
	}
	
	public Set<Food> getVertex() {
		return grafo.vertexSet();
	}
	
	public List<Congiunti> getCongiunti(Food food,int n){
		List<Congiunti> lista=new ArrayList<Congiunti>();
		List<Congiunti> result=new LinkedList<Congiunti>();
		for(Food f:Graphs.neighborListOf(grafo, food)) {
			Congiunti c=new Congiunti(f,grafo.getEdgeWeight(grafo.getEdge(f, food)));
			lista.add(c);
		}
		Collections.sort(lista);
		if(lista.size()>=n) {
		for(int i=0;i<n;i++) {
			result.add(lista.get(i));
		}
		return result;
		}
		else	
		return lista;
	}
	
	public List<Congiunti> getCongiuntiTot(Food food){
		List<Congiunti> lista=new ArrayList<Congiunti>();
		for(Food f:Graphs.neighborListOf(grafo, food)) {
			Congiunti c=new Congiunti(f,grafo.getEdgeWeight(grafo.getEdge(f, food)));
			lista.add(c);
		}
		Collections.sort(lista);	
		return lista;
	}
	
	public String simula(int k,Food f) {
		Simulator sim=new Simulator(k,this.grafo,this,f);
		sim.run();
		return "TOTALE CIBI PREPARATI: "+sim.getCibiPreparati()+"\n TOTALE TEMPO IMPIEGATO: "+sim.getTempo()+" minuti";
		
	}
}

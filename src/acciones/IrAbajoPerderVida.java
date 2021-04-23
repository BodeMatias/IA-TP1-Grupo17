package acciones;

import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Posicion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrAbajoPerderVida extends SearchAction{
	
	Double cost=50.0;

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) s;
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		Posicion posicion = nuevoEstado.getPosicion();
		int vidas = nuevoEstado.getVidas();
		
		//Si no puedo moverme, retorno null
		if(posicion.getFila()==8 || bosque[posicion.getFila()+1][posicion.getColumna()]==-1) {
		return null;	
		} 
		//Sino, empiezo a "mover" a caperucita, pero su estado permanece
		else {
			int fila = posicion.getFila();
			int columna = posicion.getColumna();
			int celda;
			int i = 1;
			do {
				celda = bosque[fila+i][columna];
				switch(celda) {
				//Esta el lobo, entonces retorno el estado inicial con una vida menos
					case 2: {
						nuevoEstado.initState();
						nuevoEstado.setVidas(vidas-1);
						return nuevoEstado;
					}
				}
				i++;
			}
			//Se corta cuando estoy en un arbol o en el borde del mapa
			while(celda != -1 && fila+i-1!=8);
			
			//No me encontre con el lobo, entonces retorno null
			return null;
		}
	}

	@Override
	public Double getCost() {
		return cost;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAmbiente nuevoEstadoAm = (EstadoAmbiente) est;
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) ast;
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		Posicion posicion = nuevoEstado.getPosicion();
		int vidas = nuevoEstado.getVidas();
		
		//Si no puedo moverme, retorno null
		if(posicion.getFila()==8 || bosque[posicion.getFila()+1][posicion.getColumna()]==-1) {
		return null;	
		} 
		//Sino, empiezo a "mover" a caperucita, pero su estado permanece
		else {
			int fila = posicion.getFila();
			int columna = posicion.getColumna();
			int celda;
			int i = 1;
			do {
				celda = bosque[fila+i][columna];
				switch(celda) {
				//Esta el lobo, entonces retorno el estado inicial pero con una vida menos
					case 2: {
						nuevoEstado.initState();
						nuevoEstado.setVidas(vidas-1);
						nuevoEstadoAm.initState();
						nuevoEstadoAm.setVidasCaperucita(vidas-1);
						return nuevoEstadoAm;
					}
				}
				i++;
			}
			//Corto al llegar a un arbol o al borde del mapa
			while(celda != -1 && fila+i-1!=8);
			
			//No me encontre con el lobo, retorno null
			return null;
		}
	}

	@Override
	public String toString() {
		return "IrAbajoPerderVida";
	}

}

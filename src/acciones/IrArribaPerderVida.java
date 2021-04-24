package acciones;

import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Posicion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrArribaPerderVida extends SearchAction{
	
	Double cost=75.0;

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) s;
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		int[][]visitadas = nuevoEstado.getVisitadas();
		Posicion posicion = nuevoEstado.getPosicion();
		int vidas = nuevoEstado.getVidas();
		
		//Si no puedo moverme, retorno null
		if(posicion.getFila()==0 || bosque[posicion.getFila()-1][posicion.getColumna()]==-1 || visitadasMasDe5Veces(bosque, visitadas, posicion)) {
		return null;	
		} 
		//Sino, empiezo a "mover" a caperucita, pero su estado permanece
		else {
			int fila = posicion.getFila();
			int columna = posicion.getColumna();
			int celda;
			int i = 1;
			do {
				celda = bosque[fila-i][columna];
				switch(celda) {
				//Esta el lobo, entonces retorno el estado inicial con una vida menos
					case 2: {
						nuevoEstado.initState();
						nuevoEstado.setVidas(vidas-1);
						return nuevoEstado;
					}
				}
				visitadas[fila-i][columna]++;
				i++;
			}while(celda != -1 && fila-(i-1)!=0);

			//No me encontre con el lobo, entonces retorno null
			return null;
		}
	}

	@Override
	public Double getCost() {
		return this.cost;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAmbiente nuevoEstadoAm = (EstadoAmbiente) est;
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) ast;
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		int[][] visitadas = nuevoEstado.getVisitadas();
		Posicion posicion = nuevoEstado.getPosicion();
		int vidas = nuevoEstado.getVidas();
		
		//Si no puedo moverme, retorno null
		if(posicion.getFila()==0 || bosque[posicion.getFila()-1][posicion.getColumna()]==-1 || visitadasMasDe5Veces(bosque, visitadas, posicion)) {
		return null;	
		} 
		//Sino, empiezo a "mover" a caperucita, pero su estado permanece
		else {
			int fila = posicion.getFila();
			int columna = posicion.getColumna();
			int celda;
			int i = 1;
			do {
				celda = bosque[fila-i][columna];
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
				visitadas[fila-i][columna]++;
				i++;
			}while(celda != -1 && fila-(i-1)!=0);

			//No me encontre con el lobo, retorno null
			return null;
		}
	}

	@Override
	public String toString() {
		return "IrArribaPerderVida"+" Costo: "+this.getCost();
	}
	
	private boolean visitadasMasDe5Veces(int[][] bosque, int[][] visitadas, Posicion p) {
		int fila = p.getFila();
		int col = p.getColumna();
		int i=1;
		
		while(fila-i>=0 && bosque[fila-i][col]!=-1) {
			if(visitadas[fila-i][col]<5) {
				return false;
			}
			i++;
		}
		
		return true;
	}

}

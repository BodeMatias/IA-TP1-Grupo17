package acciones;

import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Posicion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrDerechaPerderVida extends SearchAction{
	
	Double cost=50.0;

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) s;
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		Posicion posicion = nuevoEstado.getPosicion();
		Integer vidas = nuevoEstado.getVidas();
		
		//Si no puedo moverme, retorno null
		if(posicion.getColumna()==13 || bosque[posicion.getFila()][posicion.getColumna()+1]==-1) {
		return null;	
		} 
		//Sino, empiezo a "mover" a caperucita, pero su estado permanece
		else {
			int fila = posicion.getFila();
			int columna = posicion.getColumna();
			int celda;
			int i = 1;
			do {
				celda = bosque[fila][columna+i];
				//System.out.println("Derecha:\nFila: "+fila+"\nColumna: "+(columna+i)+"\nCelda: "+celda);
				switch(celda) {
				//Esta el lobo, entonces retorno el estado inicial con una vida menos
					case 2: {
						nuevoEstado.initState();
						nuevoEstado.setVidas(vidas-1);
						return nuevoEstado;
					}
				}
				i++;
			}while(celda != -1 && columna+i-1!=13);

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
		Integer vidas = nuevoEstado.getVidas();
		
		//Si no puedo moverme, retorno null
		if(posicion.getColumna()==13 || bosque[posicion.getFila()][posicion.getColumna()+1]==-1) {
		return null;	
		} 
		//Sino, empiezo a "mover" a caperucita, pero su estado permanece
		else {
			int fila = posicion.getFila();
			int columna = posicion.getColumna();
			int celda;
			int i = 1;
			do {
				celda = bosque[fila][columna+i];
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
			}while(celda != -1 && columna+i-1!=13);

			//No me encontre con el lobo, retorno null
			return null;
		}
	}

	@Override
	public String toString() {
		return "IrDerechaPerderVida";
	}

}

package acciones;

import java.util.ArrayList;

import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Posicion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrDerecha extends SearchAction{
	
	Double cost=0.0;

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		this.cost=0.0;
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) s;
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		int[][]visitadas = nuevoEstado.getVisitadas();
		Posicion posicion = nuevoEstado.getPosicion();
		int caramelos = nuevoEstado.getCantidadDeCaramelos();
		
		//Si no puedo moverme, retorno null
		if(posicion.getColumna()==13 || bosque[posicion.getFila()][posicion.getColumna()+1]==-1 || visitadasMasDe5Veces(bosque, visitadas, posicion)) {
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
				//Encuentro caramelo, retorno null
				case 1: {
						return null;
					}
				//Encuentro al lobo, retorno null
				case 2: {
						return null;
					}
				//Encuentro el campo de flores, retorno null
				case 4: {
						return null;
					}
				}
				visitadas[fila][columna+i]++;
				this.cost++;
				i++;
			}
			//Se corta cuando estoy en un arbol o en el borde del mapa
			while(celda != -1 && columna+i-1!=13);
			
			//Como marqué un arbol como visitado, lo desmarco
			if(celda==-1) {
				visitadas[fila][columna+(i-1)]--;
				this.cost--;
			}
			
			//termine de moverme, actualizo
			//Saco a caperucita de su antigua posicion
			bosque[posicion.getFila()][posicion.getColumna()]=0;

			//Tengo que saber si corté por llegar a un arbol (true) o por llegar al borde del mapa (false)
			posicion.setColumna(celda==-1 ? columna+(i-2) : columna+(i-1));

			//Muevo a caperucita a su nueva posicion
			bosque[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstado.setPosicion(posicion);

			//Actualizo el nuevo estado
			nuevoEstado.setCantidadDeCaramelos(caramelos);
			nuevoEstado.setBosqueCaperucita(bosque);
			nuevoEstado.setVisitadas(visitadas);
			return nuevoEstado;
		}
	}

	@Override
	public Double getCost() {
		return cost*2;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAmbiente nuevoEstadoAm = (EstadoAmbiente) est;
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) ast;
		int[][]bosqueAm = nuevoEstadoAm.getBosqueAmbiente();
		ArrayList<Posicion> posCaramelos = nuevoEstadoAm.getPosicionCaramelos();
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		int[][] visitadas = nuevoEstado.getVisitadas();
		Posicion posicion = nuevoEstado.getPosicion();
		int caramelos = nuevoEstado.getCantidadDeCaramelos();
		
		//Si no puedo moverme, retorno null
		if(posicion.getColumna()==13 || bosque[posicion.getFila()][posicion.getColumna()+1]==-1 || visitadasMasDe5Veces(bosque, visitadas, posicion)) {
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
				//Encuentro un caramelo, retorno null
					case 1: {
						return null;
					}
				//Encuentro al lobo, retorno null
					case 2: {
						return null;
					}
				//Encuentro el campo de flores, retorno null
					case 4:{
						return null;
					}
				}
				visitadas[fila][columna+i]++;
				i++;
			}while(celda != -1 && columna+i-1!=13);
			
			//Como marqué un arbol como visitado, lo desmarco
			if(celda==-1) {
				visitadas[fila][columna+(i-1)]--;
			}
			
			//termine de moverme, actualizo
			//Saco a caperucita de su posicion en los dos bosques
			bosqueAm[posicion.getFila()][posicion.getColumna()]=0;
			bosque[posicion.getFila()][posicion.getColumna()]=0;

			//Tengo que saber si corté por llegar a un arbol (true) o al borde del mapa (flse)
			posicion.setColumna(celda==-1 ? columna+(i-2) : columna+(i-1));
			
			//Pongo a caperucita en su nueva posicion
			bosque[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstado.setPosicion(posicion);
			bosqueAm[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstadoAm.setPosicionCaperucita(new Posicion(nuevoEstado.getPosicion().getFila(), nuevoEstado.getPosicion().getColumna()));

			//Termino de actualizar
			nuevoEstado.setCantidadDeCaramelos(caramelos);
			nuevoEstadoAm.setPosicionCaramelos(posCaramelos);
			nuevoEstado.setBosqueCaperucita(bosque);
			nuevoEstado.setVisitadas(visitadas);
			nuevoEstadoAm.setBosqueAmbiente(bosqueAm);
			return nuevoEstadoAm;
		}
	}

	@Override
	public String toString() {
		return "IrDerecha";
	}
	
	private boolean visitadasMasDe5Veces(int[][] bosque, int[][] visitadas, Posicion p) {
		int fila = p.getFila();
		int col = p.getColumna();
		int i=1;
		
		while(col+i<=13 && bosque[fila][col+i]!=-1) {
			if(visitadas[fila][col+i]<5) {
				return false;
			}
			i++;
		}
		
		return true;
	}

}

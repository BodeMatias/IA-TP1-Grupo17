package acciones;

import java.util.ArrayList;

import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Posicion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrAbajo extends SearchAction{
	
	Double cost=5.0;

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) s;
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		int[][]visitadas = nuevoEstado.getVisitadas();
		Posicion posicion = nuevoEstado.getPosicion();
		int caramelos = nuevoEstado.getCantidadDeCaramelos();
		
		//Si no puedo moverme, retorno null
		if(posicion.getFila()==8 || bosque[posicion.getFila()+1][posicion.getColumna()]==-1 || visitadasMasDe5Veces(bosque, visitadas, posicion)) {
		return null;	
		} 
		//Sino, empiezo a "mover" a caperucita, pero su estado permanece
		else{
			int fila = posicion.getFila();
			int columna = posicion.getColumna();
			int celda;
			int i = 1;
			do {
				celda = bosque[fila+i][columna];
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
				visitadas[fila+i][columna]++;
				i++;
			}
			//Se corta cuando estoy en un arbol o en el borde del mapa
			while(celda != -1 && fila+i-1!=8);
			
			//Como marqu� un arbol como visitado, lo desmarco
			if(celda==-1) {
				visitadas[fila+(i-1)][columna]--;
			}
			
			//termine de moverme, actualizo
			//Saco a caperucita de su antigua posicion
			bosque[posicion.getFila()][posicion.getColumna()]=0;
			
			//Tengo que saber si cort� por llegar a un arbol (true) o por llegar al borde del mapa (false)
			posicion.setFila(celda==-1? fila+(i-2) : fila+(i-1));
			
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
		return cost;
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
		if(posicion.getFila()==8 || bosque[posicion.getFila()+1][posicion.getColumna()]==-1 || visitadasMasDe5Veces(bosque, visitadas, posicion)) {
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
				visitadas[fila+i][columna]++;
				i++;
			}
			//Corto al llegar a un arbol o al borde del mapa
			while(celda != -1 && fila+i-1!=8);
			
			//Como marqu� un arbol como visitado, lo desmarco
			if(celda==-1) {
				visitadas[fila+(i-1)][columna]--;
			}
			
			//termine de moverme, actualizo
			//Saco a caperucita de su posicion en los dos bosques
			bosqueAm[posicion.getFila()][posicion.getColumna()]=0;
			bosque[posicion.getFila()][posicion.getColumna()]=0;
			
			//Tengo que saber si cort� por llegar a un arbol (true) o al borde del mapa (flse)
			posicion.setFila(celda==-1 ? fila+(i-2) : fila+(i-1));
			
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
		return "IrAbajo";
	}

	private boolean visitadasMasDe5Veces(int[][] bosque, int[][] visitadas, Posicion p) {
		int fila = p.getFila();
		int col = p.getColumna();
		int i=1;
		
		while(fila+i<=8 && bosque[fila+i][col]!=-1) {
			if(visitadas[fila+i][col]<5) {
				return false;
			}
			i++;
		}
		
		return true;
	}
}

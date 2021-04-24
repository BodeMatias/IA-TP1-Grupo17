package acciones;

import java.util.ArrayList;
import java.util.Iterator;

import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Posicion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrIzquierdaYJuntarDulceSiHay extends SearchAction{
	
	Double cost=14.0;

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		boolean hayCaramelo=false;
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) s;
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		int[][]visitadas = nuevoEstado.getVisitadas();
		Posicion posicion = nuevoEstado.getPosicion();
		int caramelos = nuevoEstado.getCantidadDeCaramelos();
		
		//Si no puedo moverme, retorno null
		if(posicion.getColumna()==0 || bosque[posicion.getFila()][posicion.getColumna()-1]==-1 || visitadasMasDe5Veces(bosque, visitadas, posicion)) {
		return null;	
		} 
		//Sino, empiezo a "mover" a caperucita, pero su estado permanece
		else {
			int fila = posicion.getFila();
			int columna = posicion.getColumna();
			int celda;
			int i = 1;
			do {
				celda = bosque[fila][columna-i];
				switch(celda) {
				//Junto caramelo, lo saco del bosque
					case 1: {
						bosque[fila+i][columna]=0;
						caramelos++;
						hayCaramelo=true;
						break;
					}
				//Esta el lobo, entonces retorno null
					case 2: {
						return null;
					}
				//Llegue al campo de flores, entonces retorno null
					case 4: {
						return null;
					}
				}
				visitadas[fila][columna-i]++;
				i++;
			}
			//Se corta cuando estoy en un arbol o en el borde del mapa
			while(celda != -1 && columna-(i-1)!=0);

			//Si no encontre caramelos, retorno null
			if(!hayCaramelo) {
				return null;
			}
			
			//Como marqu� un arbol como visitado, lo desmarco
			if(celda==-1) {
				visitadas[fila][columna-(i-1)]--;
			}
			
			//Termine de moverme, actualizo			
			//Saco a caperucita de su antigua posicion
			bosque[posicion.getFila()][posicion.getColumna()]=0;
			
			//Debo saber si cort� por llegar a un arbol (true) o al borde del mapa (false)
			posicion.setColumna(celda==-1 ? columna-(i-2) : columna-(i-1));

			//Actualizo la posicion de caperucita
			nuevoEstado.setPosicion(posicion);
			bosque[posicion.getFila()][posicion.getColumna()]=3;
			
			nuevoEstado.setCantidadDeCaramelos(caramelos);
			nuevoEstado.setBosqueCaperucita(bosque);
			nuevoEstado.setVisitadas(visitadas);
			return nuevoEstado;
		}
	}

	@Override
	public Double getCost() {
		return cost*1.5;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		boolean hayCaramelo=false;
		EstadoAmbiente nuevoEstadoAm = (EstadoAmbiente) est;
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) ast;
		int[][]bosqueAm = nuevoEstadoAm.getBosqueAmbiente();
		ArrayList<Posicion> posCaramelos = nuevoEstadoAm.getPosicionCaramelos();
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		int[][] visitadas = nuevoEstado.getVisitadas();
		Posicion posicion = nuevoEstado.getPosicion();
		int caramelos = nuevoEstado.getCantidadDeCaramelos();
		
		//Si no puedo moverme, retorno null
		if(posicion.getColumna()==0 || bosque[posicion.getFila()][posicion.getColumna()-1]==-1 || visitadasMasDe5Veces(bosque, visitadas, posicion)) {
		return null;	
		} 
		//Sino, empiezo a "mover" a caperucita, pero su estado permanece
		else {
			int fila = posicion.getFila();
			int columna = posicion.getColumna();
			int celda;
			int i = 1;
			do {
				celda = bosque[fila][columna-i];
				switch(celda) {
				//Junto caramelo, lo saco del bosque
					case 1: {
						bosque[fila][columna-i]=0;
						bosqueAm[fila][columna-i]=0;
						Iterator<Posicion> iterator = posCaramelos.iterator();
						while(iterator.hasNext()) {
							Posicion p = iterator.next();
							if(p.getFila()==fila && p.getColumna()==columna-i) {
								iterator.remove();
								break;
							}
						}
						caramelos++;
						hayCaramelo=true;
						break;
					}
				//Esta el lobo, entonces retorno null
					case 2: {
						return null;
					}
				//Esta el campo de flores, entonces retorno null
					case 4: {
						return null;
					}
				}
				visitadas[fila][columna-i]++;
				i++;
			}
			//Corto al llegar a un arbol o al borde del mapa
			while(celda != -1 && columna-(i-1)!=0);
			
			//Si no encontre caramelo, retorno null
			if(!hayCaramelo) {
				return null;
			}
			
			//Como marqu� un arbol como visitado, lo desmarco
			if(celda==-1) {
				visitadas[fila][columna-(i-1)]--;
			}
			
			//termine de moverme, actualizo
			//Saco a caperucita de su posicion en los dos bosques
			bosqueAm[posicion.getFila()][posicion.getColumna()]=0;
			bosque[posicion.getFila()][posicion.getColumna()]=0;
			
			//Tengo que saber si cort� por llegar a un arbol (true) o al borde del mapa (flse)
			posicion.setColumna(celda==-1 ? columna-(i-2) : columna-(i-1));
			
			//Pongo a caperucita en su nueva posicion
			bosque[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstado.setPosicion(posicion);
			bosqueAm[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstadoAm.setPosicionCaperucita(new Posicion(nuevoEstado.getPosicion().getFila(), nuevoEstado.getPosicion().getColumna()));
			
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
		return "IrIzquierdaJuntarCaramelo"+" Costo: "+this.getCost();
	}
	
	private boolean visitadasMasDe5Veces(int[][] bosque, int[][] visitadas, Posicion p) {
		int fila = p.getFila();
		int col = p.getColumna();
		int i=1;
		
		while(col-i>=0 && bosque[fila][col-i]!=-1) {
			if(visitadas[fila][col-i]<5) {
				return false;
			}
			i++;
		}
		
		return true;
	}

}

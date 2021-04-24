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

public class IrArribaYLlegarCampoFloresSiHay extends SearchAction{
	
	Double cost=0.0;

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		this.cost=0.0;
		boolean hayCampo=false;
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) s;
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		int[][]visitadas = nuevoEstado.getVisitadas();
		Posicion posicion = nuevoEstado.getPosicion();
		int caramelos = nuevoEstado.getCantidadDeCaramelos();
		
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
				//Junto caramelo, lo saco del bosque
					case 1: {//junto caramelo, lo saco del bosque
						bosque[fila-i][columna]=0;
						caramelos++;
						break;
					}
				//Esta el lobo, entonces retorno null
					case 2: {
						return null;
					}
				//Encuentro el campo de flores, actualizo la bandera
					case 4: {
						hayCampo=true;
					}
				}
				visitadas[fila-i][columna]++;
				this.cost++;
				i++;
			}
			//Se corta cuando estoy en un arbol o en el borde del mapa
			while(celda != -1 && fila-(i-1)!=0);

			//Si no llegue al campo de flores, retorno null
			if(!hayCampo) {
				return null;
			}
			
			//Como marqu� un arbol como visitado, lo desmarco
			if(celda==-1) {
				visitadas[fila-(i-1)][columna]--;
				this.cost--;
			}
			
			//termine de moverme, actualizo
			bosque[posicion.getFila()][posicion.getColumna()]=0;
			
			//Tengo que saber si cort� por llegar a un arbol (true) o por llegar al borde del mapa (false)
			posicion.setFila(celda==-1 ? fila-(i-2) : fila-(i-1));

			//Muevo a caperucita a su nueva posicion
			bosque[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstado.setPosicion(posicion);

			nuevoEstado.setCantidadDeCaramelos(caramelos);
			nuevoEstado.setBosqueCaperucita(bosque);
			nuevoEstado.setVisitadas(visitadas);
			return nuevoEstado;
		}
	}

	@Override
	public Double getCost() {
		return this.cost;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		boolean hayCampo=false;
		EstadoAmbiente nuevoEstadoAm = (EstadoAmbiente) est;
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) ast;
		int[][]bosqueAm = nuevoEstadoAm.getBosqueAmbiente();
		ArrayList<Posicion> posCaramelos = nuevoEstadoAm.getPosicionCaramelos();
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		int[][] visitadas = nuevoEstado.getVisitadas();
		Posicion posicion = nuevoEstado.getPosicion();
		int caramelos = nuevoEstado.getCantidadDeCaramelos();
		
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
				//Junto caramelo, lo saco del bosque
					case 1: {
						bosque[fila-i][columna]=0;
						bosqueAm[fila-i][columna]=0;
						Iterator<Posicion> iterator = posCaramelos.iterator();
						while(iterator.hasNext()) {
							Posicion p = iterator.next();
							if(p.getFila()==fila-i && p.getColumna()==columna) {
								iterator.remove();
								break;
							}
						}
						caramelos++;
						break;
					}
				//Esta el lobo, entonces retorno null
					case 2: {
						return null;
					}
				//Esta el campo de flores, entonces actualizo la bandera
					case 4: {
						hayCampo=true;
					}
				}
				visitadas[fila-i][columna]++;
				i++;
			}
			//Corto al llegar a un arbol o al borde del mapa
			while(celda != -1 && fila-(i-1)!=0);

			//Si no llegue al campo de flores, retorno null
			if(!hayCampo) {
				return null;
			}
			
			//Como marqu� un arbol como visitado, lo desmarco
			if(celda==-1) {
				visitadas[fila-(i-1)][columna]--;
			}
			
			//termine de moverme, actualizo
			//Saco a caperucita de su posicion en los dos bosques
			bosqueAm[posicion.getFila()][posicion.getColumna()]=0;
			bosque[posicion.getFila()][posicion.getColumna()]=0;
			
			nuevoEstado.setCantidadDeCaramelos(caramelos);

			//Tengo que saber si cort� por llegar a un arbol (true) o al borde del mapa (flse)
			posicion.setFila(celda==-1 ? fila-(i-2) : fila-(i-1));
			
			//Pongo a caperucita en su nueva posicion
			bosque[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstado.setPosicion(posicion);
			bosqueAm[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstadoAm.setPosicionCaperucita(new Posicion(nuevoEstado.getPosicion().getFila(), nuevoEstado.getPosicion().getColumna()));
			
			nuevoEstadoAm.setPosicionCaramelos(posCaramelos);
			nuevoEstado.setBosqueCaperucita(bosque);
			nuevoEstado.setVisitadas(visitadas);
			nuevoEstadoAm.setBosqueAmbiente(bosqueAm);
			return nuevoEstadoAm;
		}
	}

	@Override
	public String toString() {
		return "IrArribaAlCampoDeFlores";
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

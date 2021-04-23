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

public class IrAbajoYLlegarCampoFloresSiHay extends SearchAction{
	
	Double cost=1.0;

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		boolean hayCampo=false;
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) s;
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		Posicion posicion = nuevoEstado.getPosicion();
		Integer caramelos = nuevoEstado.getCantidadDeCaramelos();
		
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
				//Junto caramelo, lo saco del bosque
					case 1: {
						bosque[fila+i][columna]=0;
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
				i++;
			}
			//Se corta cuando estoy en un arbol o en el borde del mapa
			while(celda != -1 && fila+i-1!=8);
			
			//Si no llegue al campo de flores, retorno null
			if(!hayCampo) {
				return null;
			}
			
			//termine de moverme, actualizo		
			//Saco a caperucita de su antigua posicion
			bosque[posicion.getFila()][posicion.getColumna()]=0;
			
			//Tengo que saber si corté por llegar a un arbol (true) o por llegar al borde del mapa (false)
			posicion.setFila(celda==-1? fila+(i-2) : fila+(i-1));
			
			//Muevo a caperucita a su nueva posicion
			bosque[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstado.setPosicion(posicion);

			nuevoEstado.setCantidadDeCaramelos(caramelos);
			nuevoEstado.setBosqueCaperucita(bosque);
			return nuevoEstado;
		}
	}

	@Override
	public Double getCost() {
		return cost;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		boolean hayCampo=false;
		EstadoAmbiente nuevoEstadoAm = (EstadoAmbiente) est;
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) ast;
		int[][]bosqueAm = nuevoEstadoAm.getBosqueAmbiente();
		ArrayList<Posicion> posCaramelos = nuevoEstadoAm.getPosicionCaramelos();
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		Posicion posicion = nuevoEstado.getPosicion();
		Integer caramelos = nuevoEstado.getCantidadDeCaramelos();
		
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
				//Junto caramelo, lo saco del bosque
					case 1: {
						bosque[fila+i][columna]=0;
						bosqueAm[fila+i][columna]=0;
						Iterator<Posicion> iterator = posCaramelos.iterator();
						while(iterator.hasNext()) {
							Posicion p = iterator.next();
							if(p.getFila()==fila+i && p.getColumna()==columna) {
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
				i++;
			}
			//Corto al llegar a un arbol o al borde del mapa
			while(celda != -1 && fila+i-1!=8);
			
			//Si no llegue al campo de flores, retorno null
			if(!hayCampo) {
				return null;
			}
			
			//termine de moverme, actualizo
			//Saco a caperucita de su posicion en los dos bosques
			bosqueAm[posicion.getFila()][posicion.getColumna()]=0;
			bosque[posicion.getFila()][posicion.getColumna()]=0;
			
			nuevoEstado.setCantidadDeCaramelos(caramelos);
			
			//Tengo que saber si corté por llegar a un arbol (true) o al borde del mapa (flse)
			posicion.setFila(celda==-1 ? fila+(i-2) : fila+(i-1));
			
			//Pongo a caperucita en su nueva posicion
			bosque[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstado.setPosicion(posicion);
			bosqueAm[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstadoAm.setPosicionCaperucita(new Posicion(nuevoEstado.getPosicion().getFila(), nuevoEstado.getPosicion().getColumna()));

			nuevoEstadoAm.setPosicionCaramelos(posCaramelos);
			nuevoEstado.setBosqueCaperucita(bosque);
			nuevoEstadoAm.setBosqueAmbiente(bosqueAm);
			return nuevoEstadoAm;
		}
	}

	@Override
	public String toString() {
		return "IrAbajoAlCampoDeFlores";
	}

}

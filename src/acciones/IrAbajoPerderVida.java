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

public class IrAbajoPerderVida extends SearchAction{
	
	Double cost=50.0;

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) s;
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		Posicion posicion = nuevoEstado.getPosicion();
		Integer vidas = nuevoEstado.getVidas();
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
					/*case 1: {//junto caramelo, lo saco del bosque
						bosque[fila+i][columna]=0;
						caramelos++;
						this.cost--;
						break;
					}*/
					case 2: {//esta el lobo, entonces subo el costo de este camino
						/*this.cost=5.0;
						//nuevoEstado.setVidas(vidas-1);
						break;*/
						nuevoEstado.initState();
						nuevoEstado.setVidas(vidas-1);
						return nuevoEstado;
					}
					/*case 4: {
						this.cost-=5.0;
					}*/
				}
				i++;
			}
			//Se corta cuando estoy en un arbol o en el borde del mapa
			while(celda != -1 && fila+i-1!=8);
			/*
			//termine de moverme, actualizo
			
			nuevoEstado.setCantidadDeCaramelos(caramelos);
			
			//Saco a caperucita de su antigua posicion
			bosque[posicion.getFila()][posicion.getColumna()]=0;
			
			//Tengo que saber si corté por llegar a un arbol (true) o por llegar al borde del mapa (false)
			posicion.setFila(celda==-1? fila+(i-2) : fila+(i-1));
			
			//Muevo a caperucita a su nueva posicion
			bosque[posicion.getFila()][posicion.getColumna()]=3;
			
			nuevoEstado.setBosqueCaperucita(bosque);
			nuevoEstado.setPosicion(posicion);
			return nuevoEstado;*/
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
		
		int[][]bosqueAm = nuevoEstadoAm.getBosqueAmbiente();
		ArrayList<Posicion> posCaramelos = nuevoEstadoAm.getPosicionCaramelos();
		
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		Posicion posicion = nuevoEstado.getPosicion();
		Integer vidas = nuevoEstado.getVidas();
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
					/*case 1: {//junto caramelo, lo saco del bosque
						bosque[fila+i][columna]=0;
						bosqueAm[fila+i][columna]=0;
						//saco el caramelo de la posicion de caramelos dele stado del ambiente
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
					}*/
					case 2: {//esta el lobo, entonces retorno el estado inicial pero con una vida menos
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
			//termine de moverme, actualizo
			/*
			//Saco a caperucita de su posicion en los dos bosques
			bosqueAm[posicion.getFila()][posicion.getColumna()]=0;
			bosque[posicion.getFila()][posicion.getColumna()]=0;
			
			nuevoEstado.setCantidadDeCaramelos(caramelos);
			
			//Tengo que saber si corté por llegar a un arbol (true) o al borde del mapa (flse)
			posicion.setFila(celda==-1 ? fila+(i-2) : fila+(i-1));
			
			//Pongo a caperucita en su nueva posicion
			bosque[posicion.getFila()][posicion.getColumna()]=3;
			
			nuevoEstado.setBosqueCaperucita(bosque);
			nuevoEstado.setPosicion(posicion);
			
			bosqueAm[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstadoAm.setPosicionCaperucita(new Posicion(nuevoEstado.getPosicion().getFila(), nuevoEstado.getPosicion().getColumna()));
			
			nuevoEstadoAm.setBosqueAmbiente(bosqueAm);
			nuevoEstadoAm.setPosicionCaramelos(posCaramelos);
			return nuevoEstadoAm;*/
			return null;
		}
	}

	@Override
	public String toString() {
		return "IrAbajoPerderVida";
	}

}

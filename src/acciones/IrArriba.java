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

public class IrArriba extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) s;
		int[][] bosque = nuevoEstado.getBosqueCaperucita();
		Posicion posicion = nuevoEstado.getPosicion();
		Integer vidas = nuevoEstado.getVidas();
		Integer caramelos = nuevoEstado.getCantidadDeCaramelos();
		
		//Si no puedo moverme, retorno null
		if(posicion.getFila()==0 || bosque[posicion.getFila()-1][posicion.getColumna()]==-1) {
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
				//System.out.println("Arriba:\nFila: "+(fila-i)+"\nColumna: "+columna+"\nCelda: "+celda);
				switch(celda) {
					case 1: {//junto caramelo, lo saco del bosque
						bosque[fila-i][columna]=0;
						caramelos++;
						break;
					}
					case 2: {//esta el lobo, entonces retorno el estado inicial pero con una vida menos
						//nuevoEstado.initState();
						nuevoEstado.setVidas(vidas-1);
						//return nuevoEstado;
					}
				}
				i++;
			}while(celda != -1 && fila-(i-1)!=0);
			//termine de moverme, actualizo
			bosque[posicion.getFila()][posicion.getColumna()]=0;
			
			nuevoEstado.setCantidadDeCaramelos(caramelos);
			posicion.setFila(celda==-1 ? fila-(i-2) : fila-(i-1));
			
			bosque[posicion.getFila()][posicion.getColumna()]=3;
			
			nuevoEstado.setBosqueCaperucita(bosque);
			nuevoEstado.setPosicion(posicion);
			return nuevoEstado;
		}
	}

	@Override
	public Double getCost() {
		return 1.0;
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
		if(posicion.getFila()==0 || bosque[posicion.getFila()-1][posicion.getColumna()]==-1) {
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
					case 1: {//junto caramelo, lo saco del bosque
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
					case 2: {//esta el lobo, entonces retorno el estado inicial pero con una vida menos
						nuevoEstado.initState();
						nuevoEstado.setVidas(vidas-1);
						nuevoEstadoAm.initState();
						return nuevoEstadoAm;
					}
				}
				i++;
			}while(celda != -1 && fila-(i-1)!=0);
			//termine de moverme, actualizo
			bosqueAm[posicion.getFila()][posicion.getColumna()]=0;
			bosque[posicion.getFila()][posicion.getColumna()]=0;
			
			nuevoEstado.setCantidadDeCaramelos(caramelos);
			
			posicion.setFila(celda==-1 ? fila-(i-2) : fila-(i-1));
			
			bosque[posicion.getFila()][posicion.getColumna()]=3;
			
			nuevoEstado.setBosqueCaperucita(bosque);
			nuevoEstado.setPosicion(posicion);
			
			bosqueAm[posicion.getFila()][posicion.getColumna()]=3;
			nuevoEstadoAm.setPosicionCaperucita(new Posicion(nuevoEstado.getPosicion().getFila(), nuevoEstado.getPosicion().getColumna()));
			
			nuevoEstadoAm.setBosqueAmbiente(bosqueAm);
			nuevoEstadoAm.setPosicionCaramelos(posCaramelos);
			return nuevoEstadoAm;
		}
	}

	@Override
	public String toString() {
		return "IrArriba";
	}

}

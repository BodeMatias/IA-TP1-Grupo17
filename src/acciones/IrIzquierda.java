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

public class IrIzquierda extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoCaperucita nuevoEstado = (EstadoCaperucita) s;
		Integer[][] bosque = nuevoEstado.getBosqueCaperucita();
		Posicion posicion = nuevoEstado.getPosicion();
		Integer vidas = nuevoEstado.getVidas();
		Integer caramelos = nuevoEstado.getCantidadDeCaramelos();
		
		//Si no puedo moverme, retorno null
		if(bosque[posicion.getY()][posicion.getX()-1]==-1) {
		return null;	
		} 
		//Sino, empiezo a "mover" a caperucita, pero su estado permanece
		else {
			int fila = posicion.getY();
			int columna = posicion.getX();
			int celda;
			int i = 1;
			do {
				celda = bosque[fila][columna-i];
				switch(celda) {
					case 1: {//junto caramelo, lo saco del bosque
						bosque[fila][columna-i]=0;
						caramelos++;
						break;
					}
					case 2: {//esta el lobo, entonces retorno el estado inicial pero con una vida menos
						nuevoEstado.initState();
						nuevoEstado.setVidas(vidas-1);
						return nuevoEstado;
					}
				}
				i++;
			}while(celda != -1);
			//termine de moverme, actualizo
			bosque[posicion.getY()][posicion.getX()]=0;
			
			nuevoEstado.setCantidadDeCaramelos(caramelos);
			posicion.setX(columna-(i-2));
			
			bosque[posicion.getY()][posicion.getX()]=3;
			
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
		
		Integer[][]bosqueAm = nuevoEstadoAm.getBosqueAmbiente();
		ArrayList<Posicion> posCaramelos = nuevoEstadoAm.getPosicionCaramelos();
		
		Integer[][] bosque = nuevoEstado.getBosqueCaperucita();
		Posicion posicion = nuevoEstado.getPosicion();
		Integer vidas = nuevoEstado.getVidas();
		Integer caramelos = nuevoEstado.getCantidadDeCaramelos();
		
		//Si no puedo moverme, retorno null
		if(bosque[posicion.getY()][posicion.getX()-1]==-1) {
		return null;	
		} 
		//Sino, empiezo a "mover" a caperucita, pero su estado permanece
		else {
			int fila = posicion.getY();
			int columna = posicion.getX();
			int celda;
			int i = 1;
			do {
				celda = bosque[fila][columna-i];
				switch(celda) {
					case 1: {//junto caramelo, lo saco del bosque
						bosque[fila][columna-i]=0;
						bosqueAm[fila][columna-i]=0;
						Iterator<Posicion> iterator = posCaramelos.iterator();
						while(iterator.hasNext()) {
							Posicion p = iterator.next();
							if(p.getY()==fila && p.getX()==columna-i) {
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
			}while(celda != -1);
			//termine de moverme, actualizo
			bosqueAm[posicion.getY()][posicion.getX()]=0;
			bosque[posicion.getY()][posicion.getX()]=0;
			
			nuevoEstado.setCantidadDeCaramelos(caramelos);

			posicion.setX(columna-(i-2));
			
			bosque[posicion.getY()][posicion.getX()]=3;
			
			nuevoEstado.setBosqueCaperucita(bosque);
			nuevoEstado.setPosicion(posicion);
			
			bosqueAm[posicion.getY()][posicion.getX()]=3;
			nuevoEstadoAm.setPosicionCaperucita(new Posicion(nuevoEstado.getPosicion().getY(), nuevoEstado.getPosicion().getX()));
			
			nuevoEstadoAm.setBosqueAmbiente(bosqueAm);
			nuevoEstadoAm.setPosicionCaramelos(posCaramelos);
			return nuevoEstadoAm;
		}
	}

	@Override
	public String toString() {
		return "IrIzquierda";
	}

}

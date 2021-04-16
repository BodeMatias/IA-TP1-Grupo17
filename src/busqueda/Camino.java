//TODO ELIMINAR CLASE

package busqueda;

import java.util.ArrayList;

import dominio.Posicion;

public class Camino {
	private Integer distanciaAObstaculo;
	private Boolean hayLobo;
	private ArrayList<Posicion> posicionCaramelos;
	
	public Integer getDistanciaAObstaculo() {
		return distanciaAObstaculo;
	}
	public void setDistanciaAObstaculo(Integer distanciaAObstaculo) {
		this.distanciaAObstaculo = distanciaAObstaculo;
	}
	public Boolean getHayLobo() {
		return hayLobo;
	}
	public void setHayLobo(Boolean hayLobo) {
		this.hayLobo = hayLobo;
	}
	public ArrayList<Posicion> getPosicionCaramelos() {
		return posicionCaramelos;
	}
	public void setPosicionCaramelos(ArrayList<Posicion> posicionCaramelos) {
		this.posicionCaramelos = posicionCaramelos;
	}
	
	
}

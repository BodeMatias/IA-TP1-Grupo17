package busqueda;

import java.util.ArrayList;

import auxiliar.MatrizBosque;
import dominio.Posicion;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class EstadoCaperucita extends SearchBasedAgentState {
	
	private Integer cantidadDeCaramelos;
	private Posicion posicion;
	private Posicion posicion_objetivo;
	private Integer vidas;
	private Integer[][] bosqueCaperucita;
	private ArrayList<Camino> caminos;
	
	public EstadoCaperucita() {
		initState();
	}
	
	public Integer getCantidadDeCaramelos() {
		return cantidadDeCaramelos;
	}
	public void setCantidadDeCaramelos(Integer cantidadDeCaramelos) {
		this.cantidadDeCaramelos = cantidadDeCaramelos;
	}
	
	public Posicion getPosicion() {
		return posicion;
	}
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	public Integer getVidas() {
		return vidas;
	}
	public void setVidas(Integer vidas) {
		this.vidas = vidas;
	}
	public ArrayList<Camino> getCaminos() {
		return caminos;
	}
	public void setCaminos(ArrayList<Camino> caminos) {
		this.caminos = caminos;
	}
	public Integer[][] getBosqueCaperucita() {
		return bosqueCaperucita;
	}
	public void setBosqueCaperucita(Integer[][] bosqueCaperucita) {
		this.bosqueCaperucita = bosqueCaperucita;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public SearchBasedAgentState clone() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateState(Perception p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void initState() {
		// TODO Auto-generated method stub
		this.bosqueCaperucita = MatrizBosque.bosque;
		this.vidas = 3;
		this.posicion = new Posicion(5, 11);
		this.posicion_objetivo = new Posicion(7, 7);
		this.cantidadDeCaramelos = 0;
	}
	public Posicion getPosicion_objetivo() {
		return posicion_objetivo;
	}
	public void setPosicion_objetivo(Posicion posicion_objetivo) {
		this.posicion_objetivo = posicion_objetivo;
	}


}

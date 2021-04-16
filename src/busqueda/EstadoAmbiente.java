package busqueda;

import java.util.ArrayList;

import dominio.Posicion;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {

	private Integer[][] bosqueAmbiente;
	private Posicion posicionLobo;
	private Posicion posicionCampoFlores;
	private Posicion posicionCaperucita;
	private ArrayList<Posicion> posicionCaramelos;
	
	public Integer[][] getBosqueAmbiente() {
		return bosqueAmbiente;
	}
	public void setBosqueAmbiente(Integer[][] bosqueAmbiente) {
		this.bosqueAmbiente = bosqueAmbiente;
	}
	public Posicion getPosicionLobo() {
		return posicionLobo;
	}
	public void setPosicionLobo(Posicion posicionLobo) {
		this.posicionLobo = posicionLobo;
	}
	public Posicion getPosicionCampoFlores() {
		return posicionCampoFlores;
	}
	public void setPosicionCampoFlores(Posicion posicionCampoFlores) {
		this.posicionCampoFlores = posicionCampoFlores;
	}
	public Posicion getPosicionCaperucita() {
		return posicionCaperucita;
	}
	public void setPosicionCaperucita(Posicion posicionCaperucita) {
		this.posicionCaperucita = posicionCaperucita;
	}
	public ArrayList<Posicion> getPosicionCaramelos() {
		return posicionCaramelos;
	}
	public void setPosicionCaramelos(ArrayList<Posicion> posicionCaramelos) {
		this.posicionCaramelos = posicionCaramelos;
	}
	@Override
	public void initState() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

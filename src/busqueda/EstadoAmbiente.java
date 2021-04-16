package busqueda;

import java.util.ArrayList;

import auxiliar.MatrizBosque;
import dominio.Posicion;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {

	private Integer[][] bosqueAmbiente;
	private Posicion posicionLobo;
	private Posicion posicionCampoFlores;
	private Posicion posicionCaperucita;
	private ArrayList<Posicion> posicionCaramelos;
	
	public EstadoAmbiente() {
		initState();
	}
	
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
		// TODO Hacer esto generico
		this.bosqueAmbiente = MatrizBosque.bosque;
		this.posicionCaperucita = new Posicion(5, 11);
		this.posicionCampoFlores = new Posicion(7, 7);
		this.posicionLobo = new Posicion(6, 4);
		this.posicionCaramelos = new ArrayList<Posicion>();
		posicionCaramelos.add(new Posicion(1, 3));
		posicionCaramelos.add(new Posicion(1, 10));
		posicionCaramelos.add(new Posicion(3, 8));
	}
	@Override
	public String toString() {
		String res = "";
		
		res+="Bosque: \n";
		res+=MatrizBosque.imprimirMatriz(this.bosqueAmbiente);
		res+="\n";
		
		res+="Posicion Campo de Flores: \n";
		res+=this.posicionCampoFlores.toString();
		res+="\n";
		
		res+="Posicion Lobo: \n";
		res+=this.posicionLobo.toString();
		res+="\n";
		
		res+="Posicion Caperucita: \n";
		res+=this.posicionCaperucita.toString();
		res+="\n";
		
		res+="Posicion Caramelos: \n";
		for(Posicion p : this.posicionCaramelos) {
			res+=p.toString();
		}
		res+="\n";
		
		return res;
	}
	
	
}

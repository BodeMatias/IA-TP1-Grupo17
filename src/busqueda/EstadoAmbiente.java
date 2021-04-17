package busqueda;

import java.util.ArrayList;

import auxiliar.MatrizBosque;
import dominio.Posicion;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {

	private int[][] bosqueAmbiente;
	private Posicion posicionLobo;
	private ArrayList<Posicion> posicionCampoFlores;
	private Posicion posicionCaperucita;
	private ArrayList<Posicion> posicionCaramelos;
	
	public EstadoAmbiente() {
		initState();
	}
	
	public int[][] getBosqueAmbiente() {
		return bosqueAmbiente;
	}
	public void setBosqueAmbiente(int[][] bosqueAmbiente) {
		this.bosqueAmbiente = bosqueAmbiente;
	}
	public Posicion getPosicionLobo() {
		return posicionLobo;
	}
	public void setPosicionLobo(Posicion posicionLobo) {
		this.posicionLobo = posicionLobo;
	}
	public ArrayList<Posicion> getPosicionCampoFlores() {
		return posicionCampoFlores;
	}
	public void setPosicionCampoFlores(ArrayList<Posicion> posicionCampoFlores) {
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
		this.bosqueAmbiente = new int[9][14];
		for(int i=0; i<9; i++) {
			this.bosqueAmbiente[i]=MatrizBosque.bosque[i].clone();
		}
		this.posicionCampoFlores = new ArrayList<>();
		this.posicionCaramelos = new ArrayList<Posicion>();
		
		inicializarPosiciones();
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
	
	private void inicializarPosiciones() {
		for(int i = 0; i<9; i++) {
			for(int j=0; j<14; j++) {
				switch(this.bosqueAmbiente[i][j]){
				case 1:{
					this.posicionCaramelos.add(new Posicion(i,j)); break;
				}
				case 2:{
					this.posicionLobo = new Posicion(i, j); break;
				}
				case 3:{
					this.posicionCaperucita = new Posicion(i, j); break;
				}
				case 4:{
					this.posicionCampoFlores.add(new Posicion(i,j)); break;
				}
				}
			}
		}
	}
	
	public void moverLobo(Posicion nuevaPosicion) {
		this.bosqueAmbiente[posicionLobo.getFila()][this.posicionLobo.getColumna()]=0;
		this.bosqueAmbiente[nuevaPosicion.getFila()][nuevaPosicion.getColumna()]=2;
		this.posicionLobo = new Posicion(nuevaPosicion.getFila(), nuevaPosicion.getColumna());
	}
}

package busqueda;

import java.util.ArrayList;

import auxiliar.MatrizBosque;
import dominio.Posicion;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {

	private int[][] bosqueAmbiente;
	private Posicion posicionLobo;
	//Como los campos de flores pueden ocupar varias celdas, es conveniente almacenar todas sus posiciones
	private ArrayList<Posicion> posicionCampoFlores;
	private Posicion posicionCaperucita;
	private ArrayList<Posicion> posicionCaramelos;
	private int vidasCaperucita;
	
	public EstadoAmbiente() {
		//Inicializo el estado del ambiente
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
	public int getVidasCaperucita() {
		return vidasCaperucita;
	}

	public void setVidasCaperucita(int vidasCaperucita) {
		this.vidasCaperucita = vidasCaperucita;
	}

	@Override
	public void initState() {
		this.vidasCaperucita=3;
		this.bosqueAmbiente = new int[9][14];
		//Las matrices se pasan por defecto como referencia con .clone(), pero sus filas se pasan por copia con .clone()
		for(int i=0; i<9; i++) {
			this.bosqueAmbiente[i]=MatrizBosque.bosque[i].clone();
		}
		this.posicionCampoFlores = new ArrayList<>();
		this.posicionCaramelos = new ArrayList<Posicion>();
		//Inicializo las avriables correspondientes a las posiciones
		inicializarPosiciones();
	}
	@Override
	public String toString() {
		String res = "";
		
		res+="Bosque: \n";
		res+=MatrizBosque.imprimirMatriz(this.bosqueAmbiente);
		res+="\n";
		
		res+="Vidas caperucita: \n";
		res+=this.vidasCaperucita;
		res+="\n\n";
		
		res+="Posicion Campo de Flores: \n";
		for(Posicion p : this.posicionCampoFlores) {
			res+=p.toString();
		}
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
					//Si encuentro caramelos, guardo sus posiciones en el arreglo
					this.posicionCaramelos.add(new Posicion(i,j)); break;
				}
				case 2:{
					//Si encuentro al lobo, guardo su actual posicion en la variable correspondiente
					this.posicionLobo = new Posicion(i, j); break;
				}
				case 3:{
					//Si encuentro a caperucita, guardo su posicion en la variable correspondiente
					this.posicionCaperucita = new Posicion(i, j); break;
				}
				case 4:{
					//Si encuentro los campos de flores, guardo sus posiciones en el arreglo.
					this.posicionCampoFlores.add(new Posicion(i,j)); break;
				}
				}
			}
		}
	}
	
	public void moverLobo(Posicion nuevaPosicion) {
		//Marco como vacía la posicion actual del lobo
		this.bosqueAmbiente[posicionLobo.getFila()][this.posicionLobo.getColumna()]=0;
		//Actualizo la nueva posicion del lobo en la matriz
		this.bosqueAmbiente[nuevaPosicion.getFila()][nuevaPosicion.getColumna()]=2;
		//Guardo en la variable la nueva posicion del lobo
		this.posicionLobo = new Posicion(nuevaPosicion.getFila(), nuevaPosicion.getColumna());
	}
}

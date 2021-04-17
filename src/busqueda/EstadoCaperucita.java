package busqueda;

import java.util.ArrayList;

import auxiliar.MatrizBosque;
import dominio.Posicion;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class EstadoCaperucita extends SearchBasedAgentState {
	
	private Integer cantidadDeCaramelos;
	private Posicion posicion;
	private ArrayList<Posicion> posiciones_objetivo;
	private Integer vidas;
	private int[][] bosqueCaperucita;
	//TODO borrar este atributo inutil
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
	public int[][] getBosqueCaperucita() {
		return bosqueCaperucita;
	}
	public void setBosqueCaperucita(int[][] bosqueCaperucita) {
		this.bosqueCaperucita = bosqueCaperucita;
	}
	@Override
	public boolean equals(Object obj) {
		//TODO definir cuando dos estados son iguales. Por ahora solo compara posiciones y cantidad de caramelos
		EstadoCaperucita estadoComparado = (EstadoCaperucita) obj;
		boolean mismaPosicion = estadoComparado.getPosicion().equals(this.posicion);
		boolean mismaCantidadCaramelos = estadoComparado.getCantidadDeCaramelos()==(cantidadDeCaramelos);
		return mismaCantidadCaramelos && mismaPosicion;
	}
	@Override
	public SearchBasedAgentState clone() {
		EstadoCaperucita nuevoEstado = new EstadoCaperucita();
		//Los atributos de tipo primitivo se pasan por copia
		nuevoEstado.setVidas(getVidas());
		nuevoEstado.setCantidadDeCaramelos(getCantidadDeCaramelos());
		//Los atributos que son objetos (incluidos arrays) se deben clonar a mano
		nuevoEstado.setPosicion(new Posicion(this.posicion.getFila(), this.posicion.getColumna()));
		ArrayList<Posicion>nuevasPosicionesObjetivo = new ArrayList<>();
		for(Posicion p : this.posiciones_objetivo) {
			nuevasPosicionesObjetivo.add(new Posicion(p.getFila(), p.getColumna()));
		}
		nuevoEstado.setPosiciones_objetivo(nuevasPosicionesObjetivo);
		int[][] nuevoBosque = new int[9][14];
		for(int i=0; i<9; i++) {
			for(int j=0; j<14; j++) {
				nuevoBosque[i][j]=this.bosqueCaperucita[i][j];
			}
		}
		nuevoEstado.setBosqueCaperucita(nuevoBosque);
		
		return nuevoEstado;
	}
	@Override
	public void updateState(Perception p) {
		ArrayList<ArrayList<Integer>> caminos = ((CaperucitaPercepcion)p).getCaminos();
		int fila = this.posicion.getFila();
		int columna = this.posicion.getColumna();
		//actualizo el camino superior
		for(int i=0; i < caminos.get(0).size(); i++) {
			if(caminos.get(0).get(i)==2) {
				this.sacarLobo();
			}
			this.bosqueCaperucita[fila-i-1][columna] = caminos.get(0).get(i);
		}
		//actualizo el camino derecho
		for(int i=0; i < caminos.get(1).size(); i++) {
			if(caminos.get(1).get(i)==2) {
				this.sacarLobo();
			}
			this.bosqueCaperucita[fila][columna+i+1] = caminos.get(1).get(i);
		}
		//actualizo el camino inferior
		for(int i=0; i < caminos.get(2).size(); i++) {
			if(caminos.get(2).get(i)==2) {
				this.sacarLobo();
			}
			this.bosqueCaperucita[fila+i+1][columna] = caminos.get(2).get(i);
		}
		//actualizo el camino izquierdo
		for(int i=0; i < caminos.get(3).size(); i++) {
			if(caminos.get(3).get(i)==2) {
				this.sacarLobo();
			}
			this.bosqueCaperucita[fila][columna-i-1] = caminos.get(3).get(i);
		}
	}
	@Override
	public String toString() {
		String res = "";
		
		res+="Bosque caperucita: \n";
		res+=MatrizBosque.imprimirMatriz(bosqueCaperucita);
		res+="\n";
		
		res+="Posicion: \n";
		res+=this.posicion.toString();
		res+="\n";
		
		res+="Vidas: \n";
		res+=this.vidas;
		res+="\n";
		
		res+="Cantidad de Caramelos: \n";
		res+=this.cantidadDeCaramelos;
		res+="\n";
		
		return res;
	}
	@Override
	public void initState() {
		// TODO Hacer esto generico
		this.bosqueCaperucita = new int[9][14];
		for(int i=0; i<9; i++) {
			this.bosqueCaperucita[i]=MatrizBosque.bosque[i].clone();
		}
		this.vidas = 3;
		this.posiciones_objetivo = new ArrayList<>();
		this.cantidadDeCaramelos = 0;
		
		this.inicializarPosiciones();
		
	}
	public ArrayList<Posicion> getPosiciones_objetivo() {
		return posiciones_objetivo;
	}
	public void setPosiciones_objetivo(ArrayList<Posicion> posiciones_objetivo) {
		this.posiciones_objetivo = posiciones_objetivo;
	}
	private void sacarLobo() {
		for(int i = 0; i<9; i++) {
			for(int j=0; j<14; j++) {
				if(this.bosqueCaperucita[i][j]==2) {
					this.bosqueCaperucita[i][j]=0;
				}
			}
		}
	}
	
	private void inicializarPosiciones() {
		for(int i = 0; i<9; i++) {
			for(int j=0; j<14; j++) {
				switch(this.bosqueCaperucita[i][j]){
				case 1:{
					this.bosqueCaperucita[i][j]=0; break;
				}
				case 2:{
					this.bosqueCaperucita[i][j]=0; break;
				}
				case 3:{
					this.posicion = new Posicion(i, j); break;
				}
				case 4:{
					this.posiciones_objetivo.add(new Posicion(i,j)); break;
				}
				}
			}
		}
	}


}

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
	public Integer[][] getBosqueCaperucita() {
		return bosqueCaperucita;
	}
	public void setBosqueCaperucita(Integer[][] bosqueCaperucita) {
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
		nuevoEstado.setPosicion(new Posicion(this.posicion.getY(), this.posicion.getX()));
		nuevoEstado.setPosicion_objetivo(new Posicion(this.posicion_objetivo.getY(), this.posicion_objetivo.getX()));
		Integer[][] nuevoBosque = new Integer[9][14];
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
		int fila = this.posicion.getY();
		int columna = this.posicion.getX();
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
	private void sacarLobo() {
		for(int i = 0; i<9; i++) {
			for(int j=0; j<14; j++) {
				if(this.bosqueCaperucita[i][j]==2) {
					this.bosqueCaperucita[i][j]=0;
				}
			}
		}
	}


}

package busqueda;

import java.util.ArrayList;

import auxiliar.MatrizBosque;
import dominio.Posicion;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class EstadoCaperucita extends SearchBasedAgentState {
	
	private int cantidadDeCaramelos;
	private Posicion posicion;
	//El campo de flores puede ocupar una hilera de celdas, por lo tanto debemos tener todas las posiciones
	private ArrayList<Posicion> posicionesObjetivo;
	private int vidas;
	private int[][] bosqueCaperucita;
	
	public EstadoCaperucita() {
		//Inicializo el estado de caperucita
		initState();
	}
	
	public int getCantidadDeCaramelos() {
		return cantidadDeCaramelos;
	}
	public void setCantidadDeCaramelos(int cantidadDeCaramelos) {
		this.cantidadDeCaramelos = cantidadDeCaramelos;
	}
	public Posicion getPosicion() {
		return posicion;
	}
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	public int getVidas() {
		return vidas;
	}
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	public int[][] getBosqueCaperucita() {
		return bosqueCaperucita;
	}
	public void setBosqueCaperucita(int[][] bosqueCaperucita) {
		this.bosqueCaperucita = bosqueCaperucita;
	}
	public ArrayList<Posicion> getPosicionesObjetivo() {
		return posicionesObjetivo;
	}
	public void setPosicionesObjetivo(ArrayList<Posicion> posiciones_objetivo) {
		this.posicionesObjetivo = posiciones_objetivo;
	}
	@Override
	public boolean equals(Object obj) {
		//Dos estados serán iguales si tienen la misma posición y cantidad de caramelos
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
		for(Posicion p : this.posicionesObjetivo) {
			nuevasPosicionesObjetivo.add(new Posicion(p.getFila(), p.getColumna()));
		}
		nuevoEstado.setPosicionesObjetivo(nuevasPosicionesObjetivo);
		int[][] nuevoBosque = new int[9][14];
		//Las matrices se pasan por referencia con .clone(), pero cada una de sus filas se pasa por copia con .clone()
		for(int i=0; i<9; i++) {
			nuevoBosque[i]=this.bosqueCaperucita[i].clone();
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
			//Si en la nueva percepcion está el lobo, tengo que sacarlo de su antigua posicion en la memoria (bosque) de caperucita
			if(caminos.get(0).get(i)==2) {
				this.sacarLobo();
			}
			this.bosqueCaperucita[fila-i-1][columna] = caminos.get(0).get(i);
		}
		//actualizo el camino derecho
		for(int i=0; i < caminos.get(1).size(); i++) {
			//Si en la nueva percepcion está el lobo, tengo que sacarlo de su antigua posicion en la memoria (bosque) de caperucita
			if(caminos.get(1).get(i)==2) {
				this.sacarLobo();
			}
			this.bosqueCaperucita[fila][columna+i+1] = caminos.get(1).get(i);
		}
		//actualizo el camino inferior
		for(int i=0; i < caminos.get(2).size(); i++) {
			//Si en la nueva percepcion está el lobo, tengo que sacarlo de su antigua posicion en la memoria (bosque) de caperucita
			if(caminos.get(2).get(i)==2) {
				this.sacarLobo();
			}
			this.bosqueCaperucita[fila+i+1][columna] = caminos.get(2).get(i);
		}
		//actualizo el camino izquierdo
		for(int i=0; i < caminos.get(3).size(); i++) {
			//Si en la nueva percepcion está el lobo, tengo que sacarlo de su antigua posicion en la memoria (bosque) de caperucita
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
		
		this.bosqueCaperucita = new int[9][14];
		//Las matrices se pasan por referencia con .clone(), pero sus filas se pasan por copia con .clone()
		for(int i=0; i<9; i++) {
			this.bosqueCaperucita[i]=MatrizBosque.bosque[i].clone();
		}
		this.vidas = 3;
		this.posicionesObjetivo = new ArrayList<>();
		this.cantidadDeCaramelos = 0;
		//Inicializo las variables correspondientes a las posiciones y a la matriz de bosque
		this.inicializarPosiciones();
		
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
					//Si encuentro un caramelo, me olvido que está ahi.
					this.bosqueCaperucita[i][j]=0; break;
				}
				case 2:{
					//Si encuentro al lobo, me olvido que está ahi.
					this.bosqueCaperucita[i][j]=0; break;
				}
				case 3:{
					//Si me encuentro a mi misma, lo guardo en la variable posicion.
					this.posicion = new Posicion(i, j); break;
				}
				case 4:{
					//Si encuentro los campos de flores, agrego sus posiciones al Array
					this.posicionesObjetivo.add(new Posicion(i,j)); break;
				}
				}
			}
		}
	}


}

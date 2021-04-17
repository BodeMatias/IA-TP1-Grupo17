package busqueda;

import java.util.ArrayList;
import java.util.List;

import dominio.Ambiente;
import dominio.Posicion;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class CaperucitaPercepcion extends Perception{
	
	private ArrayList<ArrayList<Integer>> caminos;
	
	public CaperucitaPercepcion(){
		caminos = new ArrayList<ArrayList<Integer>>(4);
		caminos.add(new ArrayList<Integer>());
		caminos.add(new ArrayList<Integer>());
		caminos.add(new ArrayList<Integer>());
		caminos.add(new ArrayList<Integer>());
	}
	
	public CaperucitaPercepcion(Agent agent, Environment environment) {
        super(agent, environment);
    }

	@Override
	public void initPerception(Agent agent, Environment environment) {
		Caperucita agente = (Caperucita) agent;
        Ambiente ambiente = (Ambiente) environment;
        EstadoAmbiente environmentState = (EstadoAmbiente) ambiente.getEnvironmentState();
        
        Integer[][] bosque = environmentState.getBosqueAmbiente();
        Posicion posicionCaperucita = environmentState.getPosicionCaperucita();
        //Obtener camino arriba
        int fila = posicionCaperucita.getFila();
        int columna = posicionCaperucita.getColumna();
        Integer celda;
        do {
        	celda = bosque[--fila][columna];
        	this.caminos.get(0).add(celda);
        }while(celda!=-1);
        //Obtener camino derecha
        fila = posicionCaperucita.getFila();
        columna = posicionCaperucita.getColumna();
        do {
        	celda = bosque[fila][++columna];
        	this.caminos.get(1).add(celda);
        }while(celda!=-1);
        //Obtener camino abajo
        fila = posicionCaperucita.getFila();
        columna = posicionCaperucita.getColumna();
        do {
        	celda = bosque[++fila][columna];
        	this.caminos.get(2).add(celda);
        }while(celda!=-1);
        //Obtener camino izquierda
        fila = posicionCaperucita.getFila();
        columna = posicionCaperucita.getColumna();
        do {
        	celda = bosque[fila][--columna];
        	this.caminos.get(3).add(celda);
        }while(celda!=-1);
	}

	@Override
	public String toString() {
		String res = "";
		
		for(int i = 0; i<4; i++) {
			String aux = "\n";
			for(int j=0; j<this.caminos.get(i).size(); j++) {
				aux+=caminos.get(i).get(j);
			}
			res+=aux;
		}
		
		return res;
	}
	
	public ArrayList<ArrayList<Integer>> getCaminos(){
		return this.caminos;
	}
}

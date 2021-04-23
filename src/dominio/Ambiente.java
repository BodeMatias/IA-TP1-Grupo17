package dominio;

import java.util.ArrayList;

import busqueda.CaperucitaPercepcion;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.AgentState;

import java.util.concurrent.ThreadLocalRandom;

public class Ambiente extends Environment{
	
	public Ambiente () {
		this.environmentState = new EstadoAmbiente();
	}
	
	public EstadoAmbiente getEnvironmentState() {
		return (EstadoAmbiente) super.getEnvironmentState();
	}

	@Override
	public Perception getPercept() {
		//El metodo obtiene la nueva posicion del lobo, luego llama al metodo dentro de EstadoAMbiente para efectivamente moverlo
		moverLobo();
        CaperucitaPercepcion percepcion = new CaperucitaPercepcion(); 
        int[][] bosque = ((EstadoAmbiente) this.environmentState).getBosqueAmbiente();
        Posicion posicionCaperucita = ((EstadoAmbiente) this.environmentState).getPosicionCaperucita();
        //Obtener camino arriba
        int fila = posicionCaperucita.getFila();
        int columna = posicionCaperucita.getColumna();
        Integer celda;
        //Si no estoy en el borde del mapa
        if(fila>0) {
        	//"Avanzo" para ver que hay en esta direccion 
        	do {
        		celda = bosque[--fila][columna];
        		percepcion.getCaminos().get(0).add(celda);
        	}
        	//Corta cuando estoy parado en un arbol o cuando llegué al borde del mapa
        	while(celda!=-1 && fila>0);
        }
        //Obtener camino derecha
        fila = posicionCaperucita.getFila();
        columna = posicionCaperucita.getColumna();
        //Si no estoy en el borde del mapa
        if(columna<13) {
        	//"Avanzo" para ver que hay en esta direccion 
        	do {
        		celda = bosque[fila][++columna];
        		percepcion.getCaminos().get(1).add(celda);
        	}
            //Corta cuando estoy parado en un arbol o cuando llegué al borde del mapa
        	while(celda!=-1 && columna<13);
        }
        //Obtener camino abajo
        fila = posicionCaperucita.getFila();
        columna = posicionCaperucita.getColumna();
        //Si no estoy en el borde del mapa
        if(fila<8) {
        	//"Avanzo" para ver que hay en esta direccion 
        	do {
        		celda = bosque[++fila][columna];
        		percepcion.getCaminos().get(2).add(celda);
        	}
            //Corta cuando estoy parado en un arbol o cuando llegué al borde del mapa
        	while(celda!=-1 && fila<8);
        } 
        //Obtener camino izquierda
        fila = posicionCaperucita.getFila();
        columna = posicionCaperucita.getColumna();
        //Si no estoy en el borde del mapa
        if(columna>0) {
        	//"Avanzo" para ver que hay en esta direccion 
        	do {
            	celda = bosque[fila][--columna];
            	percepcion.getCaminos().get(3).add(celda);
            }
            //Corta cuando estoy parado en un arbol o cuando llegué al borde del mapa
        	while(celda!=-1 && columna>0);
        }           
        // Return the perception
        return percepcion;
	}

	public String toString() {
		return this.environmentState.toString();
	}
	
	//Este método indica bajo qué condición se considera que el agente ha fallado
	@Override
    public boolean agentFailed(Action actionReturned) {
		//Caperucita va a fallar si llega a cero vidas
    	EstadoAmbiente estado = (EstadoAmbiente)this.getEnvironmentState();
    	boolean failed = estado.getVidasCaperucita()==0;

        return failed;
    }
    
    private void moverLobo() {
    	int[][]bosque = ((EstadoAmbiente)this.environmentState).getBosqueAmbiente();
    	int fila, columna;
    	//Bosco una posicion valida para el lobo (Celda vacia (Dulce, caperucita y campo de flores cuentan como ocupadas)
    	//y al lado de al menos 1 arbol)
    	do {
    		fila = ThreadLocalRandom.current().nextInt(1, 8);
    		columna = ThreadLocalRandom.current().nextInt(1, 13);
    	}while(!(bosque[fila][columna]==0 && (bosque[fila-1][columna]==-1 
    			|| bosque[fila+1][columna]==-1 
    			|| bosque[fila][columna-1]==-1 
    			|| bosque[fila][columna+1]==-1)));
    	//Llamo al metodo de EstadoAmbiente que efectivamente mueve el lobo
    	((EstadoAmbiente)this.environmentState).moverLobo(new Posicion(fila, columna));
    }
	
}

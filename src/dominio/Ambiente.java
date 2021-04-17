package dominio;

import java.util.ArrayList;

import busqueda.CaperucitaPercepcion;
import busqueda.EstadoAmbiente;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class Ambiente extends Environment{
	
	public Ambiente () {
		this.environmentState = new EstadoAmbiente();
	}
	
	public EstadoAmbiente getEnvironmentState() {
		return (EstadoAmbiente) super.getEnvironmentState();
	}

	@Override
	public Perception getPercept() {
		
        CaperucitaPercepcion percepcion = new CaperucitaPercepcion();
        
        Integer[][] bosque = ((EstadoAmbiente) this.environmentState).getBosqueAmbiente();
        Posicion posicionCaperucita = ((EstadoAmbiente) this.environmentState).getPosicionCaperucita();
        
        //Obtener camino arriba
        int fila = posicionCaperucita.getFila();
        int columna = posicionCaperucita.getColumna();
        Integer celda;
        do {
        	celda = bosque[--fila][columna];
        	percepcion.getCaminos().get(0).add(celda);
        }while(celda!=-1);
        //Obtener camino derecha
        fila = posicionCaperucita.getFila();
        columna = posicionCaperucita.getColumna();
        do {
        	celda = bosque[fila][++columna];
        	percepcion.getCaminos().get(1).add(celda);
        }while(celda!=-1);
        //Obtener camino abajo
        fila = posicionCaperucita.getFila();
        columna = posicionCaperucita.getColumna();
        do {
        	celda = bosque[++fila][columna];
        	percepcion.getCaminos().get(2).add(celda);
        }while(celda!=-1);
        //Obtener camino izquierda
        fila = posicionCaperucita.getFila();
        columna = posicionCaperucita.getColumna();
        do {
        	celda = bosque[fila][--columna];
        	percepcion.getCaminos().get(3).add(celda);
        }while(celda!=-1);
        
        // Return the perception
        return percepcion;
	}

	public String toString() {
		return this.environmentState.toString();
	}
	
	//Este método indica bajo qué condición se considera que el agente ha fallado
    public boolean agentFailed(Action actionReturned) {
    	//TODO Resolver como hacer esto
    	boolean failed = false;

    	//Notar que en este punto tenemos 3 posibilidades inmediatas:
    	//1 - Agregar al estado del ambiente el atributo que nos indica falla (energía)
    	//2 - Agregar un operador que se denomine "apagar" (que vendrá en "actionReturned")
    	//3 - Modificar GoalBasedAgentSimulator para que pase el AgentState en lugar de Action

        return failed;
    }
	
}

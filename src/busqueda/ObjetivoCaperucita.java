package busqueda;

import dominio.Posicion;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoCaperucita extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		//Retorna verdadero sólo si tenemos mas de cero vidas y estamos parados en alguna de las celdas del campo de flores.
		EstadoCaperucita estadoCaperucita = (EstadoCaperucita) agentState;
		
		boolean vidas = estadoCaperucita.getVidas()>0;
		boolean contained = false;
		
		//El metodo contains nos tiraba error, asique hicimos el bucle a mano.
		for(Posicion p : estadoCaperucita.getPosicionesObjetivo()) {
			if(p.equals(estadoCaperucita.getPosicion())) {
				contained=true;
			}
		}
		
		return vidas && contained;
	}

}

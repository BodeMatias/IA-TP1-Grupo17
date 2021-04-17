package busqueda;

import dominio.Posicion;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoCaperucita extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		EstadoCaperucita estadoCaperucita = (EstadoCaperucita) agentState;
		
		boolean vidas = estadoCaperucita.getVidas()>0;
		boolean contained = false;
		for(Posicion p : estadoCaperucita.getPosiciones_objetivo()) {
			if(p.equals(estadoCaperucita.getPosicion())) {
				contained=true;
			}
		}
		
		return vidas && contained;
	}

}

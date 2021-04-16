package busqueda;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoCaperucita extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		EstadoCaperucita estadoCaperucita = (EstadoCaperucita) agentState;
		
		return estadoCaperucita.getVidas() > 0 && 
				estadoCaperucita.getPosicion().equals(estadoCaperucita.getPosicion_objetivo());
	}

}

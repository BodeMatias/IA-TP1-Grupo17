package busqueda;

import java.util.Vector;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class ProblemCaperucita extends Problem{

	public ProblemCaperucita(GoalTest goalTest, SearchBasedAgentState initState, Vector<SearchAction> actions) {
		super(goalTest, initState, actions);
	}
	
	@Override
	public SearchBasedAgentState getAgentState() {
		return super.getAgentState().clone();
	}

}

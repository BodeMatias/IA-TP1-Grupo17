package busqueda;

import acciones.IrAbajo;
import acciones.IrArriba;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class CostStepFuncImpl implements IStepCostFunction{

	@Override
	public double calculateCost(NTree node) {
		SearchAction action = node.getAction();		
		return action.getCost();
	}

}

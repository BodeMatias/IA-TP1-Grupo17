package busqueda;

import dominio.Posicion;
import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class allows to define a function to be used by any
 * informed search strategy, like A Star or Greedy.
 */
public class Heuristic implements IEstimatedCostFunction {

    /**
     * It returns the estimated cost to reach the goal from a NTree node.
     */
    public double getEstimatedCost(NTree node) {
        EstadoCaperucita agState = (EstadoCaperucita) node.getAgentState();
        Posicion posicionCaperucita = agState.getPosicion();
        Posicion posicionCampoFlores = agState.getPosicionesObjetivo().get(0);
        				
        return Math.abs(posicionCampoFlores.getFila()-posicionCaperucita.getFila())*1.0+Math.abs(posicionCampoFlores.getColumna()-posicionCaperucita.getColumna())*1.0;
    }
    
}

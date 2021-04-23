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
        				
        return Math.sqrt(Math.pow((posicionCampoFlores.fila-posicionCaperucita.fila),2)*1.0+Math.pow(posicionCampoFlores.getColumna()-posicionCaperucita.getColumna(),2)*1.0);
    }
    
}

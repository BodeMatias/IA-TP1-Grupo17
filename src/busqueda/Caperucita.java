package busqueda;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import acciones.IrAbajo;
import acciones.IrArriba;
import acciones.IrDerecha;
import acciones.IrIzquierda;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.Search;

public class Caperucita extends SearchBasedAgent{

	public Caperucita (){
		
		ObjetivoCaperucita objetivo = new ObjetivoCaperucita();
		
		EstadoCaperucita estado = new EstadoCaperucita();
		
		this.setAgentState(estado);
		
		Vector<SearchAction> operadores = new Vector<SearchAction>();
		
		operadores.add(new IrArriba());
		operadores.add(new IrAbajo());
		operadores.add(new IrIzquierda());
		operadores.add(new IrDerecha());
		
		Problem problema = new Problem(objetivo, estado, operadores);
		
		this.setProblem(problema);
	}
	
	
	@Override
	public void see(Perception p) {
		// TODO Auto-generated method stub
		this.getAgentState().updateState(p);
	}

	@Override
	public Action selectAction() {
		// TODO Auto-generated method stub
		DepthFirstSearch estrategia = new DepthFirstSearch();
		Search solucionadorBusqueda = new Search(estrategia);
		solucionadorBusqueda.setVisibleTree(Search.EFAIA_TREE);
		this.setSolver(solucionadorBusqueda);
		Action accion = null;
        try {
        	accion = this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(Caperucita.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accion;
	}

}

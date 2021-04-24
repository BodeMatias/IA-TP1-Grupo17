package busqueda;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import acciones.IrAbajo;
import acciones.IrAbajoPerderVida;
import acciones.IrAbajoYJuntarDulceSiHay;
import acciones.IrAbajoYLlegarCampoFloresSiHay;
import acciones.IrArriba;
import acciones.IrArribaPerderVida;
import acciones.IrArribaYJuntarDulceSiHay;
import acciones.IrArribaYLlegarCampoFloresSiHay;
import acciones.IrDerecha;
import acciones.IrDerechaPerderVida;
import acciones.IrDerechaYJuntarDulceSiHay;
import acciones.IrDerechaYLlegarCampoFloresSiHay;
import acciones.IrIzquierda;
import acciones.IrIzquierdaPerderVida;
import acciones.IrIzquierdaYJuntarDulceSiHay;
import acciones.IrIzquierdaYLlegarCampoFloresSiHay;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.solver.search.AStarSearch;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.GreedySearch;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.solver.search.UniformCostSearch;

public class Caperucita extends SearchBasedAgent{
	
	EstadoCaperucita estado;

	public Caperucita (){
		
		ObjetivoCaperucita objetivo = new ObjetivoCaperucita();
		
		estado = new EstadoCaperucita();
		
		this.setAgentState(estado);
		
		Vector<SearchAction> operadores = new Vector<SearchAction>();
		
		operadores.add(new IrArriba());
		operadores.add(new IrIzquierda());
		operadores.add(new IrAbajo());
		operadores.add(new IrDerecha());
		
		operadores.add(new IrArribaYJuntarDulceSiHay());
		operadores.add(new IrIzquierdaYJuntarDulceSiHay());
		operadores.add(new IrAbajoYJuntarDulceSiHay());
		operadores.add(new IrDerechaYJuntarDulceSiHay());
		
		operadores.add(new IrArribaYLlegarCampoFloresSiHay());
		operadores.add(new IrIzquierdaYLlegarCampoFloresSiHay());
		operadores.add(new IrAbajoYLlegarCampoFloresSiHay());
		operadores.add(new IrDerechaYLlegarCampoFloresSiHay());
		
		operadores.add(new IrArribaPerderVida());
		operadores.add(new IrIzquierdaPerderVida());
		operadores.add(new IrAbajoPerderVida());
		operadores.add(new IrDerechaPerderVida());		
		
		ProblemCaperucita problema = new ProblemCaperucita(objetivo, estado, operadores);
		
		this.setProblem(problema);
	}
	
	
	@Override
	public void see(Perception p) {
		this.getAgentState().updateState(p);
	}

	@Override
	public Action selectAction() {
		//BreathFirstSearch estrategia = new BreathFirstSearch();
		//DepthFirstSearch estrategia = new DepthFirstSearch();
		//GreedySearch estrategia = new GreedySearch(new Heuristic());
		AStarSearch estrategia = new AStarSearch(new CostStepFuncImpl(), new Heuristic());
		//UniformCostSearch estrategia = new UniformCostSearch(new CostStepFuncImpl());
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
	
	/*@Override
	public SearchBasedAgentState getAgentState() {
		return this.estado.clone();
	}*/

}

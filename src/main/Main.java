package main;

import auxiliar.MatrizBosque;
import busqueda.Caperucita;
import dominio.Ambiente;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class Main {

	public static void main(String[] args) throws PrologConnectorException{	
		Caperucita agente = new Caperucita();
		
		Ambiente ambiente = new Ambiente();
		
		SearchBasedAgentSimulator searchBasedAgentSimulator = new SearchBasedAgentSimulator(ambiente, agente);
		
		searchBasedAgentSimulator.start();	
	}
	
}



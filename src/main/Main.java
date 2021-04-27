package main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpServer;

import SpritePositions.SpritePositions;
import busqueda.Caperucita;
import busqueda.EstadoAmbiente;
import dominio.Ambiente;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import httphandler.HttpServerHandler;

public class Main {

	public static void main(String[] args) throws PrologConnectorException, IOException{

		SpritePositions spritePositions = SpritePositions.getInstance();
		HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
		
		Caperucita agente = new Caperucita();
		
		Ambiente ambiente = new Ambiente();
		
		SearchBasedAgentSimulator searchBasedAgentSimulator = new SearchBasedAgentSimulator(ambiente, agente);
		
		searchBasedAgentSimulator.start();
		
		System.out.println("Estados finales:\nEstado Caperucita:");
		System.out.println(agente.getAgentState().toString());
		System.out.println("Estado Ambiente:");
		System.out.println(ambiente.getEnvironmentState().toString());
		
		spritePositions.pushStates((EstadoAmbiente) ambiente.getEnvironmentState().clone());
		server.createContext("/states", new HttpServerHandler());
		server.setExecutor(threadPoolExecutor);
		server.start();

		System.out.println("Server started on port 8001");
	}
	
}



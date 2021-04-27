package SpritePositions;

import java.util.ArrayList;

import busqueda.EstadoAmbiente;

public class SpritePositions {
	private ArrayList<EstadoAmbiente> estadosAmbiente;
	private static SpritePositions spritePositions;
	private Boolean llegoAMeta;

	
	public SpritePositions() {
		this.estadosAmbiente = new ArrayList<EstadoAmbiente>();
		this.llegoAMeta = false;
	}

	public static SpritePositions getInstance() {
		
		if(spritePositions == null) {
			spritePositions = new SpritePositions();
		}
		return spritePositions;
	}

	public ArrayList<EstadoAmbiente> getEstadosAmbiente() {
		return estadosAmbiente;
	}

	public void setEstadosAmbiente(ArrayList<EstadoAmbiente> estadosAmbiente) {
		this.estadosAmbiente = estadosAmbiente;
	}
	
	public void pushStates(EstadoAmbiente estadoAmbiente) {
		this.estadosAmbiente.add(estadoAmbiente);
	}

	public Boolean getLlegoAMeta() {
		return llegoAMeta;
	}

	public void setLlegoAMeta(Boolean llegoAMeta) {
		this.llegoAMeta = llegoAMeta;
	}
}

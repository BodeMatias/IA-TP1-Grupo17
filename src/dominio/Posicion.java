package dominio;

public class Posicion {
	
	public Integer columna;
	public Integer fila;
	
	public Posicion(Integer fila, Integer columna) {
		this.columna = columna;
		this.fila = fila;
	}

	public Integer getColumna() {
		return columna;
	}

	public void setColumna(Integer columna) {
		this.columna = columna;
	}

	public Integer getFila() {
		return fila;
	}

	public void setFila(Integer fila) {
		this.fila = fila;
	}
	
	public Boolean equals(Posicion posicion) {
		 return posicion.getColumna()==(columna) && posicion.getFila()==(fila);
	}

	@Override
	public String toString() {
		String aux = "";
		aux+="Fila: "+this.fila+"\n";
		aux+="Columna: "+this.columna+"\n";
		return aux;
	}
}

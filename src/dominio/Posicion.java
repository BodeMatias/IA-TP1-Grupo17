package dominio;

public class Posicion{
	
	public int columna;
	public int fila;
	
	public Posicion(int fila, int columna) {
		this.columna = columna;
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}
	
	public boolean equals(Posicion posicion) {
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

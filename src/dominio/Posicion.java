package dominio;

public class Posicion {
	
	public Integer x;
	public Integer y;
	
	public Posicion(Integer y, Integer x) {
		this.x = x;
		this.y = y;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
	public Boolean equals(Posicion posicion) {
		 return posicion.getX()==(x) && posicion.getY()==(y);
	}

	@Override
	public String toString() {
		String aux = "";
		aux+="Fila: "+this.y+"\n";
		aux+="Columna: "+this.x+"\n";
		return aux;
	}
}

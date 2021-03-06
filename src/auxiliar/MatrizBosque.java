package auxiliar;

public abstract class MatrizBosque {
	
	public static int[][] bosque = 	   {{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
									    {-1, -1, -1,  1,  0,  0,  0, -1,  0,  0,  1, -1, -1, -1},
									    {-1, -1, -1,  0, -1,  0,  0,  0,  0,  0,  0,  0, -1, -1},
									    { 4,  4,  4,  0,  0,  0,  0,  0,  1, -1,  0,  0, -1, -1},
									    {-1, -1, -1, -1, -1,  0,  0,  0, -1,  0,  0,  0, -1, -1},
									    {-1, -1, -1,  0, -1, -1,  0,  0,  0,  0,  0,  0, -1, -1},
									    {-1, -1, -1,  0,  2, -1, -1, -1,  0, -1,  0, -1, -1, -1},
									    {-1, -1, -1,  0,  0,  0, -1,  0,  0,  0,  0, -1, -1, -1},
									    {-1, -1, -1, -1, -1, -1, -1,  3, -1, -1, -1, -1, -1, -1}};

	public static String imprimirMatriz(int[][] matriz) {
		String aux = "";
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<14; j++) {
				if(matriz[i][j]>=0) {
					aux+=" ";
				}
				aux+=matriz[i][j]+" ";
			}
			aux+="\n";
		}
		return aux;
	}
}

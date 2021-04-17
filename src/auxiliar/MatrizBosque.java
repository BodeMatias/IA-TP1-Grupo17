package auxiliar;

public class MatrizBosque {
//	public static Integer[][] bosque = {{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//									    {-1, -1, -1,  1,  0,  0,  0, -1,  0,  0,  1, -1, -1, -1},
//									    {-1, -1, -1,  0, -1,  0,  0,  0,  0,  0,  0,  0, -1, -1},
//									    {-1, -1, -1,  0,  0,  0,  0,  0,  1, -1,  0,  0, -1, -1},
//									    {-1, -1, -1, -1, -1,  0,  0,  0, -1,  0,  0,  0, -1, -1},
//									    {-1, -1, -1,  0, -1, -1,  0,  0,  0,  0,  0,  3, -1, -1},
//									    {-1, -1, -1,  0,  2, -1, -1, -1,  0, -1,  0, -1, -1, -1},
//									    {-1, -1, -1,  0,  0,  0, -1,  4,  0,  0,  0, -1, -1, -1},
//									    {-1, -1, -1, -1, -1, -1, -1,  4, -1, -1, -1, -1, -1, -1}};
	
	public static Integer[][] bosque = {{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
									    {-1, -1, -1,  1,  0,  0,  0, -1,  0,  0,  1, -1, -1, -1},
									    {-1, -1, -1,  0, -1,  0,  0,  0,  0,  0,  0,  2, -1, -1},
									    {-1, -1, -1,  0,  0,  0,  0,  0,  1, -1,  0,  0, -1, -1},
									    {-1, -1, -1, -1, -1,  0,  0,  0, -1,  0,  0,  0, -1, -1},
									    {-1, -1, -1,  0, -1, -1,  0,  0,  0,  0,  0,  3, -1, -1},
									    {-1, -1, -1,  0,  0, -1, -1, -1,  0, -1,  0, -1, -1, -1},
									    {-1, -1, -1,  0,  0,  0, -1,  4,  0,  0,  0, -1, -1, -1},
									    {-1, -1, -1, -1, -1, -1, -1,  4, -1, -1, -1, -1, -1, -1}};

	public static String imprimirMatriz(Integer[][] matriz) {
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

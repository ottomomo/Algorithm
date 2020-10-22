import java.lang.Math;
/**
 * @author DA18, Octavio Sales Calvo
 *Problema 30, Aibofobia
 */
public class Aibofobia extends SolverJudgeA {
	
	private String palabra;
	
	public Aibofobia(TipoSolucion tipo){
		super(tipo);
	}
	
	public Aibofobia(String p) {
		palabra=p;
	}

	/*
	 * funcion palindromo(), calcula el minimo numero de letras a añadir a una palabra para convertirla en un palindormo.
	 * recursion palindormo(i,j) siendo 'i' 'j' el indice de las letras a comparar. i= inicio, j=final
	 * 			p(i,j)= 0     si i>j ó j=i  		*CASOS BASE
	 * 			p(i,j)= min(p(i,j-1), p(i+1,j))+1   * si v[i]!=v[j] *si NO son la misma letra
	 * 			p(i,j)= p(i+1,j)	  				* si v[i]=v[j]  *si son la misma letra
	 * */
	int palindromo(){
		int matriz [][]= new int [palabra.length()][palabra.length()];
		/*CASOS BASE
		for(int i=0;i<=palabra.length();i++){
			matriz[i][i]=0;
		}*/
		String fila, col;
		
		for(int i=0;i<palabra.length()-1;i++){
			int z=0;
			for(int j=i+1;j<palabra.length();j++){
				fila=palabra.substring(z, z+1);
				col=palabra.substring(j,j+1);
				if(z>=j){
					matriz[z][j]=0;
				}else if(fila.equalsIgnoreCase(col)){
					matriz[z][j]= matriz[z+1][j-1];
				}else{
					matriz[z][j]= Math.min(matriz[z][j-1], matriz[z+1][j])+1;
				}
				z++;
			}
			
		}
		return matriz[0][palabra.length()-1];
	}
	/* (non-Javadoc)
	 * @see SolverJudgeA#solutionCase()
	 */
	@Override
	public boolean solutionCase() {
		while(sc.hasNext()){
			String p;
			p=sc.next();
			Aibofobia a= new Aibofobia(p);
			System.out.println(a.palindromo());
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Aibofobia juez = new Aibofobia(SENTINEL);
		juez.run();

	}

}

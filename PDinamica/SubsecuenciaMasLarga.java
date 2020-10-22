/**
 * 
 */

/**
 * @author Octavio Sales, DA18
 * Subsecuencia Mas Larga, 31
 */
public class SubsecuenciaMasLarga extends SolverJudgeA {

	/**
	 * 
	 */
	public SubsecuenciaMasLarga() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 */
	public SubsecuenciaMasLarga(TipoSolucion solutionType) {
		super(solutionType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 * @param fileName
	 */
	public SubsecuenciaMasLarga(TipoSolucion solutionType, String fileName) {
		super(solutionType, fileName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see SolverJudgeA#solutionCase()
	 */
	@Override
	public boolean solutionCase() {
		while(sc.hasNext()){
			String palabra1, palabra2;
			palabra1=sc.next();
			palabra2=sc.next();
			System.out.println(subsecuencia(palabra1, palabra2));
		}
		return true;
	}

	/**
	 * f(i,j) = halla la subsecuencia mas larga siendo i una letra de la palabra1 y j otra de la palabra2
	 * Caso base: f(i,j)=0  si: i=(palabra1.length) ó j=(palabra2.length). los indices ya han recorrido toda la palabra.
	 * Caso recursivo: max(f(i+1,j), f(i,j+1))  si: pi != pj
	 * 					1+(f(i+1,j+1))			si: pi == pj
	 * @param palabra1
	 * @param palabra2
	 * @return subsecuencia mas larga
	 * Coste:
	 - En tiempo O(n1*n2) siendo n1 el tamaño de la palabra 1 y n2 el tamaño de la palabra 2
	 - En espacio O(n1*n2) siendo n1 el tamaño de la palabra 1 y n2 el tamaño de la palabra 2
	 */
	private int subsecuencia(String palabra1, String palabra2) {
		// TODO Auto-generated method stub
		int [][] matriz = new int [palabra1.length()+1][palabra2.length()+1];
		//CASOS BASE:
		//for (int i = 0; i < palabra1.length()-1; i++) {matriz[i][palabra2.length()]=0;}
		//for(int i=0;i<palabra2.length()-1;++i){	matriz[palabra1.length()][i]=0;}
		
		for (int i = palabra1.length()-1; i >=0 ; i--) {
			for (int j = palabra2.length()-1; j >=0 ; j--) {
				if(palabra1.charAt(i)!=palabra2.charAt(j)){
					matriz[i][j]=Math.max(matriz[i+1][j], matriz[i][j+1]);
				}else{
					matriz[i][j]= matriz[i+1][j+1]+1;
				}
			}
			
		}
		return matriz[0][0];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubsecuenciaMasLarga s= new SubsecuenciaMasLarga(SENTINEL);
		s.run();

	}

}

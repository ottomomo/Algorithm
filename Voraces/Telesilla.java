import java.util.Arrays;

/**
 * 
 */

/**
 * @author DA18, Octavio Sales
 * Problema Telesilla
 */
public class Telesilla extends SolverJudgeA {

	/**
	 * 
	 */
	public Telesilla() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 */
	public Telesilla(TipoSolucion solutionType) {
		super(solutionType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 * @param fileName
	 */
	public Telesilla(TipoSolucion solutionType, String fileName) {
		super(solutionType, fileName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see SolverJudgeA#solutionCase()
	 */
	@Override
	public boolean solutionCase() {
		// TODO Auto-generated method stub
		int p=0, n=0;
		while(sc.hasNextInt()){
			p=sc.nextInt();
			n=sc.nextInt();
			int [] pesos = new int[n];
			for(int i=0;i<n;++i){
				pesos[i]=sc.nextInt();
			}
			Arrays.sort(pesos);// ordenacion quicksort creciente
			System.out.println(voraz(p, pesos));
		}
		return true;
	}

	private  int voraz(int p, int[] pesos) {
		// TODO Auto-generated method stub
		int cnt=0, j=0;
		for(int i=pesos.length-1; i>=j;--i){
			if(p>= (pesos[j]+pesos[i])){
				cnt++;
				j++;
			}else{
				cnt++;
			}
		}
		return cnt;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Telesilla t = new Telesilla(SENTINEL);
		t.run();
	}

}

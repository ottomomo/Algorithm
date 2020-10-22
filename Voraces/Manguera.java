/**
 * 
 */

/**
 * @author DA18, Ocatvio Sales Calvo
 * Problema Parches en la manguera
 */
public class Manguera extends SolverJudgeA {

	/**
	 * 
	 */
	public Manguera() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 */
	public Manguera(TipoSolucion solutionType) {
		super(solutionType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 * @param fileName
	 */
	public Manguera(TipoSolucion solutionType, String fileName) {
		super(solutionType, fileName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see SolverJudgeA#solutionCase()
	 */
	@Override
	public boolean solutionCase() {
		// TODO Auto-generated method stub
		int n=0, l=0, a=0;
		while(sc.hasNextInt()){
			n=sc.nextInt();
			l=sc.nextInt();
			int [] m= new int[n];
			for(int i=0;i<n;++i){
				a=sc.nextInt();
				m[i]=a;
			}
			System.out.println(voraz(l,m));
		}
		return true;
	}

	private int voraz(int l, int[] m) {
		// TODO Auto-generated method stub
		int cnt=0, acum=0;
		for(int i=0;i<m.length;++i){
			if(acum< m[i]){
				cnt++;
				acum = m[i]+l;
			}
		}
		return cnt;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manguera juez = new Manguera(SENTINEL);
		juez.run();

	}

}

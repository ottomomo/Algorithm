import java.util.Arrays;

/**
 * 
 */

/**
 * @author DA18, Octavio Sales Calvo
 * Problema Nos invaden!
 */
public class Invasion extends SolverJudgeA {

	/**
	 * 
	 */
	public Invasion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 */
	public Invasion(TipoSolucion solutionType) {
		super(solutionType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 * @param fileName
	 */
	public Invasion(TipoSolucion solutionType, String fileName) {
		super(solutionType, fileName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see SolverJudgeA#solutionCase()
	 */
	@Override
	public boolean solutionCase() {
		// TODO Auto-generated method stub
		int n=0;
		while(sc.hasNext()){
			n=sc.nextInt();
			int [] a= new int [n];
			int [] d= new int [n];
			for(int i=0;i<n;++i){
				a[i]=sc.nextInt();
			}
			for(int i=0;i<n;++i){
				d[i]=sc.nextInt();
			}
			Arrays.sort(a);
			Arrays.sort(d);
			System.out.println(voraz(a,d));
		}
		return true;
	}

	private int voraz(int[] a, int[] d) {
		// TODO Auto-generated method stub
		int cnt=0, j=a.length-1;
		for(int i=a.length-1;i>=0;--i){
			if(a[i]<=d[j]){
				cnt++;
				j--;
			}
		}
		return cnt;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Invasion in = new Invasion(SENTINEL);
		in.run();

	}

}

import java.util.Arrays;

/**
 * 
 */

/**
 * @author DA18, Octavio Sales
 * Problema los Broncos de Boston
 */
public class Boston extends SolverJudgeA {

	/**
	 * 
	 */
	public Boston() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 */
	public Boston(TipoSolucion solutionType) {
		super(solutionType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 * @param fileName
	 */
	public Boston(TipoSolucion solutionType, String fileName) {
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
		n=sc.nextInt();
		while(n>0){
			int [] r= new int [n];
			int [] b = new int [n];
			for(int i=0;i<n;++i){
				r[i]=sc.nextInt();
			}
			for(int i=0;i<n;++i){
				b[i]=sc.nextInt();
			}
			Arrays.sort(r);
			Arrays.sort(b);
			System.out.println(voraz(r,b));
			n=sc.nextInt();
		}
		
		return true;
	}

	private int voraz(int[] r, int[] b) {
		// TODO Auto-generated method stub
		int dif=0, j=0;
		for(int i=b.length-1;i>=0;--i){
			if(b[i]>r[j]){
				dif +=b[i]-r[j];
			}else{
				i=0;
			}
			j++;
		}
		return dif;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Boston b= new Boston(SENTINEL);
		b.run();

	}

}

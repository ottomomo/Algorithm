import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 
 */

/**
 * @author DA18, Octavio Sales
 * Problema Planifiacion de conferencias
 */
public class Conferencias extends SolverJudgeA {

	/**
	 * 
	 */
	public Conferencias() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 */
	public Conferencias(TipoSolucion solutionType) {
		super(solutionType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 * @param fileName
	 */
	public Conferencias(TipoSolucion solutionType, String fileName) {
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
		while(n!=0){
			Conferencia [] clases= new Conferencia [n];
			int ini, fin;
			for(int i=0;i<n;++i){
				ini=sc.nextInt();
				fin=sc.nextInt();
				Conferencia c= new Conferencia(ini, fin);
				
				clases[i]=c;
				
			}
			Comparadora cmp = new Comparadora();
			Arrays.sort(clases,cmp);
			System.out.println(voraz(clases));
			n=sc.nextInt();
		}
		return true;
	}

	private int voraz(Conferencia[] conferencias) {
		// TODO Auto-generated method stub
		Comparadora cmp = new Comparadora();
		PriorityQueue<Conferencia> q= new PriorityQueue<Conferencia>(cmp);
		q.add(conferencias[0]);
		for(int i=1;i<conferencias.length;++i){
			if(conferencias[i].horaIni<q.peek().horaFin){
				q.add(conferencias[i]);
			}else{
				q.poll();
				q.add(conferencias[i]);
			}	
		}
		return q.size();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conferencias c= new Conferencias(SENTINEL);
		c.run();

	}

}

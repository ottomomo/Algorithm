import java.util.ArrayList;

/**
 * 
 */

/**
 * @author DA18, Octavio Sales Calvo
 *	Problema Caza tesoros
 */
public class CTesoro extends SolverJudgeA {

	private ArrayList<Tesoro> tesoros; // peso, valor, marcado
	private int cont;
	private int capacidad;
	/**
	 * 
	 */
	public CTesoro(ArrayList<Tesoro> t, int c) {
		// TODO Auto-generated constructor stub
		this.tesoros=t;
		this.capacidad=c;
		fnCoste();
	}

	private void fnCoste(){
		int [][] matrix= new int [tesoros.size()+1][capacidad+1];
		ArrayList<Tesoro> cofres= new ArrayList<Tesoro>(tesoros.size());
		//casos BASE:
		for(int i=0;i<=tesoros.size();++i)	matrix[i][0]=0;
		for(int i=0;i<=capacidad;++i)	matrix[0][i]=0;
		//MATRIZ
		for(int i=1;i<=tesoros.size();++i){
			for(int j=1; j<=capacidad;++j){
				if(tesoros.get(i-1).profundidad > j){
					matrix[i][j]=matrix[i-1][j];
				}else{
						matrix[i][j]=Math.max( matrix[i-1][j], matrix[i-1][j-tesoros.get(i-1).profundidad]+tesoros.get(i-1).valor);
						// si cojo el cofre...
						if(matrix[i][j]== matrix[i][j-tesoros.get(i-1).profundidad]+tesoros.get(i-1).valor){
							if(!cofres.contains(tesoros.get(i-1)))
								cofres.add(tesoros.get(i-1));
						}
				}
			}
		}
		System.out.println(matrix[tesoros.size()][capacidad]);
		System.out.println(cofres.size());
		for (Tesoro t : cofres){
			System.out.println(t.profundidad/3+" "+t.valor);
		}
		System.out.println("----");
	}
	/**
	 * @param solutionType
	 */
	public CTesoro(TipoSolucion solutionType) {
		super(solutionType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 * @param fileName
	 */
	public CTesoro(TipoSolucion solutionType, String fileName) {
		super(solutionType, fileName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see SolverJudgeA#solutionCase()
	 */
	@Override
	public boolean solutionCase() {
		// TODO Auto-generated method stub
		int t=0, c=0;
		while(sc.hasNextInt()){
			c=sc.nextInt();
			t=sc.nextInt();
			ArrayList<Tesoro> tes = new ArrayList<Tesoro>(t);
			int v, p;
			for(int i=0;i<t;++i){
				p= sc.nextInt();
				v= sc.nextInt();
				Tesoro e = new Tesoro(p, v);
				tes.add(e);
			}
			CTesoro dinamico = new CTesoro(tes, c);
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CTesoro c = new CTesoro(SENTINEL);
		c.run();

	}

}

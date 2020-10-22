import edu.princeton.cs.algs4.Graph;

/**
 * 
 */

/**
 * @author DA_18, Octavio Sales Calvo
 * Problema 9, Amigos de mis Amigos...
 */
public class Amigos extends SolverJudgeA {

	private boolean marked[];
	private int[] edgeTo;        // contador de vertices
	int max;
	/**
	 * 
	 */
	public Amigos(Graph g) {
		max=0;
		edgeTo = new int[g.V()];
		marked= new boolean[g.V()];
		for(int i=0; i<g.V();++i){
			edgeTo[i]=1;
			dfs(g,i);
			if(edgeTo[i]>max){
				max=edgeTo[i];
			}
		}
	}

	// depth first search from v
		/*
		 * Metodo que hace una  busqueda en profundidad del grafo
		 * @param g grafo a analizar
		 * @param v vertice del grafo por donde empieza la busqueda
		 * 
		 * Coste: O(n) siendo n el numero de vectores del grafo.
		 * */
	    private void dfs(Graph g, int v) {
	        this.marked[v] = true;
	        for (int w : g.adj(v)) {
	            if (!marked[w]) {
	            	edgeTo[w]=edgeTo[v]+1;
	                dfs(g,w);
	                edgeTo[v]=edgeTo[w];
	            }
	        }
	    }
	/**
	 * @param solutionType
	 */
	public Amigos(TipoSolucion solutionType) {
		super(solutionType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 * @param fileName
	 */
	public Amigos(TipoSolucion solutionType, String fileName) {
		super(solutionType, fileName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see SolverJudgeA#solutionCase()
	 */
	@Override
	public boolean solutionCase() {
		int n,m, v,w;
		n = sc.nextInt();
		m = sc.nextInt();
		Graph g= new Graph (n);
		for (int i = 0; i < m; i++) {
			v = sc.nextInt();
			w = sc.nextInt();
			g.addEdge(v-1, w-1);
		}
		Amigos a= new Amigos(g);
		System.out.println(a.max);
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Amigos juez = new Amigos(QUANTY);
		juez.run();

	}

}

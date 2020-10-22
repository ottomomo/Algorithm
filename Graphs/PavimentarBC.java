/**
 * Pavimentar Barrio city
 */
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.KruskalMST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;
/**
 * @author DA18, Octavio Sales Calvo
 *
 */
public class PavimentarBC extends SolverJudgeA {

	private KruskalMST k;
	
	/**
	 * Constructora de la clase, la cual comprueba si es posible pavimentar la city y su coste.
	 * 
	 * @param grafo valorado
	 */
	public PavimentarBC(EdgeWeightedGraph g) {
		// TODO Auto-generated constructor stub
		pavimentar(g);
	}
	/**
	 *  metdo pavimentar: donde se realiza el algoritmo de Kruskal, 
	 * el cual permite hallar un arbol de recubrimiento minimo de un grafo valorado.
	 * @param g
	 */
	private void pavimentar(EdgeWeightedGraph g) {
		// TODO Auto-generated method stub
		this.k = new KruskalMST(g);
		Queue<Edge> m =(Queue<Edge>) k.edges();
		if(!esArbol(g) || (m.size()<(g.V()-1))){
			System.out.println("imposible");
		}else{
			int w= (int) k.weight();
			System.out.println(w);
		}
	}
	/**
	 * Metodo que comprueba que un grafo es arborescente.
	 * @param G
	 * @return true en caso de que Sí sea, false en caso contrario.
	 */
	private boolean esArbol(EdgeWeightedGraph G) {
		// check that it is acyclic
        UF uf = new UF(G.V());
        for (Edge e : k.edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                //System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

		 // check that it is a spanning forest
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                //System.err.println("Not a spanning forest");
                return false;
            }
        }
        return true;
	}
	/**
	 * @param solutionType
	 */
	public PavimentarBC(TipoSolucion solutionType) {
		super(solutionType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 * @param fileName
	 */
	public PavimentarBC(TipoSolucion solutionType, String fileName) {
		super(solutionType, fileName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see SolverJudgeA#solutionCase()
	 */
	@Override
	public boolean solutionCase() {
		// TODO Auto-generated method stu
		int v=0, e1=0, e2=0, w=0, a=0;
		while(sc.hasNextInt()){
		v=sc.nextInt();
		a=sc.nextInt();
		EdgeWeightedGraph g= new EdgeWeightedGraph(v);
		for(int i=0;i<a;++i){
			e1=sc.nextInt();
			e2=sc.nextInt();
			w=sc.nextInt();
			Edge e= new Edge(e1-1, e2-1, w);
			g.addEdge(e);
		}
		PavimentarBC c = new PavimentarBC(g);
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PavimentarBC bc = new PavimentarBC(SENTINEL);
		bc.run();
	}

}

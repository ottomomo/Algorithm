import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * 
 */

/**
 * @author DA_18, Octavio Sales Calvo
 * Problema 17, Repartiendo paquetes
 *
 */
public class Paquetes extends SolverJudgeA {
	
	private double[] distTo;          // distTo[v] = distance  of shortest s->v path
	private DirectedEdge[] edgeTo;    // edgeTo[v]  last edge on shortest s->v path
	private IndexMinPQ<Double> pq;    // priority queue of vertices
	private double cost;

	/**
	 *  Dijkstra's algorithm with a binary heap.
	 *  The constructor takes time proportional to E log V,
	 * @param targets 
	 * @param p 
	 * @param o 
	 * @param g 
	 * 
	 */
	public Paquetes(EdgeWeightedDigraph g, int o, int p, int[] targets) {
		distTo = new double[g.V()];
		edgeTo = new DirectedEdge[g.V()];
		cost=0;
		validateVertex(o);

		for (int v = 0; v < g.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[o] = 0.0;
		// relax vertices in order of distance from s
		pq = new IndexMinPQ<Double>(g.V());
		pq.insert(o, distTo[o]);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			for (DirectedEdge e : g.adj(v))
				relax(e);
		}
		for(int i=0;i<targets.length;i++){
			cost += distTo[targets[i]];
		}
	}

	/**
	 * @param solutionType
	 */
	public Paquetes(TipoSolucion solutionType) {
		super(solutionType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 * @param fileName
	 */
	public Paquetes(TipoSolucion solutionType, String fileName) {
		super(solutionType, fileName);
		// TODO Auto-generated constructor stub
	}

	
	  // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    // relax edge e and update pq if changed
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }
    
	/* (non-Javadoc)
	 * @see SolverJudgeA#solutionCase()
	 */
	@Override
	public boolean solutionCase() {
		int v, w, weight;
		int e;
		while (sc.hasNextInt()) {
			v = sc.nextInt();
			e = sc.nextInt();
			EdgeWeightedDigraph g2= new EdgeWeightedDigraph (v);//Grafo invertido
			EdgeWeightedDigraph g= new EdgeWeightedDigraph (v);
			for (int i = 0; i < e; i++) {
				v = (sc.nextInt())-1;
				w = (sc.nextInt())-1;
				weight= sc.nextInt();
				DirectedEdge ed1= new DirectedEdge(v, w, weight);
				g.addEdge(ed1);
				DirectedEdge ed2= new DirectedEdge(w, v, weight);
				g2.addEdge(ed2);
			}
			
			int o = sc.nextInt()-1;
			int p = sc.nextInt();
			int[] targets = new int [p];
			for(int i=0;i<p;++i){
				targets[i]=sc.nextInt()-1;
			}
			Paquetes paq= new Paquetes(g, o,p,targets);
			double c=paq.cost;
			paq=new Paquetes(g2,o,p,targets);//Dijkstra Grafo invertido.
			c+=paq.cost;
			if(c<Double.POSITIVE_INFINITY)
				System.out.println((int)c);
			else
				System.out.println("Imposible");
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Paquetes juez= new Paquetes(SENTINEL);
		juez.run();

	}

}

import edu.princeton.cs.algs4.DepthFirstSearch;
import edu.princeton.cs.algs4.Graph;




public class Bipartito extends SolverJudgeA{
	
	private boolean color [];
	private boolean marked[];
	private boolean ok;
	
	/*Constructor de Bipartito : genera un oobjeto de la clase. instancia los atributos de la clase,
	 *  y despues llama al metodo dfs, donde se ralizará una busqueda en profundidad del grafo pasado por parametro.
	 *  @param g: grafo a analizar
	 *  
	 * */
	public Bipartito(Graph g){
		this.marked = new boolean[g.V()];
		this.color = new boolean [g.V()]; // false = black; true = white; 
		color[0]=true;
		ok=true;
		for(int i=0; i<g.V() && ok;i++){
			dfs(g,i);
		}
	}
      
	
	public Bipartito(TipoSolucion tipo) {
		super(tipo);
	}
	
	
	// depth first search from v
	/*
	 * Metodo que indica si un grafo es bipartito, haciendo una busqueda en profundidad del grafo
	 * @param g grafo a analizar
	 * @param v vertice del grafo por donde empieza la busqueda
	 * 
	 * Coste: O(n) siendo n el numero de vectores del grafo.
	 * */
    private void dfs(Graph g, int v) {
        this.marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
            	this.color[w]= !this.color[v];
                dfs(g,w);
            }else{
            	if(color[w]== color[v]){
            		this.ok=false;
            	}
            }
        }
    }
    
    /*
     * Metodo que devuelve el valor del atributo 'ok' , el cual inidica si el grafo es bipartito
     * @return true si 'G' es bipartito */
    public boolean isBp(){
    	return this.ok;
    }
	
	
	@Override
	public boolean solutionCase() {
		int v, w;
		int e;
		while (sc.hasNextInt()) {
			v = sc.nextInt();
			e = sc.nextInt();
			Graph g= new Graph (v);
			for (int i = 0; i < e; i++) {
				v = sc.nextInt();
				w = sc.nextInt();
				g.addEdge(v, w);
			}
			Bipartito bp = new Bipartito(g);
			if(bp.isBp()){
				System.out.println("SI");
			}else{
				System.out.println("NO");
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Bipartito juez = new Bipartito(TipoSolucion.SENTINEL) ;
		juez.run();
	}
}

import edu.princeton.cs.algs4.DepthFirstSearch;

/* 18, Octavio Sales Calvo*/


public class Oveja extends SolverJudgeA {
	
	private boolean marked[][]; // marked[v] = is there an s-v path?
	private int ovejasBlancas;  // Componentes conexas del grafo
	
	
	/*Constructor:  genera un objeto de clase, instanciando 
	 * la clase padre con el tipo de solucion.
	 * @param tipo: TipoSolucion del juez.
	 * */
	public Oveja(TipoSolucion tipo) {
		super(tipo);
	}

	/*Constructor de Bipartito : genera un objeto de la clase. instancia los atributos de la clase,
	 *  y despues llama al metodo dfs, donde se ralizará una busqueda en profundidad del grafo pasado por parametro.
	 *  Recorrerá todo la matriz indicando todas la componentes conexas que existen.
	 *  @param foto: matriz que emula el grafo a analizar
	 *  @param row: filas de la matriz
	 *  @param col: columnas de la matriz
	 *  Coste O(V), donde v es el numero de vertices.
	 * */
	public Oveja(String[][] foto, int row, int col) {
		this.marked=new boolean[row][col];
		this.ovejasBlancas=0;
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(!marked[i][j] && !foto[i][j].equals("X")){
					ovejasBlancas++;
					dfs(foto, i, j, row,col);
				}
			}
		}
	}

	/*Busqueda en profundidad de una matriz que emula un grafo, donde el vertice es g[i][j].
	 * @param g: matriz a analizar
	 * @param i: posicion 'i' del vertice en la matriz
	 * @param j: posicion 'j' del vertice en la matriz
	 * @param row: filas de la matriz
	 * @param col: columnas de la matriz
	 * */
	private void dfs(String[][] g,int i, int j, int row, int col) {
		 marked[i][j] = true;
		 if(i<row-1){
			 if(!this.marked[i+1][j] && (!g[i+1][j].equals("X"))){
				 dfs(g, i+1, j, row, col);
			 }
		 }if(i>0){
			 if(!this.marked[i-1][j] && (!g[i-1][j].equals("X"))){
				 dfs(g, i-1, j, row, col);
			 }
		 }if(j>0){
			 if(!this.marked[i][j-1] && (!g[i][j-1].equals("X"))){
				 dfs(g, i, j-1, row, col);
			 }
		 }if(j<col-1){
			 if(!this.marked[i][j+1] && (!g[i][j+1].equals("X"))){
				 dfs(g, i, j+1, row, col);
			 }
		 }
	}

	/*Metodo que lee y codifica los datos de entrada, seguidamente hace una llamada al constructor de la clase.
	 * Y finalmente mostrará por pantalla el numero de ovejas blancas que hay en la foto. */
	@Override
	public boolean solutionCase() {
		String [][] foto;
		String columna;
		int row, col;
		while(sc.hasNextInt()){
			col=sc.nextInt();
			row=sc.nextInt();
			foto= new String[row][col];
			for(int i=0;i<row;i++){
				columna=sc.next();
				for(int j=0;j<col;j++){
					foto[i][j]=columna.substring(j, j+1);
				}
			}
			Oveja ob= new Oveja(foto, row, col);
			System.out.println(ob.ovejasBlancas-1);// componenetes conexas menos 1 (que es el borde de la foto)
		}
		return true;
	}

	public static void main(String[] args) {
		Oveja juez = new Oveja(SENTINEL);
		juez.run();

	}

}

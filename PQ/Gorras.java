import java.util.PriorityQueue;



public class Gorras extends SolverJudgeA{

	private PriorityQueue<Long> cola;
	
	
	/*Constructora de Gorras: genera un objeto de clase, instanciando su atributo.
	 * @param nElems: numero de elementos del atributo cola.
	 * */
	public Gorras(int centinela) {
		cola = new PriorityQueue<Long>(centinela);
	}
	
	/*Constructora de Gorras: genera un objeto de clase, instanciando 
	 * la clase padre con el tipo de solucion.
	 * @param tipo: TipoSolucion del juez.
	 * */
	public Gorras(TipoSolucion tipo) {
		super(tipo);
	}


	/* funcion minimo: funcion que calcula el minimo numero de gorras necesarias para realizar todos los partidos.
	 * Siendo cada elemento de la cola un equipo.
	 * @param cola: cola con prioridad greater.
	 * @return el numero de gorras
	 * Coste: O(n) donde n es el numero de elementos de la cola.
	 * */
	public Long minimo() {
		long gorras = 0, e1 = 0, e2 = 0;
		while ((cola.size()) > 1) {
			e1 = cola.poll();
			gorras += e1;
			e2 = cola.poll();
			gorras += e2;
			cola.add(e1 + e2);
		}
		return gorras;

	}
	
	public boolean add (long e){
		return cola.add(e);
	}

	@Override
	public boolean solutionCase() {
		int centinela = sc.nextInt();
		long e;
		while (centinela != 0) {
			Gorras g = new Gorras(centinela);
			for (int i = 0; i < centinela; i++) {
				e = sc.nextInt();
				g.add(e);
			}
			System.out.println(g.minimo());
			centinela = sc.nextInt();
		}
		return true;
	}
	
	public static void main(String[] args) {
		Gorras juez = new Gorras(TipoSolucion.SENTINEL) ;
		juez.run();
	}
	
}

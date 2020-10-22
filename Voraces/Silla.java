import java.util.Arrays;
/*
 * Problema 21, Telesilla
 *  @author DA18, Octavio Sales Calvo 
 */
public class Silla extends SolverJudgeA {

	private int pmax; // peso maximo de la silla
	private int personas; // numero de personas
	private int[] pesos; // peso de cada persona

	/*
	 * Constructor de clase, crea un objeto instanciando los atributos de la
	 * clase.
	 * @param m: peso maximo de la silla.
	 * @param p: numero de personas.
	 */
	public Silla(int m, int p) {
		this.pmax = m;
		this.personas = p;
		this.pesos = new int[personas];
	}

	/*
	 * Constructor: genera un objeto de clase, instanciando la clase padre con
	 * el tipo de solucion.
	 * 
	 * @param tipo: TipoSolucion del juez.
	 */
	public Silla(TipoSolucion tipo) {
		super(tipo);
	}

	/*
	 * Metodo que realiza un algoritmo voraz, que devuelve el minimo numero de viajes para llevar a n personas.
	 * los pesos de las personas se representan en un array ordenado.
	 * 
	 * @return viajes: numero de viajes.
	 * 
	 * Coste en el peor de los casos: O(n), donde n es el numero de personas.
	 */
	public int voraz() {
		int viajes = 0;
		int i = this.personas-1, n = 0;
		int actual = 0;
		while (i >= n) {
			actual=pesos[i];
			if ((actual + pesos[n]) <= pmax) {	//test de factibilidad
				i--;
				n++;
				viajes++;
			}else{
				i--;
				viajes++;
			}
		}
		return viajes;
	}

	/*
	 * Metodo resuelve caso, lee los datos de entrada (creando un objeto de clase, donde se
	 * guardan los pesos, ordenados de menor a mayor, en su atributo (array)) y llama a la funcion
	 * voraz, mostrando la solucion por pantalla.
	 * 
	 * Coste Total: O(n+(n*log(n)))
	 */
	@Override
	public boolean solutionCase() {
		while (sc.hasNextInt()) {
			int peso= sc.nextInt();
			int n= sc.nextInt();
			Silla s = new Silla(peso, n);
			for(int i=0; i<n;i++){
				s.pesos[i]=sc.nextInt();
			}
			Arrays.sort(s.pesos);			// Ordenacion quick sort con doble pivote: O(n log(n))
			System.out.println(s.voraz());
		}
		return true;
	}

	public static void main(String[] args) {
		Silla juez = new Silla(SENTINEL);
		juez.run();

	}

}

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/* 
 * Problema 25, Planficador de Conferencias 
 * @author DA18, Octavio Sales Calvo
 */
public class Planificacion extends SolverJudgeA {

	private Comparator<Conferencia> cmp = new Comparadora(); // comprador para ordenacion de array de conferencias
	private Comparator<Conferencia> cmpf = new ComparadoraF(); // comprador para ordenacion de cola de conferencias
	private Conferencia [] conferencias;					//Array de conferencias a planificar
	//private boolean marked[]; // array de marcas de conferencias
	
	/*
	 * Constructor de clase, crea un objeto instanciando los atributos de la
	 * clase.
	 * 
	 * @param centinela: numero de conferencias.
	 */
	public Planificacion(int centinela) {
//		marked = new boolean[n];
//		conferencias= new PriorityQueue<Conferencia>(n, cmp);
		this.conferencias = new Conferencia[centinela];
	}

	/*
	 * Constructor: genera un objeto de clase, instanciando la clase padre con
	 * el tipo de solucion.
	 * 
	 * @param tipo: TipoSolucion del juez.
	 */
	public Planificacion(TipoSolucion tipo) {
		super(tipo);
	}

	/*
	 * Metodo que realiza un algoritmo voraz, que devuelve el menor numero de
	 * clases que se pueden utilizar para las conferencias. 
	 * Utilizamos una cola de prioridad para representar las clases, 
	 * siendo la cima, la conferencia que más pronto finaliza.
	 * 
	 * @return clases.size() : numero de elementos de la cola.
	 * 
	 * Coste: O(N*(log n)), donde N es el numero de conferencias.
	 */
	 public int voraz(){
		 PriorityQueue<Conferencia> clases = new PriorityQueue<Conferencia>(conferencias.length, cmpf);
		 clases.add(conferencias[0]);
		 for(int i=1;i<conferencias.length;i++){
			 if(conferencias[i].horaIni>= clases.peek().horaFin){	//Test de factibilidad
				 clases.poll();
				 clases.add(conferencias[i]);	//O(log n)
			 }else{
				 clases.add(conferencias[i]);	//O(log n)
			 }
		 }
		 return clases.size();
	 }
	/*public int voraz(Conferencia[] conferencias) {
		int clases = 0;
		for (int i = 0; i < conferencias.length; i++) {
			if (!marked[i]) { // test de factibilidad
				clases++;
				solucion( conferencias, i);
			}
		}
		return clases;
	}*/

	/*
	 * Metodo que realiza una solucion (dfs) para un subconjunto de los
	 * candidatos dados, a la funcion voraz.
	 * 
	 * @param c3: conjunto de candidatos
	 * 
	 * @param i; indice del candidato
	 *
	private void solucion( Conferencia[] c3, int i) {
		int hora = c3[i].horaFin;
		marked[i] = true;
		for (int w = i+1; w < c3.length; w++) {
			if (!marked[w] && (c3[w].horaIni >= hora)) {
				solucion(c3, w);
			}
		}
	}*/
	 

	/*
	 * Metodo resuelve caso, lee los datos de entrada(creando un objeto de clase, donde se
	 * guardan las conferencias, ordenadas por hora de inicio, en su atributo (array)) y llama a la funcion voraz,
	 * mostrando la solucion por pantalla.
	 * 
	 * Coste Total: O(n*(log n))
	 */
	@Override
	public boolean solutionCase() {
		int centinela = sc.nextInt();
		while (centinela > 0) {
			int ini, fin;
			Planificacion p = new Planificacion(centinela);
			for (int i = 0; i < centinela; i++) {
				ini = sc.nextInt();
				fin = sc.nextInt();
				Conferencia c = new Conferencia(ini, fin);
				p.conferencias[i] = c;
			}
			Arrays.sort(p.conferencias, cmp);				//O(n*(log n))
			System.out.println(p.voraz());
			centinela = sc.nextInt();
		}
		return true;
	}


	public static void main(String[] args) {
		Planificacion juez = new Planificacion(SENTINEL);
		juez.run();

	}

}

/* Clase Conferencia, (Datos con los que vamos a trabajar) */
class Conferencia {

	int horaIni;
	int horaFin;
	int duracion;

	public Conferencia(int ini, int fin) {
		horaIni = ini;
		horaFin = fin;
		duracion = Math.abs(ini - fin);
	}

	public Conferencia() {
	}

}

/* Clase Comparadora de Conferencias, respecto hora Inicial y duracion */
class Comparadora implements Comparator<Conferencia> {

	/*
	 * Metodo que compara dos objetos, segun la hora a la que empieza la
	 * conferencia. En caso de iguales según la duracion.
	 */
	@Override
	public int compare(Conferencia o1, Conferencia o2) {

		if (o1.horaIni > o2.horaIni) {// ordenamos de menor a mayor
			return 1;
		} else if (o1.horaIni < o2.horaIni) {
			return -1;
		} else {
			if (o1.duracion < o2.duracion) {//ordenamos de mayor a menor
				return 1;
			} else if (o1.duracion > o2.duracion) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}

/* Clase Comparadora de Conferencias, respecto hora Final y duracion */
class ComparadoraF implements Comparator<Conferencia> {

	/*
	 * Metodo que compara dos objetos, segun la hora a la que termina la
	 * conferencia. En caso de iguales según la duracion.
	 */
	@Override
	public int compare(Conferencia o1, Conferencia o2) {

		if (o1.horaFin > o2.horaFin) {// ordenamos de menor a mayor
			return 1;
		} else if (o1.horaFin < o2.horaFin) {
			return -1;
		} else {
			if (o1.duracion < o2.duracion) {//ordenamos de mayor a menor
				return 1;
			} else if (o1.duracion > o2.duracion) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}

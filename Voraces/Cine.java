import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * Problema 24, Maraton de Cine
 * @author DA18, Octavio Sales Calvo
 */
public class Cine extends SolverJudgeA {

	private PriorityQueue<Pelicula> peliculas; // cola con prioridad cronologica, donde se almacenan las peliculas
	private Comparator<Pelicula> comp = new Comparador(); // comparador de cola con prioridad

	/*
	 * Constructor de clase, crea un objeto instanciando los atributos de la
	 * clase.
	 * @param n: numero de peliculas.
	 */
	public Cine(int n) {
		peliculas = new PriorityQueue<Pelicula>(n, comp);
	}

	/*
	 * Constructor: genera un objeto de clase, instanciando la clase padre con
	 * el tipo de solucion.
	 * 
	 * @param tipo: TipoSolucion del juez.
	 */
	public Cine(TipoSolucion tipo) {
		super(tipo);
	}

	/*
	 * Metodo que añade un elemento a la cola de la clase.
	 * @param p: pelicula a añadir a la cola.
	 * @return if (success) True. EOC False. 
	 */
	public boolean add(Pelicula p) {
		return this.peliculas.add(p);
	}

	/*
	 * Metodo que realiza un algoritmo voraz, que devuelve el mayor numero de
	 * peliculas que se pueden ver. 
	 * Las peliculas (candidatas) estan contenidas en una cola de prioridad ordenadas por la hora de finalizacion.
	 * @return cnt: numero de peliculas.
	 * 
	 * Coste: O(n), donde n es el numero de peliculas.
	 */
	public int voraz() {
		int cnt = 1;
		Pelicula p;
		p = peliculas.poll();
		int hora = p.horaFin + 10;
		while (!peliculas.isEmpty()) {
			p = peliculas.poll();
			if (p.horaIni >= hora) {	//test de factibilidad
				cnt++;
				hora = p.horaFin + 10;
			}
		}
		return cnt;
	}

	/*
	 * Metodoresuelve caso, lee los datos de entrada y llama a la funcion voraz,
	 * mostrando la solucion por pantalla.
	 * 
	 * Coste Total: O(n+(n*(log n)))
	 */
	@Override
	public boolean solutionCase() {
		int centinela = sc.nextInt();
		while (centinela > 0) {
			Cine c = new Cine(centinela);
			Integer ini = 0, aux = 0, min = 0;
			for (int i = 0; i < centinela; i++) {
				String a = sc.next();			//cadena: 00:00
				ini = Integer.parseInt(a.substring(0, 2));
				ini = ini * 60;					// tiempo en minutos.
				aux = Integer.parseInt(a.substring(3, 5));
				ini += aux;
				min = sc.nextInt();
				Pelicula p = new Pelicula(ini, min);
				c.add(p);							//O(log n)
			}
			System.out.println(c.voraz());
			centinela = sc.nextInt();
		}
		return true;
	}

	public static void main(String[] args) {
		Cine juez = new Cine(SENTINEL);
		juez.run();

	}

}

/*Clase Pelicula, (Datos con los que vamos a trabajar)*/
class Pelicula {

	int horaIni;
	int horaFin;
	int duracion;

	public Pelicula(int ini, int min) {
		horaIni = ini;
		horaFin = horaIni + (min);
		duracion = min;
	}

}

/* Clase Comparadora de Peliculas, respecto hora y duracion */
class Comparador implements Comparator<Pelicula> {

	/* Metodo que compara dos objetos, segun la hora a la que finaliza la pelicula.
	 *  En caso de iguales según la duracion.
	 */
	@Override
	public int compare(Pelicula o1, Pelicula o2) {

		if (o1.horaFin > o2.horaFin) {// ordenamos de menor a mayor
			return 1;
		} else if (o1.horaFin < o2.horaFin) {
			return -1;
		} else {
			if (o1.duracion < o2.duracion) {
				return -1;
			} else if (o1.duracion > o2.duracion) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}

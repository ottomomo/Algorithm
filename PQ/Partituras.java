import java.util.Comparator;
import java.util.PriorityQueue;

public class Partituras extends SolverJudgeA {

	private PriorityQueue<Musicos> cola;
	Comparator<Musicos> comparator = new Comparador();

	/*
	 * Constructora de Partituras: genera un objeto de clase, instanciando su
	 * atributo.
	 * 
	 * @param nElems: numero de elementos del atributo cola.
	 */
	public Partituras(int nElems) {
		cola = new PriorityQueue<Musicos>(nElems, comparator);
	}

	/*
	 * Constructora de Partituras: genera un objeto de clase, instanciando la
	 * clase padre con el tipo de solucion.
	 * 
	 * @param tipo: TipoSolucion del juez.
	 */
	public Partituras(TipoSolucion tipo) {
		super(tipo);
	}

	public Long musicos(long partituras) {
		if (cola.size() == 0) {
			return (long) 0;
		}
		Musicos e;
		long cnt = partituras - (cola.size());
		while (cnt > 0) {
			e = cola.poll();
			cnt--;
			e.addA();
			cola.add(e);
		}
		e = cola.poll();
		if((e.getN()%e.getA())==0){
			return cnt=(e.getN()/e.getA());
		}else{
			return cnt=(e.getN()/e.getA())+1;
		}
	}

	public boolean add(Musicos e) {
		return cola.add(e);
	}

	@Override
	public boolean solutionCase() {
		Long partituras;
		int n;
		int e;
		boolean exit = false;
		while (sc.hasNextInt() && !exit) {
			partituras = sc.nextLong();
			n = sc.nextInt();
			if (n <= partituras) {
				Partituras g = new Partituras(n);
				for (int i = 0; i < n; i++) {
					e = sc.nextInt();
					Musicos m= new Musicos(e,1);
					g.add(m);
				}
				System.out.println(g.musicos(partituras));
			} else {
				exit = true;
				System.out.println("0");
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Partituras juez = new Partituras(SENTINEL);
		juez.run();
	}

}

/* Clase Comparadora (de mayor que) */
class Comparador implements Comparator<Musicos> {

	/*Metodo que compara dos objetos, segun el numero de musicos que comparten un atril*/
	@Override
	public int compare(Musicos o1, Musicos o2) {
		long a1, a2;
		// calculamos el mayor numero de musicos por atril
		if (o1.getN() % o1.getA() == 0) {
			a1 = o1.getN() / o1.getA();
		} else {
			a1 = (o1.getN() / o1.getA()) + 1;
		}
		if (o2.getN() % o2.getA() == 0) {
			a2 = o2.getN() / o2.getA();
		} else {
			a2 = (o2.getN() / o2.getA()) + 1;
		}
		
		if (a1 > a2) {// ordenamos de mayor a menor
			return -1;
		} else if (a1 < a2) {
			return 1;
		} else {
			return 0;
		}
	}
}

/* Clase Musicos (datos con los que vamos a trabajar) */
class Musicos {

	private long numero;
	private long atriles;
	
	public Musicos(long n, long a){
		this.numero=n;
		this.atriles=a;
	}

	public long getN() {
		return numero;
	}

	public long getA() {
		return atriles;
	}
	
	public void addA(){
		this.atriles++;
	}

}

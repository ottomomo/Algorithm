//DA18, Octavio Sales

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Queue;

public class FreeLove extends SolverJudgeA {
	
	/*Codificacion de Puntos*/
	final static int EMPTY = 0; // vertices del grafo
	final static int WALL = -1;
	final static int CAT = -2;
	final static int E = 1; // vertice inicio de busqueda
	final static int P = 2; // vertice de la puerta

	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked; // marked[v] = is there an s-v path
	private int[] edgeTo; // edgeTo[v] = previous edge on shortest s-v path
	private int[] distTo; // distTo[v] = number of edges shortest s-v path
	private boolean solution; // hay solucion

	/*Constructor:  genera un objeto de clase, instanciando 
	 * la clase padre con el tipo de solucion.
	 * @param tipo: TipoSolucion del juez.
	 * */
	public FreeLove(TipoSolucion tipo) {
		super(tipo);
	}

	/*Constructor FreeLove: genera un objeto de la clase instanciando sus atributos. 
	 * Seguidamente hace una BFS, con los parametros de entrada.
	 * @param m: Punto [], una array de puntos, que emula un grafo.
	 * @param row: numero de filas de la matriz, pasada por la entrada estandar
	 * @param col: numero de columnas de la matriz, pasada por la entrada estandar
	 * @param s: vertice source, vertice de inicio de la bfs
	 * */
	public FreeLove(Punto[] m, int row, int col, int s) {
		marked = new boolean[row * col];
		distTo = new int[row * col];
		edgeTo = new int[row * col];
		bfs(m, s, row, col);

	}
	
	/*Metodo que nos dice si existe un camino desde el vertice source hasta el vertice v
	 * @param: v: vertice destino
	 * @return: T si existe un camino, F si no existe
	 * */
	public boolean hasPathTo(int v) {
		return marked[v];
	}

	/*Metodo que devuelve la distancia mas corta desde el vertice source hasta el vertice v
	 * @param v: vertice destino
	 * @return: disTo[v]
	 * */
	public int distTo(int v) {
		return distTo[v];
	}

	// breadth-first search from a single source
	/*Metodo que realiza una busqueda en anchura de una matriz (array de Puntos), la cual emula a un grafo.
	 * @param m: la matriz a estudiar
	 * @param s: vertice source, por donde se inicia la busqueda
	 * @param row: numero de filas de la matriz
	 * @param col: numero de columnas de la matriz
	 * Coste O(v+e) siendo v los vertices y e aristas del grafo.
	 * */
	private void bfs(Punto[] m, int s, int row, int col) {
		Queue<Integer> q = new Queue<Integer>();
		for (int v = 0; v < row * col; v++)
			distTo[v] = INFINITY;
		distTo[s] = 0;
		marked[s] = true;
		q.enqueue(s);

		while (!q.isEmpty()) {
			int v = q.dequeue();
			List<Integer> adj = new ArrayList<Integer>();// lista de adyacentes
			if ((v % col) < col - 1)
				adj.add(v + 1);
			if ((v % col) > 0)
				adj.add(v - 1);
			if ((v / col) > 0)
				adj.add(v - col);
			if ((v / col) < row - 1)
				adj.add(v + col);
			for (int w : adj) {
				if (!marked[w] && (m[w].getV() >= 0)) {// si no esta marcado y es un vertice
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
	}

	/*Metodo que lee y codifica los datos de entrada, y finalmente hace una llamada al constructor de la clase.
	 * Con el fin de que realice una busqueda en anchura, y facilite el recorrido mas corto entre 2 ptos
	 * */
	@Override
	public boolean solutionCase() {
		int col = sc.nextInt();
		int row = sc.nextInt();
		int cnt = col * row;
		Punto[] matrix = new Punto[cnt];
		for (int z = 0; z < cnt; z++) {
			matrix[z] = new Punto();
		}
//		sc.useDelimiter("");// modificamos delimitador del scanner
		String m = null;
		String mapa;
		this.solution = false;// inicializamos atributo de control
		int s = 0; // variable source, indica el vertice de inicio de busqueda
		int p = 0; // variable puerta, indica el vertice de la puerta principal
		/*Construimos la matriz, que emula un grafo:	*/
		for (int j = 0; j < cnt; j++) {
			if ((j % col == 0)) {
				m = sc.next();// lectura de fila
			}
			mapa = m.substring((j%col), ((j)%(col)+1));// recibimos caracter m[j]
			switch (mapa) {
			case ".":
//			case '.':
				if (matrix[j].getV() != CAT) {
					matrix[j] = new Punto((j / col), (j % col), EMPTY);
				}
				break;
			case "P":
//			case 'P':
				if (matrix[j].getV() != CAT) {
					matrix[j] = new Punto((j / col), (j % col), P);
					this.solution = true;
					p = j; // matrix[j]= vertice final de busqueda
				} else {
					this.solution = false;// no hay solucion
				}
				break;
			case "E":
//			case 'E':
				if (matrix[j].getV() != CAT) {
					matrix[j] = new Punto((j / col), (j % col), E);
					this.solution = true;
					s = j; // matrix[s]= vertice inicio de busqueda
				} else {
					this.solution = false;// no hay solucion
				}
				break;
			case "#":
//			case '#':
				matrix[j] = new Punto((j / col), (j % col), WALL);
				if (matrix[j + col].getV() == CAT) {// si se habia colocado un CAT antes que el muro...
					for (int z = (j + col); z < row * col; z += j + col) {
						matrix[z] = new Punto((z / col), (z % col), EMPTY);// se anula el CAT
					}
				}
				if (matrix[j + 1].getV() == CAT) {
					for (int z = (j + 1); (z % col - 1) != 0; z++) {
						matrix[z] = new Punto((z / col), (z % col), EMPTY);
					}
				}
				break;
			default:
				if (mapa.equals("0") || mapa.equals("1") || mapa.equals("2")
						|| mapa.equals("3") ||mapa.equals("4")
						|| mapa.equals("5") || mapa.equals("6")
						|| mapa.equals("7") || mapa.equals("8")
						|| mapa.equals("9")) {
				/*if(mapa==('0') ||mapa==('1') || mapa==('2')
						|| mapa==('3') ||mapa==('4')
						|| mapa==('5') || mapa==('6')
						|| mapa==('7') ||mapa==('8')
						|| mapa==('9')){*/
					int longitud = Integer.parseInt(mapa);// grado del CAT, la distancia que alcanza
//					int longitud= mapa;
					int fila = (j / col);// fila actual
					int columna = (j % col);// columna actual
					int desplaza = j;// desplazamiento por filas y columnas

					matrix[j] = new Punto((j / col), (j % col), CAT);

					// ARRIBA
					desplaza = desplaza - col;// posicion fila-1
					for (int a = fila - 1; (a >= (fila - longitud)) && (a >= 0); a--) {
						if (matrix[desplaza].getV() != WALL) {
							matrix[desplaza] = new Punto((desplaza / col),
									(desplaza % col), CAT);
						} else {
							break;
						}
						desplaza = desplaza - col;// a--
					}
					// ABAJO
					desplaza = j + col;// posicion fila+1
					for (int a = fila + 1; (a <= (fila + longitud))
							&& (a < row); a++) {
						if (matrix[desplaza].getV() != WALL) {
							matrix[desplaza] = new Punto((desplaza / col),
									(desplaza % col), CAT);
						} else {
							break;
						}
						desplaza = desplaza + col;
					}
					// DERECHA
					desplaza = j + 1;// posicion columna+1
					for (int a = columna + 1; (a <= (columna + longitud))
							&& (a < col); a++) {
						if (matrix[desplaza].getV() != WALL) {
							matrix[desplaza] = new Punto((desplaza / col),
									(desplaza % col), CAT);
						} else {
							break;
						}
						desplaza++;
					}
					// IZQUIERDA
					desplaza = j - 1;// posicion columna -1
					for (int a = columna - 1; (a >= (columna - longitud))
							&& (a >= 0); a--) {
						if (matrix[desplaza].getV() != WALL) {
							matrix[desplaza] = new Punto((desplaza / col),
									(desplaza % col), CAT);
						} else {
							break;
						}
						desplaza--;
					}
				}
				break;
			}
		}
//		sc.reset();// restauramos delimitadores del scanner
		if (this.solution) {
			FreeLove busqueda = new FreeLove(matrix, row, col, s);// lamada al constructor de la clase
			if (busqueda.hasPathTo(p)) {
				System.out.println(busqueda.distTo(p));
			} else {
				System.out.println("NO");
			}
		} else {
			System.out.println("NO");
		}
		return false;
	}

	public static void main(String[] args) {
		FreeLove juez = new FreeLove(QUANTY);
		juez.run();
	}
}

/*Clase con la que construiremos nuestra Matriz*/
class Punto {

	final static int EMPTY = 0;
	final static int WALL = -1;
	final static int CAT = -2;
	final static int E = 1;
	final static int P = 2;

	private int row;
	private int col;
	private int value;

	public Punto() {
		this.value = EMPTY;
	}

	public Punto(int r, int c, int v) {
		this.row = r;
		this.col = c;
		if (v == 0) {
			this.value = EMPTY;
		} else if (v == 2) {
			this.value = P;
		} else if (v == 1) {
			this.value = E;
		} else if (v == -1) {
			this.value = WALL;
		} else {
			this.value = CAT;
		}
	}

	int getR() {
		return this.row;
	}

	int getC() {
		return this.col;
	}

	int getV() {
		return this.value;
	}

}
import java.lang.Math;

/**
 * @author DA18, Octavio Sales Calvo 
 * Problema 28, Caza Tesoros
 *
 */
public class CazaTesoros extends SolverJudgeA {

	private boolean marcas[];// array de marcaje de objetos a estudiar
	private int tiempo; // tiempo del que se dispone
	private int nTotal; // numero de objetos que se dispone
	private int contador; // numero de objetos seleccionados
	private Tesoro tesoros[];// array de los objetos

	/*
	 * Constructor: genera un objeto de clase, instanciando la clase padre con
	 * el tipo de solucion.
	 * 
	 * @param tipo: TipoSolucion del juez.
	 */
	public CazaTesoros(TipoSolucion tipo) {
		super(tipo);
	}

	/*
	 * Constructor de clase, crea un objeto instanciando los atributos de la
	 * clase.
	 * 
	 * @param t: tiempo total.
	 * 
	 * @param n: numero de tesoros.
	 */
	public CazaTesoros(int t, int n) {
		tiempo = t;
		nTotal = n;
		contador = 0;
		marcas = new boolean[n + 1];
		tesoros = new Tesoro[n + 1];
	}

	/*
	 * funcion mochila(i,j): calcula el maximo valor que podemos llevar en una
	 * mochila de peso maximo 'j' considerando los objetos del 1 hasta 'i'.
	 * Recursion: mochila(i, j): -mochila(i-1, j) si mochila[i].peso > j
	 * 							-max{mochila(i-1, j), mochila(i-1, j-(mochila[i].peso))+mochila[i].valor}  si mochila[i].peso <= j
	 * 
	 * Coste: O((tiempo*numeroTotal)+numeroTotal) = (n*m)
	 */
	int mochila() {
		int max = 0;
		int mochila[][] = new int[nTotal + 1][tiempo + 1];
		/* Casos Base: */
		for (int z = 0; z < tiempo; z++) {
			mochila[0][z] = 0;
		}
		for (int z = 0; z < nTotal; z++) {
			mochila[z][0] = 0;
		}
		// rellenamos la matriz
		int suma= tesoros[1].profundidad;
		for (int i = 1; i <= nTotal; ++i) {
			for (int j = 1; j <= tiempo; ++j) {
				if (tesoros[i].profundidad > j){
					mochila[i][j] = mochila[i - 1][j];
				}else {
					mochila[i][j] = Math.max(mochila[i - 1][j],	mochila[i - 1][j - tesoros[i].profundidad]+ tesoros[i].valor);
				}
			}
		}
		max = mochila[nTotal][tiempo];
		
		//cálculo de los objetos 
		int resto = tiempo;
		for (int i = nTotal; i  >= 1; --i) { 
			if (mochila[i][resto] == mochila[i - 1][resto]) { // no cogemos objeto i 
				marcas[i] = false; 
			} 
			else { // sí cogemos objeto i
				marcas[i] = true;
				contador++; 
				resto = resto - tesoros[i].profundidad;
			}
		}
		 
		return max;
	}

	/*
	 * Metodo resuelve caso, lee los datos de entrada (creando un objeto de la
	 * clase, instanciado con los datos) y llama a la funcion mochila, mostrando
	 * la solucion por pantalla.
	 * Coste Total: O(2*numeroTotal+(n*m)) = (n*m)
	 */
	@Override
	public boolean solutionCase() {
		while (sc.hasNextInt()) {
			int tiempo = sc.nextInt();
			int total = sc.nextInt();
			CazaTesoros c = new CazaTesoros(tiempo, total);
			int p, v;
			for (int i = 1; i <= total; i++) {
				p = sc.nextInt();
				v = sc.nextInt();
				Tesoro t = new Tesoro(p, v);
				c.tesoros[i] = t;
			}
			System.out.println(c.mochila());
			System.out.println(c.contador);
			for (int i = 1; i <= total; i++) {
				if (c.marcas[i] == true) {
					System.out.println(c.tesoros[i].profundidad / 3 + " "+ c.tesoros[i].valor);
				}
			}
			System.out.println("----");
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CazaTesoros juez = new CazaTesoros(SENTINEL);
		juez.run();
	}

}

/*
 * Clase Tesoro (datos con los que trabjamos)
 */
class Tesoro {

	int profundidad;
	int valor;

	public Tesoro(int p, int v) {
		profundidad = 3 * p; // tiempo que se tarda en recoger un tesoro:
								// t=3*profundidad
		valor = v;
	}

}
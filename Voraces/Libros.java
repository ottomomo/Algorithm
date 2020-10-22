import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * 
 */

/**
 * @author DA18, Octavio Sales Calvo
 * Problema Oferta 3x2
 */
public class Libros extends SolverJudgeA {

	/**
	 * 
	 */
	private int[] libros;
	public Libros() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * O(n) se recorren todos los elementos del array
	 * @return maximo descuento
	 */
	int voraz(){
		int cont=0, acum=0;
		for(int i=libros.length-1;i>=0;--i){
			cont++;
			if(cont%3==0){
				acum += libros[i];
			}
		}
		return acum;
	}
	
	/**
	 * @param solutionType
	 */
	public Libros(TipoSolucion solutionType) {
		super(solutionType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param solutionType
	 * @param fileName
	 */
	public Libros(TipoSolucion solutionType, String fileName) {
		super(solutionType, fileName);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see SolverJudgeA#solutionCase()
	 * COSTE TOTAL: lectura de datos: O(n)
	 * 				Ordenacion de datos: O(n log(n))
	 * 				Alg Voraz:		O(n)
	 * 				TOTAL: O(2n + nlog(n)) = (nlog(n))
	 */
	@Override
	public boolean solutionCase() {
		// TODO Auto-generated method stub
		int n=0;
		while(sc.hasNextInt()){
			n= sc.nextInt();
			this.libros = new int [n];
			for(int i=0;i<n;++i){
				this.libros[i]=sc.nextInt();
			}
			Arrays.sort(libros);// O(nlog(n)); ordena de forma ascendente.
			System.out.println(voraz());
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Libros juez = new Libros(SENTINEL);
		juez.run();
	}

}

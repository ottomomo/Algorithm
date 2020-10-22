/**
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Clase que soluciona los problemas del juez que encajan en los patrones CENTINELA y CANTIDAD.
 * 
 * Esta clase lleva una indicador de si me encuentro en modo depuración o no y además lleva sus
 * funciones para el manejo de dicho flag. Dicho flag es {@code DEBUG} y 
 * las funciones {@link #onDebug() onDebug} y
 * {@link #offDebug() offDebug}.
 * 
 * @version <bf>Versión 2</bf>: Lee las opciones de datos de la línea de ejecución.
 * <p>java -DDOMJUDGE=1 fichero ...	
 * activa el modo DOMJUDGE
 * <p>Si no hay opción para modo DOMJUDGE este queda inactivo en el constructor. 
 * De esta manera se comporta similar a las directivas de compilación de C++
 * y no es necesario cambiar el código cada vez que se envía.
 * <p>Aún así se mantiene las funciones de activación y desactivación por compatibilidad hacia atras.
 * <p>El modo de uso es muy simple se hace cada ejercicio heredando de esta clase e 
 * implementado el método {@link #solutionCase() solutionCase}. Se crea un objeto de dicha clase y
 * se llama al método {@link #run() run}.
 * 
 * @author Alberto de la Encina Vara
 *
 */
/* ToDo Añadir los tipos Enumerados
public enum TipoSolucion
{
	QUANTY, SENTINEL
}
*/
public abstract class SolverJudgeA {
	/**
	 * Tipo enumerado para los distintos problemas que se solucionan en el juez.
	 * {@code QUANTY} o {@code SENTINEL}.
	 * @author Alberto
	 */
	public static enum TipoSolucion{ QUANTY, SENTINEL};
	private static BufferedReader in = new BufferedReader( new InputStreamReader( System.in) );
	/**
	 * La salida de los programas debería hacerse desde el atributo {@code out}.
	 */
	protected static PrintStream out = System.out;
	/**
	 * La salida de los errores del programa debería hacerse desde el atributo {@code err}.
	 */
	protected static PrintStream err = System.err;
	/**
	 * Las lecturas de los datos deberían realizarse desde el {@code Scanner} {@code sc} para que estas
	 * dependan del modo de ejecucion DOMJUDGE o normal.
	 */
	protected static Scanner sc = new Scanner(in);

	/**
	 *  Atributo que controla si estoy en modo depuración o no
	 */
	protected boolean DEBUG = false;
	// Atributo que controla si estoy en modo DOMJUDGE. Ahora lo hace de forma automática.
	private boolean DOMJUDGE;
	/**
	 * Por comodidad se exporta la variable {@code QUANTY}.
	 */
	public static final TipoSolucion QUANTY = TipoSolucion.QUANTY;
	/**
	 * Por comodidad se exporta la variable {@code SENTINEL}.
	 */
	public static final TipoSolucion SENTINEL = TipoSolucion.SENTINEL;
	// Tipo de solución
	private TipoSolucion solutionType = QUANTY;
	// Nombre del fichero desde el que leer.
	private String fileName = "casos.txt";

	/**
	 * Genera un objeto que realiza una solución al juez de tipo CANTIDAD.
	 * Dicho objeto no se encuentra en modo depuración.
	 * Por defecto se lee del fichero casos.txt.
	 */
	public SolverJudgeA(){
		DOMJUDGE = systemIsDomjudge();
	}
	/**
	 * Genera un objeto que realiza una solución al juez de tipo CENTINELA o CANTIDAD.
	 * Además considera que no se encuentra en modo DEBUG.
	 * Por defecto se lee del fichero casos.txt.
	 * 
	 * @param solutionType tipo de solución posible QUANTY o SENTINEL.
	 */
	public SolverJudgeA(TipoSolucion solutionType){
		this.solutionType = solutionType;
		DOMJUDGE = systemIsDomjudge();
	}
	/**
	 * Genera un objeto que realiza una solución al juez de tipo CENTINELA o CANTIDAD.
	 * 
	 * Además considera que no se encuentra en modo DEBUG. 
	 * Si se quiere cambiar dicho comportamiento es necesario utilizar los métodos onDebug(), o offDebug()
	 * 
	 * @param solutionType tipo de solución posible QUANTY o SENTINEL.
	 * @param fileName nombre del fichero desde el que se leerá la solución.
	 */
	public SolverJudgeA(TipoSolucion solutionType, String fileName){
		this.DOMJUDGE = systemIsDomjudge();
		this.solutionType = solutionType;
		this.fileName = fileName;
	}
	
	/**
	 * Función que se debe encargar de solucionar un caso. 
	 * Realizará la lectura adecuada, así como el procesamiento del caso y la muestra del resultado.
	 * 
	 * <p>Para la implementación de esta función se debe realizar la lectura desde el atributo {@code sc} (Scanner)
	 * ya que toda la lectura se realiza desde allí.
	 *  
	 * @return un booleano {@code true} para indicar que ha habido algún problema en la lectura o 
	 * que se ha llegado al final de la lectura de los casos en caso del patrón centinela y
	 * {@code false} en caso de que el caso se haya procesado correctamente. 
	 */
	public abstract boolean solutionCase();
	
	/**
	 * Método que ejecuta la solución de problema. 
	 * 
	 * Dependiendo de si estoy en modo DOMJUDGE o no se 
	 * realiza la lectura desde el fichero o desde el input standard.
	 * 
	 * Si se produce algún error en la lectura por fichero se lanza un mensaje de error
	 * por la consola estándar de error y se continua con la lectura por teclado.
	 */
	public void run() {
		if ( !DOMJUDGE ) { 
			try {
				in = new BufferedReader( new FileReader(this.fileName) );
				sc = new Scanner(in);
			} catch (FileNotFoundException e) {
				err.println("ERROR: En la lectura del fichero con el patrón cantidad. LEO DEL TECLADO."); 
			}
		}

		switch (this.solutionType) {
			case QUANTY:
				runQuanty();
				break;
			case SENTINEL:
				runSentinel();
				break;
			default:
				err.println("ERROR: Not correctly defined solutionType");
		}
		
		sc.close();

		if ( !DOMJUDGE ) out.println("YA FUNCIONA! el flag DOMJUDGE automático.");
	}
	
	/*
	 * Método que resuelve un problema del juez basado en el patrón centinela del juez.
	 */
	private void runSentinel() {
		boolean fin;
		do {
			fin = solutionCase();
		} while (!fin);		
	}
	
	/*
	 * Método que resuelve un problema del juez basado en el patrón cantidad del juez.
	 * Avisa de errores de lectura, a través del System.err, tanto en el número de casos como 
	 * en la lectura de un caso a través del patrón cantidad. 
	 * Por ejemplo, hay menos datos de los esperados en el patrón cantidad.
	 */
	private void runQuanty() {
		int numCasos =-1; 
		boolean error = false;
		try {
			numCasos = sc.nextInt(); //Integer.parseInt(in.readLine());
		} catch (Exception e) {
			error = true;
		}
		if (error) err.println("ERROR: En la lectura del número de casos."); 
		else {
			for (int i = 0; i < numCasos && !error; i++)
				error = solutionCase();
			if (error) err.println("ERROR: En la lectura de un caso con el patrón cantidad."); 
		}
	}
	
	/**
	 * Método que activa la depuración.
	 */
	public void onDebug() {
		DEBUG = true;
	}
	/**
	 * Método que desactiva la depuración.
	 */
	public void offDebug() {
		DEBUG = false;
	}
	/*
	 * Método encargado de coger de la línea del comando si estoy ejecutando en modo DOMJUDGE o no.
	 * 
	 * java -DDOMJUDGE=1 ....
	 * activa el modo domjudge.
	 * @return true si está en modo domjuge y false en otro caso.
	 */
	private static boolean systemIsDomjudge() {
		String domOption = System.getProperty("DOMJUDGE");
		return domOption != null && (domOption.equals("1") || domOption.equals("true"));
	}

}

/* 
 * Problema 20, Agujeros en la manguera.
 * @author DA18, Octavio Sales Calvo
 */
public class Parches extends SolverJudgeA {
	
	private int n;			//numero de agujeros en la manguera
	private int [] manguera;// array que representa la ubicacion de los agujeros
	private int l;			//longitud de los parches
	
	/*
	 * Constructor de clase, crea un objeto instanciando los atributos de la clase.
	 * 
	 * @param agujeros: numero de agujeros.
	 * @param longitud: logitud de los parches.
	 */
	public Parches(int agujeros, int longitud){
		this.n=agujeros;
		this.l=longitud;
		this.manguera= new int [n];
	}

	
	/*Constructor:  genera un objeto de clase, instanciando 
	 * la clase padre con el tipo de solucion.
	 * 
	 * @param tipo: TipoSolucion del juez.
	 * */
	public Parches(TipoSolucion tipo) {
		super(tipo);
	}
	/*Metodo que realiza un algoritmo voraz, que devuelve el minimo numero de parches para cubrir todos los agujeros.
	 * 
	 * @return cont: numero de parches utilizados.
	 * 
	 * Coste O(n), donde n es el numero de agujeros, ya que recorremos el array entero.
	 */
	public int voraz(){
		int cont=1;
		int acum= this.manguera[0];
		for(int i=1;i<this.n;i++){
			if((acum+this.l)<this.manguera[i]){// test de factibilidad
				cont++;
				acum=this.manguera[i];
			}
		}
		return cont;
	}

	/*
	 * Metodo resuelve caso, lee los datos de entrada (creando un objeto de la clase, instanciado con los datos)
	 * y llama a la funcion voraz, mostrando la solucion por pantalla.
	 * Coste Total: O(2n), donde n es el numero de agujeros
	 */
	@Override
	public boolean solutionCase() {
		while(sc.hasNextInt()){
			int agujeros=sc.nextInt();
			int longitud=sc.nextInt();
			Parches p= new Parches(agujeros, longitud);
			for(int i=0;i<agujeros;i++){
				p.manguera[i]=sc.nextInt();
			}
			System.out.println(p.voraz());
		}
		return true;
	}
	
	public static void main(String[] args) {
		Parches juez= new Parches(SENTINEL);
		juez.run();
	}

}

import java.util.ArrayList;
import java.util.Random;

/*Clase de Ordenacion QuickSort. Ordena un ArrayList mediante algoritmo QuickSort.*/
class QuickSort {
	
private static ArrayList<Pelicula> inputArray = new ArrayList<Pelicula>();
         
    public QuickSort(ArrayList<Pelicula> inputArray){
        QuickSort.inputArray = inputArray;
    }
 
    public int compare(Pelicula o1, Pelicula o2) {

		if (o1.horaIni > o2.horaIni) {// ordenamos de menor a mayor
			return 1;
		} else if (o1.horaIni < o2.horaIni) {
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
    
    public void startQuickStart(int start,int end){
        int q;
        if(start<end){
            q = partition(start, end);
            startQuickStart(start, q);
            startQuickStart(q+1, end);
        }
    }
 
    public ArrayList<Pelicula> getSortedArray(){
        return QuickSort.inputArray;
    }
 
    int partition(int start,int end){
         
        int init = start;
        int length = end;
         
        Random r = new Random();
        int pivotIndex = nextIntInRange(start,end,r);
        Pelicula pivot = inputArray.get(pivotIndex);
                          
        while(true){
            while(/*inputArray.get(length)>pivot*/  compare(inputArray.get(length), pivot)==1 && length>start){
                length--;
            }
             
            while(/*inputArray.get(init)<pivot*/ compare(inputArray.get(init), pivot)==-1 && init<end){
                init++;
            }
             
            if(init<length){
            	Pelicula temp;
                temp = inputArray.get(init);
                inputArray.set(init,inputArray.get(length));
                inputArray.set(length,temp);
                length--;
                init++;
                
            }else{
                return length;
            }
        }
         
    }
     
    // Below method is to just find random integer from given range
    static int nextIntInRange(int min, int max, Random rng) {
           if (min > max) {
              throw new IllegalArgumentException("Cannot draw random int from invalid range [" + min + ", " + max + "].");
           }
           int diff = max - min;
           if (diff >= 0 && diff != Integer.MAX_VALUE) {
              return (min + rng.nextInt(diff + 1));
           }
           int i;
           do {
              i = rng.nextInt();
           } while (i < min || i > max);
           return i;
        }
}
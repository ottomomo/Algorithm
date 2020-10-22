/*Autor: Octavio Sales Calvo
Ejercicio 5.*/

#include<iostream>
#include<stdlib.h>
//#include"PriorityQueue.h"

#include <queue>          // std::priority_queue
#include <vector>         // std::vector
//#include <functional>     // std::greater

using namespace std;

/* Clase mycomparison : clase comparadora, menor que(<) o mayor que(>). la cual utilizo para dar prioridad a la cola.
*/
class Comparador
{
	bool reverse=false;
public:
	/*Constructor*/
	Comparador(/*const bool& revparam*/)
	{
		reverse = false;
	}
	/*funcion operadora: compara dos argumentos */
	bool operator() (const long int& lhs, const long int&rhs) const
	{
		if (reverse) return (lhs>rhs);
		else return (lhs<rhs);
	}
};

/* funcion gorras: funcion que calcula el minimo numero de gorras necesarias para realizar todos los partidos.
	@param cola: cola con prioridad less.
	@return el numero de gorras
	Coste: O(n) donde n es el numero de elementos de la cola.
	*/
long int gorras(priority_queue<long int>cola) {
	long int gorras = 0;
	long int e1 = 0, e2 = 0;

	while ((cola.size())>1) {
		e1 = cola.top();
		cola.pop();
		gorras += e1;
		e2 = cola.top();
		cola.pop();
		gorras += e2;
		cola.push(e1 + e2);
	}

	return gorras;
}

void resuleveCaso(long int nElems) {
	long int elems[100000];
	//PriorityQueue<size_t, std::less<size_t>> q;
	priority_queue<long int> cola;
	//PriorityQueue<long int,  std::less<long int>> cola;
	for (int i = 1; i <= nElems; i++) {
		cin >> elems[i];
		cola.push(elems[i]);
	}
	long int r = gorras(cola);
	cout << r << endl;
}

int main() {
	long int nElems;
	cin >> nElems;
	while (nElems != 0) {
		resuleveCaso(nElems);
		cin >> nElems;
	}
	system("pause");
	return 0;
}
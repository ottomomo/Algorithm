/*problema 2  AVL
Autor: Octavio Sales Calvo
*/

#include<iostream>
#include<fstream>
#include<stdlib.h>
#include<algorithm>
#include"bintree_eda.h"

using namespace std;

bool avl(bintree<int> arbol, int &altura, int &min, int &maximo) {
	if (arbol.empty()) {
		altura = 0, min = 0, maximo = 0; return true;
	}
	else {
		int der = 0, izq = 0, maxi = 0, mind = 0, maxd = 0, mini = 0;
		if (avl(arbol.left(), izq, mini, maxi) && avl(arbol.right(), der, mind, maxd)) {
			altura = 1 + max(izq, der);
			if (abs(izq - der) <= 1) {//equilibrado
				if (izq == 0 && der == 0) {// hoja
					maximo = arbol.root();
					min = arbol.root();
					return true;
				}
				else if (izq ==0 && arbol.root()< mind) {// hijo der
					min = arbol.root();
					maximo = maxd;
					return true;
				}
				else if (der==0 && arbol.root() > maxi) {// hijo izq
					maximo = arbol.root();
					min = mini;
					return true;
				}
				else if ((maxi < arbol.root()) && (arbol.root() < mind)) {
					maximo = maxd;
					min = mini;
					return true;
				}
			}
		}
		return false;
	}
}

//contruye un arbol mediante la entrada estandar
template <class T>
bintree<T> leerArbol(const T& repVacio) {
	T elem;
	cin >> elem;
	if (elem == repVacio)
		return bintree<T>();
	else {
		bintree<T> hi = leerArbol(repVacio);
		bintree<T> hd = leerArbol(repVacio);
		return bintree<T>(hi, elem, hd);
	}
}

// resuelve un caso de prueba
void resuelveCaso() {
	auto arbol = leerArbol(-1);
	int alt = 0, min = 0, max = 0;
	if (avl(arbol, alt, min, max)) {
		cout << "SI" << endl;
	}
	else {
		cout << "NO" << endl;
	}
}

//programa main
int main() {

	int numCasos;
	cin >> numCasos;
	for (int i = 0; i < numCasos; i++)
		resuelveCaso();

	system("pause");
	return 0;
}
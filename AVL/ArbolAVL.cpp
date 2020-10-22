/*problema 2  AVL
Autor: Octavio Sales Calvo
*/

#include<iostream>
#include<fstream>
#include<stdlib.h>
#include<algorithm>
#include"bintree_eda.h"

using namespace std;

/**funcion altura :dado un árbol binario, calcula su altura.
/ Coste O(n)  siendo n el número de nodos del árbol.
/@param: arbol binario tipo int
/@return: altura del arbol bin
/*/
unsigned int altura(bintree<int> const& arbol) {
	if (arbol.empty())
		return 0;
	else
		return 1 + std::max(altura(arbol.left()), altura(arbol.right()));
}

//
/**funcion abb: funcion que comprueba si dado un arbol, es un arbol binario de busqueda.
/@param: arbol bintree tipo int
/@param: min tipo int
/@param: max tipo int
/@return: devuelve 'true' si es un arbol binario de busqueda, 'false' en caso contrario.
*/
bool abb(bintree<int> arbol, int &min, int &max) {
	if ( arbol.empty() || (arbol.right().empty() && arbol.left().empty())) {
		return true;
	}
	else {
		int maxi = arbol.root() - 2;
		int mind = arbol.root() + 2;

		bool der, izq;
		izq = abb(arbol.left(), min, maxi);
		der = abb(arbol.right(), mind, max);

		if ((min > arbol.root()) && (arbol.root() > max))
		{
			return der & izq;
		}
		else {
			return false;
		}
	}
}/*

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
	int sold = altura(arbol.right());
	int soli = altura(arbol.left());
	int min, max;
	if (!arbol.right().empty()) {
		min = arbol.right().root();
	}
	else {
		min = arbol.root() + 1;
	}
	if (!arbol.left().empty()) {
		max = arbol.left().root();
	}
	else {
		max = arbol.root() - 1;
	}
	
	if (abs(soli - sold) < 2) {
		if (abb(arbol, min, max)) {
			cout << "SI" << endl;
		}
		else {
			cout << "NO" << endl;
		}
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
}*/
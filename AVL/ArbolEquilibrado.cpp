#include<iostream>
#include<stdlib.h>
#include"bintree_eda.h"
#include"TreeMap_AVL.h"

using namespace std;
/*
void ae(bintree<char> a, int &cont) {
	if (a.empty()) {
		cont = 0;
	}
	else if (a.left().empty() && a.right().empty()) {
		cont=1;
	}
	else {
		ae(a.left(), cont);
		ae(a.right(), cont);
		cont++;
	}
}

bool equilibrado(bintree<char> a) {
	 
	int conti=1, contd=1;
	ae(a.left(),conti);
	ae(a.right(), contd);
	//cout << contd << " " << conti << endl;
	return abs(conti - contd) < 2;

}


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

void resuelveCaso() {
	bintree<char> arbol;
	int numero = 0, max = 0, cont = 0;
	bool hay = false;
	arbol = leerArbol('.'); // -1 es la repr. de arbol vacio
	if (equilibrado(arbol)) {
		cout << "SI";
	}
	else {
		cout << "NO";
	}
	cout << endl;
}

int main() {

	int numCasos;
	cin >> numCasos;
	for (int i = 0; i < numCasos; i++)
		resuelveCaso();

	system("pause");
	return 0;
}*/
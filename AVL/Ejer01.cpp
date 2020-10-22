/*
@author: Octavio Sales, DA_18
Ejercicio 1: dado un arbol decir si esta equilibrado.
*/
#include<stdio.h>
#include<iostream>
#include<fstream>
#include<algorithm>
#include "bintree_eda.h"
using namespace std;

template <typename T>
void altura(bintree<T> b, int &h) {
	//caso base: vacio
	if (b.empty()) {
		h = 0;
	}
	else {
		int hi = 0, hd = 0;
		// si es hoja:
		if (b.left().empty() && b.right().empty()) {
			h = 1;
		}// si no...
		else {
			if (!b.left().empty()) {
				altura(b.left(), hi);
			}
			if (!b.right().empty()) {
				altura(b.right(), hd);
			}
			if (hi >= hd) {
				h += hi;
			}
			else {
				h += hd;
			}
			h++;
		}
	}
}

template <typename T>
bool equilibrado(bintree<T> b) {
	// caso base: vacío
	if (b.empty()) {
		return true;
	}
	else {
		int hi = 0, hd = 0;
		altura(b.left(), hi);
		altura(b.right(), hd);
		if ((abs(hi - hd) <= 1) && equilibrado(b.left()) && equilibrado(b.right())) {
			return true;
		}
		else {
			return false;
		}
	}
}

//contruye un arbol 
template <class T>
bintree<T> leerArbol(const T& Vacio) {
	T elem;
	cin >> elem;
	if (elem == Vacio)
		return bintree<T>();
	else {
		bintree<T> hi = leerArbol(Vacio);
		bintree<T> hd = leerArbol(Vacio);
		return bintree<T>(hi, elem, hd);
	}
}

void resuelveCaso() {
	bintree<char> a = leerArbol('.');
	bool e= equilibrado(a);
	if (e) {
		cout << "SI" << "\n";
	}
	else {
		cout << "NO" << "\n";
	}
}

int main() {
	// ajustes para que cin extraiga directamente de un fichero
#ifndef DOMJUDGE
	std::ifstream in("casos.txt");
	auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif
	int numCasos;
	std::cin >> numCasos;
	for (int i = 0; i < numCasos; ++i)
		resuelveCaso();
	// para dejar todo como estaba al principio
#ifndef DOMJUDGE
	std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif
	return 0;
}
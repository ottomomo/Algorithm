#include<iostream>
#include<fstream>
#include<algorithm>
#include<stdio.h>
#include<stdlib.h>
#include"IndexPQ.h"
#include"PriorityQueue.h"
using namespace std;

typedef struct
{
	int n;
	int p;
	int total;
}datos;

class comparador
{
public:
	bool operator()(const datos &a,const  datos &b) {
		/*
		if(a.total % a.p ==0)
			a.n = a.total / a.p;
		else
			a.n = (a.total / a.p) + 1;
		return a.n > b.n;
		*/
		return (((a.n / a.p) + (a.n % a.p != 0)) > ((b.n / b.p) + (b.n % b.p != 0)));
	}
};

int partituras(PriorityQueue<datos, comparador>& q, int p) {
	p -= q.size();
	datos a;
	while (p > 0) {
		a = q.top();
		a.p++;
		q.pop();
		q.push(a);
		--p;
	}
	a =q.top();
	return (a.n / a.p) + (a.n % a.p != 0);
}
bool resuelveCaso() {
	int n, p;
	cin >> p;
	if (cin.fail()) return false;
	PriorityQueue<datos, comparador> q;
	cin >> n;
	datos a;
	for (int i = 0; i < n; ++i) {
		cin >> a.total;
		a.n = a.total;
		a.p = 1;
		q.push(a);
	}
	cout << partituras(q, p) << "\n";
	return true;
}

int main() {
	// ajustes para que cin extraiga directamente de un fichero
#ifndef DOMJUDGE
	std::ifstream in("casos.txt");
	auto cinbuf = std::cin.rdbuf(in.rdbuf());
#endif
	while (resuelveCaso());
	// para dejar todo como estaba al principio
#ifndef DOMJUDGE
	std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif
	return 0;
}


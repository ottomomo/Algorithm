#include<iostream>
#include<fstream>
#include<algorithm>
#include"IndexPQ.h"
#include"PriorityQueue.h"
using namespace std;

typedef struct
{
	int id;
	int t;
	int p;
}datos;

class comparador
{
public:
	bool operator()(datos a, datos b) {
		if (a.t == b.t) {
			return a.id < b.id;
		}
		else {
			return a.t < b.t;
		}
	}

};

void ucm(PriorityQueue<datos, comparador> q, int k) {
	datos aux;
	while (k>0)
	{
		aux = q.top();
		cout << aux.id << "\n";
		q.pop();
		aux.t += aux.p;
		q.push(aux);
		k--;
	}
	cout << "----" << "\n";
}

bool resuelveCaso() {
	int n;
	cin >> n;
	if (n == 0) return false;
	else {
		PriorityQueue<datos, comparador> q;
		datos d;
		for (int i = 0; i < n; ++i) {
			cin >> d.id;
			cin >> d.t;
			d.p = d.t;
			q.push(d);
		}
		int k;
		cin >> k;
		ucm(q, k);
		return true;
	}
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


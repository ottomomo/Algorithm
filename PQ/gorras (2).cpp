#include<fstream>
#include<iostream>
#include"IndexPQ.h"
#include"PriorityQueue.h"
using namespace std;

int gorras(PriorityQueue<int> q) {
	int e1=0, e2=0, acum=0;
	while (q.size()>1) {
		e1 = q.top();
		q.pop();
		e2 = q.top();
		q.pop();
		e1 += e2;
		acum += e1;
		q.push(e1);
	}
	return acum;
}

bool resuelveCaso() {
	int n;
	cin >> n;
	if (n == 0) return false;
	else {
		PriorityQueue<int,less<int>> q;
		int elem;
		for (int i = 0; i < n; ++i) {
			cin >> elem;
			q.push(elem);
		}
		cout << gorras(q) << "\n";
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
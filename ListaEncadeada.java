public class ListaEncadeada {
	Nodo inicio;
	Nodo fim;
	int tamanho = 0;
	
	void inserir(int valorDoNodo){
		Nodo novoNodo = new Nodo(valorDoNodo);
		
		if (inicio == null) {
			inicio = novoNodo;
			fim = novoNodo;
		} else {
			fim.proximo = novoNodo;
			fim = novoNodo;
		}
		
		tamanho += 1;
	}


	@Override
	public String toString() {
		String string = "";
		Nodo atual = inicio;
		while (atual != null) {
			string += atual.valor;
			
			if (atual.proximo != null) {
				string += " => ";
			}
			
			atual = atual.proximo;
		}
		return string;
	}
	
}
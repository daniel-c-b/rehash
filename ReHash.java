import java.util.Random;

public class ReHash {
	public static void main(String[] args) {
		// cria tabela de listas
		int tamanhoDaTabela = 100;
		
		ListaEncadeada[] matriz = new ListaEncadeada[tamanhoDaTabela];
		for (int i = 0; i < tamanhoDaTabela; i++) {
			matriz[i] = new ListaEncadeada();
		}

		Random random = new Random(1090); // seed
		
		int elementosAInserir = 100;
		int maxRandom = 100000;
		
		int totalColisoes = 0;
		int posicoesOcupadas = 0;
		boolean bloqueadoPorRehash = false;
		
		for (int i = 0; i < elementosAInserir; i++) {
			int numeroAleatorio = random.nextInt(maxRandom); 
			int posicao = numeroAleatorio % tamanhoDaTabela; 
			
			if (matriz[posicao].tamanho == 0) {
				if (posicoesOcupadas >= (int)(tamanhoDaTabela * 0.9)) {
					bloqueadoPorRehash = true;
					System.out.println("MATRIZ 90% OCUPADA");
					break;
				}
				posicoesOcupadas += 1;
			} else {
				totalColisoes += 1;
			}

			matriz[posicao].inserir(numeroAleatorio);
		}

		// estatisticas
		int pos1 = -1;
		int pos2 = -1;
		int pos3 = -1;
		
		int max1 = -1; 
		int max2 = -1; 
		int max3 = -1;

		for (int i = 0; i < tamanhoDaTabela; i++) {
			int colisoesNestaPosicao = 0;
			if (matriz[i].tamanho > 1) {
				colisoesNestaPosicao = matriz[i].tamanho - 1;
			}

			if (colisoesNestaPosicao > max1) {
				max3 = max2; 
				pos3 = pos2;
				
				max2 = max1; 
				pos2 = pos1;
				
				max1 = colisoesNestaPosicao; 
				pos1 = i;
			} else if (colisoesNestaPosicao > max2) {
				max3 = max2; 
				pos3 = pos2;
				
				max2 = colisoesNestaPosicao; 
				pos2 = i;
			} else if (colisoesNestaPosicao > max3) {
				max3 = colisoesNestaPosicao;
				pos3 = i;
			}
		}
		// estatisticas (print)
		System.out.println("colisoes ao todo: " + totalColisoes);
		System.out.println("posiçoes que mais colidiram: ");
		System.out.println("- " + pos1 + " com " + max1 + " colisoes");
		System.out.println("- " + pos2 + " com " + max2 + " colisoes");
		System.out.println("- " + pos2 + " com " + max3 + " colisoes");
		
		System.out.println("posiçoes livres da matriz: ");
		
		for (int i = 0; i < tamanhoDaTabela; i++) {
			if (matriz[i].tamanho == 0) {
				System.out.print(i + " "); // espaço pra ficar mais bonito
			}
		}
		
		// teste de busca
		System.out.println();
		System.out.println("FAZENDO TESTE DE BUSCA");
		
		int elementoProcurado1 = -1;
		int elementoProcurado2 = -1;
		int elementoProcurado3 = -1;

		if (pos1 != -1 && matriz[pos1].inicio.proximo != null) {
			elementoProcurado1 = matriz[pos1].inicio.proximo.valor;
		}
		if (pos2 != -1 && matriz[pos2].inicio.proximo != null) {
			elementoProcurado2 = matriz[pos2].inicio.proximo.valor;
		}
		if (pos3 != -1 && matriz[pos3].inicio.proximo != null) {
			elementoProcurado3 = matriz[pos3].inicio.proximo.valor;
		}

		int[] alvosBusca = {elementoProcurado1, elementoProcurado2, elementoProcurado3};
		int[] posicoesAlvo = {pos1, pos2, pos3};
		
		for (int k = 0; k < 3; k++) {
			int valorAlvo = alvosBusca[k];
			
			if (valorAlvo == -1) {
				continue;
			}

			int pos = valorAlvo % tamanhoDaTabela;
			
			System.out.println("buscando " + valorAlvo);
			
			Nodo atual = matriz[pos].inicio;
			int indicio = 0;
			boolean encontrado = false;

			while (atual != null) {
				if (atual.valor == valorAlvo) {
					System.out.println("ENCONTRADO: posiçao " + pos + ", indice " + indicio);
					encontrado = true;
					break;
				}
				atual = atual.proximo;
				indicio++;
			}

			if (!encontrado) {
				System.out.println("nao foi encontrado o elemento");
			}
		}
	}
}
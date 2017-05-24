import java.util.Stack;

public class BuscaProfundidade {

	private Grafo grafo;
	private Stack<Integer> pilha;

	public BuscaProfundidade(Grafo grafo) {
		this.grafo = grafo;
		pilha = new Stack<Integer>();
	}

	public String busca(String rotuloInicio, String rotuloFim) {
		String log = "";
		int inicio = grafo.localizaRotulo(rotuloInicio);
		int fim = grafo.localizaRotulo(rotuloFim);
		if (inicio == -1 || fim == -1) {
			return "Não Encontrado! Rótulo inválido!\n\n";
		}
		grafo.getVertices().get(inicio).setVisitado(true);
		pilha.push(inicio);
		while (!pilha.isEmpty()) {
			int elemento = pilha.get(pilha.size() - 1);
			if (elemento == fim) {
				log += "Encontrado! Pilha: ";
				for (Integer value : pilha) {
					log += grafo.getVertices().get(value).getRotulo() + " ";
				}
				grafo.cleanVisitados();
				return log += "\n\n";
			}
			int vertice = grafo.adjacenteNaoVisitado(elemento);
			if (vertice == -1) {
				pilha.pop();
			} else {
				grafo.getVertices().get(vertice).setVisitado(true);
				pilha.push(vertice);
			}
		}
		grafo.cleanVisitados();
		return "Não Encontrado!\n\n";

	}

}

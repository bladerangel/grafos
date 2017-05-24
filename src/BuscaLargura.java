import java.util.ArrayDeque;
import java.util.Queue;

public class BuscaLargura {
	private Grafo grafo;
	private Queue<Integer> fila;

	public BuscaLargura(Grafo grafo) {
		this.grafo = grafo;
		fila = new ArrayDeque<Integer>();
	}

	public String busca(String rotuloInicio, String rotuloFim) {
		String log = "";
		int inicio = grafo.localizaRotulo(rotuloInicio);
		int fim = grafo.localizaRotulo(rotuloFim);
		if (inicio == -1 || fim == -1) {
			return "Não Encontrado! Rótulo inválido!\n\n";
		} else if (inicio == fim) {
			return "Encontrado!\n\n";
		}
		grafo.getVertices().get(inicio).setVisitado(true);
		fila.offer(inicio);
		log += " Fila : " + grafo.getVertices().get(inicio).getRotulo() + " ";
		while (!fila.isEmpty()) {
			int elemento = fila.poll();
			int vertice = grafo.adjacenteNaoVisitado(elemento);
			while (vertice != -1) {
				if (vertice == fim) {
					grafo.cleanVisitados();
					return "Encontrado!" + log + grafo.getVertices().get(vertice).getRotulo() + "\n\n";
				}
				grafo.getVertices().get(vertice).setVisitado(true);
				fila.offer(vertice);
				log += grafo.getVertices().get(vertice).getRotulo() + " ";
				vertice = grafo.adjacenteNaoVisitado(elemento);
			}
		}
		grafo.cleanVisitados();
		return "Não Encontrado!" + log + "\n\n";

	}

}

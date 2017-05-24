import java.util.ArrayList;

public class Grafo {

	private ArrayList<Vertice> vertices;
	private ArrayList<ArrayList<Integer>> adjacentes;

	public Grafo() {
		vertices = new ArrayList<Vertice>();
		adjacentes = new ArrayList<ArrayList<Integer>>();
	}

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public void addVertice(String rotulo) {
		vertices.add(new Vertice(rotulo));
		adjacentes.add(new ArrayList<Integer>());
		for (ArrayList<Integer> arrayList : adjacentes) {
			for (int i = arrayList.size(); i < vertices.size(); i++) {
				arrayList.add(0);
			}
		}
	}

	public void addArco(String rotuloInicio, String rotuloFim) {
		int inicio = localizaRotulo(rotuloInicio);
		int fim = localizaRotulo(rotuloFim);
		adjacentes.get(inicio).set(fim, 1);
		adjacentes.get(fim).set(inicio, 1);
	}

	public int adjacenteNaoVisitado(int vertice) {
		for (int i = 0; i < vertices.size(); i++) {
			if (adjacentes.get(vertice).get(i) == 1 && vertices.get(i).isVisitado() == false)
				return i;
		}
		return -1;

	}

	public void cleanVisitados() {
		for (Vertice v : vertices) {
			v.setVisitado(false);
		}
	}

	public String printGrafo() {
		String log = "  ";
		for (Vertice vertice : vertices) {
			log += vertice.getRotulo() + " ";
		}
		log += "\n";
		for (int i = 0; i < vertices.size(); i++) {
			log += vertices.get(i).getRotulo() + " ";
			for (int j = 0; j < vertices.size(); j++) {
				log += adjacentes.get(i).get(j) + " ";
			}
			log += "\n";
		}
		log += "\n";
		return log;
	}

	public int localizaRotulo(String rotulo) {
		return vertices.indexOf(new Vertice(rotulo));
	}

}

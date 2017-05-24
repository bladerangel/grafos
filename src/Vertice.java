
public class Vertice {
	private String rotulo;
	private boolean visitado;

	public Vertice(String rotulo) {
		this.rotulo = rotulo;
	}

	@Override
	public boolean equals(Object rotulo) {
		if (rotulo == null) {
			return false;
		}
		return rotulo.equals(this.rotulo);
	}

	public String getRotulo() {
		return rotulo;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

}

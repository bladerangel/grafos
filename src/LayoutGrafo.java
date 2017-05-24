import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class LayoutGrafo {
	private Grafo grafo;
	private Graph<String, String> g;

	public LayoutGrafo() {
		g = new SparseGraph<String, String>();
		grafo = new Grafo();
	}

	public Grafo getGrafo() {
		return grafo;
	}

	public void addVertice(String rotulo) {
		grafo.addVertice(rotulo);
		g.addVertex(rotulo);
	}

	public void addArco(String rotuloInicio, String rotuloFim) {
		grafo.addArco(rotuloInicio, rotuloFim);
		g.addEdge("Arco " + rotuloInicio + "-" + rotuloFim, rotuloInicio, rotuloFim);
		g.addEdge("Arco " + rotuloFim + "-" + rotuloInicio, rotuloFim, rotuloInicio);

	}

	public BasicVisualizationServer<String, String> criarLayout() {

		Layout<String, String> layout = new CircleLayout(g);
		layout.setSize(new Dimension(300, 300));
		BasicVisualizationServer<String, String> vv = new BasicVisualizationServer<String, String>(layout);
		vv.setPreferredSize(new Dimension(350, 350));
		Transformer<String, Paint> vertexPaint = new Transformer<String, Paint>() {
			@Override
			public Paint transform(String arg) {
				return Color.GREEN;
			}
		};
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		return vv;
	}
}

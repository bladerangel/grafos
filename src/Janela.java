import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Janela extends JFrame {

	public static final String IMAGEM_GRAFO = "imagens/grafo.png";
	public static final String IMAGEM_LOG = "imagens/log.png";
	public static final String IMAGEM_BUSCAR = "imagens/buscar.png";
	public static final String IMAGEM_VERTICE = "imagens/vertice.png";
	public static final String IMAGEM_ARCO = "imagens/arco.png";
	public static final String IMAGEM_SOBRE = "imagens/sobre.png";
	public static final String IMAGEM_TESTE = "imagens/teste.png";

	private JPanel panel = new JPanel();
	private JPanel contentPane;

	private JMenuBar menuBar = new JMenuBar();
	private JMenu informação = new JMenu("Informação");
	private JMenuItem sobre = new JMenuItem("Sobre");

	private JScrollPane scrollPane = new JScrollPane();
	private JTextArea textArea = new JTextArea();

	private JLabel labelGrafo = new JLabel();
	private JLabel labelLog = new JLabel();

	private ButtonGroup group = new ButtonGroup();
	private JRadioButton largura = new JRadioButton("Largura");
	private JRadioButton profundidade = new JRadioButton("Profundidade");

	private JButton buscar = new JButton();
	private JButton addVertice = new JButton();
	private JButton addArco = new JButton();
	private JButton teste = new JButton();

	private LayoutGrafo layout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela frame = new Janela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void atualizar() {
		panel.removeAll();
		panel.add(layout.criarLayout());
		panel.revalidate();
		textArea.append(layout.getGrafo().printGrafo());
	}

	public void teste() {
		layout.addVertice("a");
		layout.addVertice("b");
		layout.addVertice("c");
		layout.addVertice("d");
		layout.addVertice("e");
		layout.addVertice("f");
		layout.addVertice("g");
		layout.addVertice("h");
		layout.addVertice("i");

		layout.addArco("a", "b");
		layout.addArco("a", "c");
		layout.addArco("a", "d");
		layout.addArco("a", "e");
		layout.addArco("b", "f");
		layout.addArco("f", "h");
		layout.addArco("d", "g");
		layout.addArco("g", "i");
		atualizar();

	}

	/**
	 * Create the frame.
	 */
	public Janela() {
		setResizable(false);
		layout = new LayoutGrafo();
		addVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rotulo = JOptionPane.showInputDialog(null, "Digite um rotulo");
				layout.addVertice(rotulo);
				atualizar();
			}
		});
		addArco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rotuloInicio = JOptionPane.showInputDialog(null, "Digite um rotulo de inicio");
				String rotuloFim = JOptionPane.showInputDialog(null, "Digite um rotulo de fim");
				layout.addArco(rotuloInicio, rotuloFim);
				atualizar();
			}
		});

		teste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teste();
			}
		});
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rotuloInicio = JOptionPane.showInputDialog(null, "Digite um rotulo de inicio");
				String rotuloFim = JOptionPane.showInputDialog(null, "Digite um rotulo de fim");
				if (largura.isSelected()) {
					BuscaLargura busca = new BuscaLargura(layout.getGrafo());
					textArea.append(busca.busca(rotuloInicio, rotuloFim));
				} else {
					BuscaProfundidade busca = new BuscaProfundidade(layout.getGrafo());
					textArea.append(busca.busca(rotuloInicio, rotuloFim));
				}

			}
		});
		sobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Desenvolvido por Pedro Rangel.");
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 596);
		setJMenuBar(menuBar);
		menuBar.add(informação);
		sobre.setIcon(new ImageIcon(IMAGEM_SOBRE));
		informação.add(sobre);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBounds(23, 85, 444, 345);
		contentPane.add(panel);
		addVertice.setIcon(new ImageIcon(IMAGEM_VERTICE));
		addVertice.setBounds(33, 441, 116, 33);
		contentPane.add(addVertice);
		addArco.setIcon(new ImageIcon(IMAGEM_ARCO));
		addArco.setBounds(191, 441, 116, 33);
		contentPane.add(addArco);
		scrollPane.setBounds(500, 85, 299, 345);
		contentPane.add(scrollPane);
		textArea.setEditable(false);
		textArea.setLocation(457, 0);
		textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		scrollPane.setViewportView(textArea);
		buscar.setIcon(new ImageIcon(IMAGEM_BUSCAR));
		buscar.setBounds(601, 471, 116, 33);
		contentPane.add(buscar);
		labelGrafo.setIcon(new ImageIcon(IMAGEM_GRAFO));
		labelGrafo.setBounds(90, 11, 311, 63);
		contentPane.add(labelGrafo);
		labelLog.setIcon(new ImageIcon(IMAGEM_LOG));
		labelLog.setBounds(544, 11, 210, 63);
		contentPane.add(labelLog);
		largura.setSelected(true);
		largura.setBounds(565, 441, 70, 23);
		contentPane.add(largura);
		profundidade.setBounds(647, 441, 123, 23);
		contentPane.add(profundidade);
		group.add(largura);
		group.add(profundidade);
		teste.setIcon(new ImageIcon(IMAGEM_TESTE));
		teste.setBounds(345, 441, 116, 33);
		contentPane.add(teste);

	}
}

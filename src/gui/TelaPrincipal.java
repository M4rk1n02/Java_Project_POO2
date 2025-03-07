package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import model.Exame;
import model.Paciente;
import service.PacienteService;
import service.ExameService;

public class TelaPrincipal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar barraMenu;
	private JMenu menuPaciente;
	private JMenu menuExame;
	private JMenuItem menuItemAdicionarPaciente;
	private JMenuItem menuItemAtualizarPaciente;
	private JMenuItem menuItemAdicionarExame;
	private JMenuItem menuItemAtualizarExame;
	private JScrollPane scrollPane;
	private JScrollPane scrollPaneExames;
	private JTable tablePacientes;
	private JTable tableExames;
	private PacienteService pacService;
	private ExameService examService;
	private JTabbedPane tabbed;
	
	
	public TelaPrincipal(ExameService examService, PacienteService pacService) {
		this.examService = examService;
		this.pacService = pacService;
		setTitle("Ger�ncia de Prontu�rios");
		setSize(480,360);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		barraMenu = new JMenuBar();
		
		menuPaciente = new JMenu("Paciente");
		barraMenu.add(menuPaciente);
		menuItemAdicionarPaciente = new JMenuItem("Adicionar");
		menuItemAdicionarPaciente.addActionListener(e -> new TelaCadastrarPaciente(pacService, this));
		menuItemAtualizarPaciente = new JMenuItem("Atualizar");
		menuPaciente.add(menuItemAdicionarPaciente);
		menuPaciente.add(menuItemAtualizarPaciente);
		
		menuExame = new JMenu("Exame");
		menuItemAdicionarExame = new JMenuItem("Adicionar");
		menuItemAdicionarExame.addActionListener(e -> new TelaCadastrarExame(examService, this));
		menuItemAtualizarExame = new JMenuItem("Atualizar");
		menuExame.add(menuItemAdicionarExame);
		menuExame.add(menuItemAtualizarExame);
		
		barraMenu.add(menuPaciente);
		barraMenu.add(menuExame);
		add(barraMenu, BorderLayout.NORTH);
		
		tablePacientes = new JTable();
		scrollPane = new JScrollPane(tablePacientes);
		
		tableExames = new JTable();
		scrollPaneExames = new JScrollPane(tableExames);
		
		tabbed = new JTabbedPane();
		tabbed.addTab("Pacientes", scrollPane);
		tabbed.addTab("Exames", scrollPaneExames);
		
		add(tabbed, BorderLayout.CENTER);
		
		loadTablePaciente();
		loadTableExame();
	}
	
	protected void loadTablePaciente() {
		List<Paciente> itens = pacService.getPacientes();
		System.out.println(itens);
		tablePacientes.setModel(new TabelaPacienteModel(itens));
	}

	public void loadTableExame() {
		// TODO Auto-generated method stub
		List<Exame> itens = examService.getExames();
		System.out.println(itens);
		tableExames.setModel(new TabelaExameModel(itens));
	}

}

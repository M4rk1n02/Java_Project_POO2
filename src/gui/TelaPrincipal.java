package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JSeparator;


import exception.ExameException;
import exception.PacienteException;
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
	private JMenuItem menuItemDeletarPaciente;
	private JMenuItem menuItemAdicionarExame;
	private JMenuItem menuItemAtualizarExame;
	private JMenuItem menuItemDeletarExame;
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
		setTitle("Gerencia de Prontuarios");
		setSize(600,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		barraMenu = new JMenuBar();
		
		menuPaciente = new JMenu("Gerenciar Pacientes");
		menuPaciente.add(new JSeparator());
		
				
		menuItemAdicionarPaciente = new JMenuItem("Adicionar");
		menuItemAdicionarPaciente.addActionListener(e -> new TelaCadastrarPaciente(pacService, this));
		menuItemAtualizarPaciente = new JMenuItem("Atualizar");
		menuItemAtualizarPaciente.addActionListener(e -> editarPaciente());
		menuItemDeletarPaciente = new JMenuItem("Deletar");
		menuItemDeletarPaciente.addActionListener(e -> deletarPaciente());
		
		menuPaciente.add(menuItemAdicionarPaciente);
		menuPaciente.add(menuItemAtualizarPaciente);
		menuPaciente.add(menuItemDeletarPaciente);
		menuPaciente.add(new JSeparator());

		
		barraMenu.add(menuPaciente);
				
		menuExame = new JMenu("Gerenciar Exames");	
		
		
		menuItemAdicionarExame = new JMenuItem("Adicionar");
		menuItemAdicionarExame.addActionListener(e -> new TelaCadastrarExame(examService, this));
		menuItemDeletarExame = new JMenuItem("Deletar");
		menuItemDeletarExame.addActionListener(e -> deletarExame());
		
		menuExame.add(menuItemAdicionarExame);
		menuExame.add(menuItemDeletarExame);
		menuExame.add(new JSeparator());

		
		barraMenu.add(menuExame);
		
		setJMenuBar(barraMenu);
		
		tablePacientes = new JTable();
		tablePacientes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane = new JScrollPane(tablePacientes);
		
		tableExames = new JTable();
		tableExames.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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
	
	private void atualizarPaciente() {
        loadTablePaciente();
    }

    private void atualizarExame() {
        loadTableExame();
    }

    private void deletarPaciente() {
    	int selectedRow = tablePacientes.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
                "Tem certeza que deseja excluir este paciente?", "Confirmação", 
                javax.swing.JOptionPane.YES_NO_OPTION);
            
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                Paciente paciente = ((TabelaPacienteModel) tablePacientes.getModel()).getPacienteAt(selectedRow);
                try {
					pacService.deletarPaciente(paciente);
				} catch (PacienteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                loadTablePaciente();
            }
        }
    }
    
    private void editarPaciente() {
        int selectedRow = tablePacientes.getSelectedRow();
        if (selectedRow != -1) {
            Paciente paciente = ((TabelaPacienteModel) tablePacientes.getModel()).getPacienteAt(selectedRow);
            new TelaEditarPaciente(pacService, this, paciente); 
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para editar.");
        }
    }

    private void deletarExame() {
    	int selectedRow = tableExames.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
                "Tem certeza que deseja excluir este exame?", "Confirmação", 
                javax.swing.JOptionPane.YES_NO_OPTION);
            
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                Exame exame = ((TabelaExameModel) tableExames.getModel()).getExameAt(selectedRow);
                try {
					examService.deletarExame(exame);
				} catch (ExameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                loadTableExame();
            }
        }
    }
    
}

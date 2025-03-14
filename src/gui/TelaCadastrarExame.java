package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exception.ExameException;
import model.Exame;
import model.Paciente;
import service.ExameService;

public class TelaCadastrarExame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ExameService examService;
	private TelaPrincipal main;
	private JPanel painelForm;
	private JPanel painelBotoes;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JButton btnSair;
	private JLabel lblDescricao;
	private JLabel lblDataExame;
	private JLabel lblPaciente;
	private JTextField txfDescricao;
	private JTextField txfDataExame;
	private JTextField txfPaciente; 

	
	public TelaCadastrarExame(ExameService examService, TelaPrincipal main) {
		this.examService = examService;
		this.main = main;
		setSize(400, 200);
		setResizable(false);
		setTitle("Tela de Cadastro do Exame");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		painelForm = new JPanel();
		lblDescricao = new JLabel("Descrição: ");
		lblDataExame = new JLabel("Data do Exame: ");
		lblPaciente = new JLabel("ID do Paciente: ");
		painelForm = new JPanel();
		txfDescricao = new JTextField(20); 
		txfDataExame = new JTextField(20);
		txfPaciente = new JTextField(20); 
		
		painelForm.add(lblDescricao);
		painelForm.add(txfDescricao);
		painelForm.add(lblDataExame);
		painelForm.add(txfDataExame);
		painelForm.add(lblPaciente);
		painelForm.add(txfPaciente);
		
		add(painelForm, BorderLayout.CENTER);
		painelBotoes = new JPanel();
		btnSair = new JButton("Sair");
		btnSair.addActionListener(e -> fecharTela());
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(e -> limparCampos());
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(e -> addExame());
		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnLimpar);
		painelBotoes.add(btnSair);
		add(painelBotoes, BorderLayout.SOUTH);
		
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void fecharTela() {
		this.hide();
	}
	
	private void limparCampos() {
		txfDescricao.setText("");
		txfDataExame.setText("");
		txfPaciente.setText("");
	}
	
	private void addExame() {
		String descricao = txfDescricao.getText();
		String dataExame = txfDataExame.getText();
		Long pacienteId = Long.parseLong(txfPaciente.getText());

		
		Exame exame = new Exame(0L, descricao, dataExame, new Paciente(pacienteId, "", ""));
		try {
			examService.adicionarExame(exame);
		} catch (ExameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Exame cadastrado com sucesso");

		
		txfDescricao.setText("");
		txfDataExame.setText("");
		txfPaciente.setText("");
		main.loadTableExame(); 
	}

}

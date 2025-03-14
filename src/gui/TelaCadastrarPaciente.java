package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exception.PacienteException;
import model.Paciente;
import service.PacienteService;

public class TelaCadastrarPaciente extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PacienteService pacService;
	private TelaPrincipal main;
	private JPanel painelForm;
	private JPanel painelBotoes;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JButton btnSair;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JTextField txfNome;
	private JTextField txfCpf;
	
	
	
	public TelaCadastrarPaciente(PacienteService pacService, TelaPrincipal main) {
		this.pacService = pacService;
		this.main = main;
		setSize(400, 200);
		setResizable(false);
		setTitle("Tela de Cadastro de Paciente");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		painelForm = new JPanel();
		lblNome = new JLabel("Nome: ");
		lblCpf = new JLabel("CPF: ");
		txfNome = new JTextField(24);
		txfCpf = new JTextField(24);
		painelForm.add(lblNome);
		painelForm.add(txfNome);
		painelForm.add(lblCpf);
		painelForm.add(txfCpf);
		add(painelForm, BorderLayout.CENTER);
		
		painelBotoes = new JPanel();
		btnSair = new JButton("Sair");
		btnSair.addActionListener(e -> fecharTela());
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(e -> limparCampos());
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(e -> addPaciente());
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
		txfNome.setText("");
		txfCpf.setText("");
	}
	
	private void addPaciente() {
		
		String nome = txfNome.getText().trim();
	    String cpf = txfCpf.getText().trim();

	    if (nome.isEmpty() || cpf.isEmpty()) {
	    	JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    if (!validarCpf(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido! O CPF deve conter 11 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
	       
		Paciente p = new Paciente(0L, txfCpf.getText(), txfNome.getText());
		try {
			pacService.adicionarPaciente(p);
		} catch (PacienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso");
		txfCpf.setText("");
		txfNome.setText("");
		main.loadTablePaciente();
	}
	
	private boolean validarCpf(String cpf) {
        return cpf.matches("\\d{11}");
    }
	
}

package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exception.PacienteException;
import model.Paciente;
import service.PacienteService;

public class TelaEditarPaciente extends JDialog {

    private static final long serialVersionUID = 1L;

    private PacienteService pacService;
    private TelaPrincipal main;
    private Paciente paciente;

    private JTextField txfNome;
    private JTextField txfCpf;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaEditarPaciente(PacienteService pacService, TelaPrincipal main, Paciente paciente) {
        this.pacService = pacService;
        this.main = main;
        this.paciente = paciente;

        setTitle("Editar Paciente");
        setSize(360, 200);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelForm = new JPanel();
        painelForm.setLayout(new GridLayout(2, 2));

        JLabel lblNome = new JLabel("Nome: ");
        JLabel lblCpf = new JLabel("CPF: ");
        txfNome = new JTextField(paciente.getNome(), 24);
        txfCpf = new JTextField(paciente.getCpf(), 24);

        painelForm.add(lblNome);
        painelForm.add(txfNome);
        painelForm.add(lblCpf);
        painelForm.add(txfCpf);

        add(painelForm, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarAlteracoes());
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> fecharTela());

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        add(painelBotoes, BorderLayout.SOUTH);

        setModal(true);
        setVisible(true);
    }

    private void salvarAlteracoes() {
        String novoNome = txfNome.getText();
        String novoCpf = txfCpf.getText();

        if (novoNome.isEmpty() || novoCpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        paciente.setNome(novoNome);
        paciente.setCpf(novoCpf);

        try {
            pacService.atualizarPaciente(paciente);
            JOptionPane.showMessageDialog(this, "Paciente atualizado com sucesso.");
            main.loadTablePaciente(); 
            fecharTela();
        } catch (PacienteException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o paciente.");
            e.printStackTrace();
        }
    }

    private void fecharTela() {
        this.hide(); 
    }
}


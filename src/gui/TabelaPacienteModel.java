package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Paciente;

public class TabelaPacienteModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Paciente> pacientes;
	private String[] colunas = {"ID", "CPF", "Nome"};
	
	public TabelaPacienteModel(List<Paciente> itens) {
		this.pacientes = itens;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return pacientes.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}
	
	@Override
	public String getColumnName(int index) {
		return colunas[index];
	}
	
	public Paciente getPacienteAt(int rowIndex) {
		// TODO Auto-generated method stub
		if (rowIndex >= 0 && rowIndex < pacientes.size()) {
	        return pacientes.get(rowIndex);
	    }
	    return null;
	};

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Paciente p = pacientes.get(rowIndex);
		return switch(columnIndex) {
		case 0 -> p.getId();
		case 1 -> p.getCpf();
		case 2 -> p.getNome();
		default -> null;
		};
	}

}

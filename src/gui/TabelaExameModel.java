package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Exame;
import model.Paciente;

public class TabelaExameModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Exame> exames;
	private String[] colunas = {"ID", "Descrição", "Data do Exame", "Paciente"};
	
	public TabelaExameModel(List<Exame> itens) {
		this.exames = itens;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		 return exames.size();
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
	
	public Exame getExameAt(int rowIndex) {
		// TODO Auto-generated method stub
		if (rowIndex >= 0 && rowIndex < exames.size()) {
	        return exames.get(rowIndex);
	    }
	    return null;
	};

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Exame exame = exames.get(rowIndex);
		return switch(columnIndex) {
        case 0 -> exame.getId();
        case 1 -> exame.getDescricao();
        case 2 -> exame.getData_exame();
        case 3 -> {
            Paciente paciente = exame.getPaciente();
            if (paciente != null) {
                yield paciente.getNome();
            } else {
                yield "Paciente não associado";             }
        }
        default -> null;
		};
	}


}


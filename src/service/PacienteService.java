package service;

import java.util.List;

import dao.GenericDAO;
import model.Paciente;
import exception.ExameException;
import exception.PacienteException;


public class PacienteService {

	private GenericDAO<Paciente, Long> daoPaciente;
	
	public PacienteService(GenericDAO<Paciente, Long> dao) {
		this.daoPaciente = dao;
	}
	
	public void adicionarPaciente(Paciente p) throws PacienteException {
		try { 			
			daoPaciente.add(p);
		}catch (Exception er) {
			throw new PacienteException("Erro ao adicionar paciente " ,er);
		}
	}
	
	public Paciente localizarPacientePorId(Long id) throws PacienteException {
		try {
			return daoPaciente.findByID(id);			
		} catch (Exception er) {
			throw new PacienteException("Erro ao procurar Id " ,er);
		}
	}
	
	
	public void deletarPaciente(Paciente p) throws PacienteException {
		try {
			daoPaciente.delete(p);			
		} catch (Exception er) {
			throw new PacienteException("Erro ao deletar paciente " ,er);
		}
	}
	
	public List<Paciente> getPacientes() {
		return daoPaciente.getAll();
	}
	
	public void atualizarPaciente(Paciente p) throws PacienteException {
		try {
			daoPaciente.update(p);			
		} catch (Exception er){
			throw new PacienteException("Erro ao atualizar " ,er);
		}
	}
}

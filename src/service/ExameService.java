package service;

import java.util.List;

import dao.GenericDAO;
import exception.ExameException;
import model.Exame;

public class ExameService {
	
	private GenericDAO<Exame, Long> daoExame;
	
	public ExameService(GenericDAO<Exame, Long> dao) {
		this.daoExame = dao;
	}
	
	public void adicionarExame(Exame e) throws ExameException {
		try {
            daoExame.add(e);
        } catch (Exception er) {
            throw new ExameException("Erro ao adicionar exame", er);
        }
	}
	
	public Exame localizarExamePorId(Long id) throws ExameException {
		try {
			return daoExame.findByID(id);			
		} catch (Exception er) {
			throw new ExameException("Erro ao procurar Id " ,er);
		}
	}
	
	
	public void deletarExame(Exame e) throws ExameException {
		try {
		daoExame.delete(e);
		} catch (Exception er) {
			throw new ExameException("Erro ao deletar exame " ,er);
		}
	}
	
	public List<Exame> getExames(){
		return daoExame.getAll();
	}
	
	public void atualizarExame(Exame e) throws ExameException {
		try {
		daoExame.update(e);
		} catch (Exception er) {
			throw new ExameException("Erro ao deletar exame " ,er);
		}
	}
}

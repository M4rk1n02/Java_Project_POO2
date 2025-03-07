package app;

import javax.swing.SwingUtilities;

import dao.ExameDAO;
import dao.PacienteDAO;
import db.MySQLDatabaseConnection;
import gui.TelaPrincipal;
import service.ExameService;
import service.PacienteService;

public class Aplicacao {

	public static void main(String[] args) {
		
		PacienteService pacServ = 
				new PacienteService(new PacienteDAO(new MySQLDatabaseConnection()));
		
		 ExameService examServ = 
				 new ExameService(new ExameDAO(new MySQLDatabaseConnection())); 
		
		SwingUtilities.invokeLater(() 
				-> new TelaPrincipal(examServ, pacServ).setVisible(true));
	}
}

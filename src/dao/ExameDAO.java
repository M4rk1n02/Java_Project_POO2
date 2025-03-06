package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DatabaseConnection;
import model.Exame;
import model.Paciente;

public class ExameDAO implements GenericDAO<Exame, Long> {
    
    private DatabaseConnection db;
    
    public ExameDAO(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public void add(Exame obj) {
        try (PreparedStatement pstm = db.getConnection()
                .prepareStatement("INSERT INTO EXAMES (descricao, dataExame, paciente_id) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, obj.getDescricao());
            pstm.setString(2, obj.getDataExame());
            pstm.setLong(3, obj.getPaciente().getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Exame findByID(Long id) {
        try (PreparedStatement pstm = db.getConnection().prepareStatement("SELECT * FROM EXAMES WHERE id = ?")) {
            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                PacienteDAO pacienteDAO = new PacienteDAO(db);
                Paciente paciente = pacienteDAO.findByID(rs.getLong("paciente_id"));
                return new Exame(rs.getLong("id"), rs.getString("descricao"), rs.getString("dataExame"), paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Exame obj) {
        try (PreparedStatement pstm = db.getConnection().prepareStatement("DELETE FROM EXAMES WHERE id = ?")) {
            pstm.setLong(1, obj.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Exame obj) {
        try (PreparedStatement pstm = db.getConnection().prepareStatement("UPDATE EXAMES SET descricao = ?, dataExame = ?, paciente_id = ? WHERE id = ?")) {
            pstm.setString(1, obj.getDescricao());
            pstm.setString(2, obj.getDataExame());
            pstm.setLong(3, obj.getPaciente().getId());
            pstm.setLong(4, obj.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Exame> getAll() {
        List<Exame> exames = new ArrayList<>();
        try (PreparedStatement pstm = db.getConnection().prepareStatement("SELECT * FROM EXAMES");
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                PacienteDAO pacienteDAO = new PacienteDAO(db);
                Paciente paciente = pacienteDAO.findByID(rs.getLong("paciente_id"));
                Exame exame = new Exame(rs.getLong("id"), rs.getString("descricao"), rs.getString("dataExame"), paciente);
                exames.add(exame);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exames;
    }
}

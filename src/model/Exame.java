package model;

import java.util.Objects;

public class Exame {

	private Long id;
	private String descricao;
	private String data_exame;
	private Paciente paciente;
	
	public Exame(Long id, String descricao, String data_exame, Paciente paciente) {
		this.id = id;
		this.descricao = descricao;
		this.data_exame = data_exame;
		this.paciente = paciente;
	}

	public Exame() {
		
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData_exame() {
		return data_exame;
	}

	public void setData_exame(String data_exame) {
		this.data_exame = data_exame;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exame other = (Exame) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Exame [id=" + id + ", descricao=" + descricao + ", dataExame=" + data_exame + "]";
	}
	
	
	
	
	
	
}

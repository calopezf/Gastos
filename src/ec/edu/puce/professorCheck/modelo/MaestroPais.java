package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AmPais")
public class MaestroPais implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "AmPaisCod")
	private String codigo;

	@Column(name = "AmPaisNom")
	private String nombrePais;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

}
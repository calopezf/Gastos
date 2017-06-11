package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AmScn")
public class SocioNegocio implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "AmScnCod")
	private Integer codigo;
			
	@Column(name = "AmScNRazS")
	private String razonSocial;
	
	@Column(name = "AmScNIdF")
	private String idFiscal;

	@Column(name = "AmScNEma")
	private String mail;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getIdFiscal() {
		return idFiscal;
	}

	public void setIdFiscal(String idFiscal) {
		this.idFiscal = idFiscal;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CmCcs")
public class MaestroCentroCosto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "AmComCod")
	private Integer codigoCompania;
	@Id	
	@Column(name = "CmCCsCod")
	private String codigoCentroCostos;

	@Column(name = "CmCCsNom")
	private String NombreCentroCostos;

	public Integer getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(Integer codigoCompania) {
		this.codigoCompania = codigoCompania;
	}

	public String getCodigoCentroCostos() {
		return codigoCentroCostos;
	}

	public void setCodigoCentroCostos(String codigoCentroCostos) {
		this.codigoCentroCostos = codigoCentroCostos;
	}

	public String getNombreCentroCostos() {
		return NombreCentroCostos;
	}

	public void setNombreCentroCostos(String nombreCentroCostos) {
		NombreCentroCostos = nombreCentroCostos;
	}

}
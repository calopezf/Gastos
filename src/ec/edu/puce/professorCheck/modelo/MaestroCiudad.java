package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CmCcs")
public class MaestroCiudad implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id	
	@Column(name = "AmComCod")
	private String codigoCompania;

	@Column(name = "CmCCsCod")
	private String codigoCentroCostos;

	@Column(name = "CmCCsNom")
	private String NombreCentroCostos;

	public String getCodigoCompania() {
		return codigoCompania;
	}

	public void setCodigoCompania(String codigoCompania) {
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
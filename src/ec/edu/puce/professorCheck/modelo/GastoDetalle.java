package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

import ec.edu.puce.professorCheck.constantes.EnumEstado;
import ec.edu.puce.professorCheck.constantes.EnumEstadoGasto;

@Entity
@Table(name = "WdGasLin")
@TableGenerator(table = "SECUENCIAS", name = "GASTO", pkColumnName = "NOMBRE", pkColumnValue = "SYLLABUS", valueColumnName = "VALOR", allocationSize = 1)
public class GastoDetalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "WdGasLinId")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GASTO")
	private Long id;
	@Column(name = "WcGasTipDEp", nullable = false)
	private String tipoDocumento;
	@Column(name = "WcGasLinUrlDoc")
	private String urlDoc;
	@Column(name = "AmScnCod", nullable = true)
	private Integer codigoSocio;
	@Column(name = "AmComCod", nullable = true)
	private Integer companiaCodigo;
	@Column(name = "WdGasLin", nullable = false)
	private BigDecimal monto;
	@Column(name = "WdGasLinObs", nullable = false, length = 2000)
	private String observacion;
	@Column(name = "WdGasLinConRec", nullable = false, length = 2000)
	private String conceptoRechazo;
	@Column(name = "WdGasLinEst")
	@Enumerated(EnumType.STRING)
	private EnumEstadoGasto estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getUrlDoc() {
		return urlDoc;
	}

	public void setUrlDoc(String urlDoc) {
		this.urlDoc = urlDoc;
	}

	public Integer getCodigoSocio() {
		return codigoSocio;
	}

	public void setCodigoSocio(Integer codigoSocio) {
		this.codigoSocio = codigoSocio;
	}

	public Integer getCompaniaCodigo() {
		return companiaCodigo;
	}

	public void setCompaniaCodigo(Integer companiaCodigo) {
		this.companiaCodigo = companiaCodigo;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getConceptoRechazo() {
		return conceptoRechazo;
	}

	public void setConceptoRechazo(String conceptoRechazo) {
		this.conceptoRechazo = conceptoRechazo;
	}

	public EnumEstadoGasto getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoGasto estado) {
		this.estado = estado;
	}

}
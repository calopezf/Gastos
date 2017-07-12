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

@Entity
@Table(name = "WcFon")
@TableGenerator(table = "SECUENCIAS", name = "GEN_FONDO", pkColumnName = "NOMBRE", pkColumnValue = "FONDO_FIJO", valueColumnName = "VALOR", allocationSize = 1)
public class FondoFijo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "WcFonId")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_FONDO")
	private Long id;
	@Column(name = "WcFonNom", nullable = false, length = 200)
	private String nombre;
	@Column(name = "WcFonFec", length = 200)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Column(name = "WcFonCon", nullable = false, length = 2000)
	private String concepto;
	@Column(name = "WcFonMon", nullable = false)
	private BigDecimal monto;
	@Column(name = "WcFonEst")
	@Enumerated(EnumType.STRING)
	private EnumEstado estado;
	@Column(name = "AmScnCod", nullable = true)
	private Integer codigoSocio;
	@Column(name = "AmComCod", nullable = true)
	private Integer companiaCodigo;
	@Column(name = "NmEmpFlg", nullable = true, length = 1)
	private String flagEmpleado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public EnumEstado getEstado() {
		return estado;
	}

	public void setEstado(EnumEstado estado) {
		this.estado = estado;
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

	public String getFlagEmpleado() {
		return flagEmpleado;
	}

	public void setFlagEmpleado(String flagEmpleado) {
		this.flagEmpleado = flagEmpleado;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

}
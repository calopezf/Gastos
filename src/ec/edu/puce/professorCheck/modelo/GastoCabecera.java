package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

import ec.edu.puce.professorCheck.constantes.EnumEstado;

@Entity
@Table(name = "WcGas")
@TableGenerator(table = "SECUENCIAS", name = "GASTO", pkColumnName = "NOMBRE", pkColumnValue = "SYLLABUS", valueColumnName = "VALOR", allocationSize = 1)
public class GastoCabecera implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "WcGasId")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GASTO")
	private Long id;
	@Enumerated(EnumType.STRING)
	private EnumEstado tipoDocumento;
	@Column(name = "AmScnCod", nullable = false)
	private Integer usuario;
	@Column(name = "AmUsrFlg", nullable = false)
	private String usuarioFlag;
	@Column(name = "WcGasFec")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date fechaLegalizacion;
	@Column(name = "WcGasCons", nullable = false)
	private String consecutivo;
	@Column(name = "WcGasConc")
	private String concepto;
	@Column(name = "WcGasEst")
	@Enumerated(EnumType.STRING)
	private EnumEstado estado;
	@Column(name = "AmScnCod", nullable = true, insertable = false, updatable = false)
	private Integer codigoSocio;
	@Column(name = "AmComCod", nullable = true)
	private Integer companiaCodigo;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private List<GastoDetalle> gastosDetalle;

	@Column(name = "WcPlaId")
	private Long idPlanificacion;

	@ManyToOne(optional = false)
	@JoinColumn(name = "WcPlaId", referencedColumnName = "WcPlaId", insertable = false, updatable = false)
	private Planificacion planificacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumEstado getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(EnumEstado tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public String getUsuarioFlag() {
		return usuarioFlag;
	}

	public void setUsuarioFlag(String usuarioFlag) {
		this.usuarioFlag = usuarioFlag;
	}

	public Date getFechaLegalizacion() {
		return fechaLegalizacion;
	}

	public void setFechaLegalizacion(Date fechaLegalizacion) {
		this.fechaLegalizacion = fechaLegalizacion;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
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

	public List<GastoDetalle> getGastosDetalle() {
		return gastosDetalle;
	}

	public void setGastosDetalle(List<GastoDetalle> gastosDetalle) {
		this.gastosDetalle = gastosDetalle;
	}

	public Long getIdPlanificacion() {
		return idPlanificacion;
	}

	public void setIdPlanificacion(Long idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}

	public Planificacion getPlanificacion() {
		return planificacion;
	}

	public void setPlanificacion(Planificacion planificacion) {
		this.planificacion = planificacion;
	}

}
package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

import ec.edu.puce.professorCheck.constantes.EnumEstado;

@Entity
@Table(name = "WdPlM")
@TableGenerator(table = "SECUENCIAS", name = "GEN_PLANIFICACION_MARCA", pkColumnName = "NOMBRE", pkColumnValue = "SYLLABUS", valueColumnName = "VALOR", allocationSize = 1)
public class PlanificacionMarca implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "WdPlMId")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_PLANIFICACION_MARCA")
	private Long id;
	@Column(name = "WcPlaId")
	private Long idPlanificacion;
	@Column(name = "WdPlRId")
	private Long idRuta;
	@Column(name = "AmComCod")
	private Integer codigoCompania;
	@Column(name = "CmCCsCod", length = 200)
	private String codigoCentroCostos;
	@Column(name = "CmCCsNom")
	private String nombreCentroCostos;
	@Column(name = "WdPlMPor", nullable = false, length = 200)
	private Double porcentaje;
	@ManyToOne(optional = false)
	@JoinColumn(name = "WdPlRId", referencedColumnName = "WdPlRId", insertable = false, updatable = false)
	private PlanificacionRuta ruta;
	@ManyToOne(optional = false)
	@JoinColumn(name = "WcPlaId", referencedColumnName = "WcPlaId", insertable = false, updatable = false)
	private Planificacion planificacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPlanificacion() {
		return idPlanificacion;
	}

	public void setIdPlanificacion(Long idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}

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

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public PlanificacionRuta getRuta() {
		return ruta;
	}

	public void setRuta(PlanificacionRuta ruta) {
		this.ruta = ruta;
	}

	public Planificacion getPlanificacion() {
		return planificacion;
	}

	public void setPlanificacion(Planificacion planificacion) {
		this.planificacion = planificacion;
	}

	public String getNombreCentroCostos() {
		return nombreCentroCostos;
	}

	public void setNombreCentroCostos(String nombreCentroCostos) {
		this.nombreCentroCostos = nombreCentroCostos;
	}

	public Long getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(Long idRuta) {
		this.idRuta = idRuta;
	}

}
package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
@Table(name = "WdPlR")
@TableGenerator(table = "SECUENCIAS", name = "GEN_PLANIFICACION_RUTA", pkColumnName = "NOMBRE", pkColumnValue = "SYLLABUS", valueColumnName = "VALOR", allocationSize = 1)
public class PlanificacionRuta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "WdPlRId")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_PLANIFICACION_RUTA")
	private Long id;
	@Column(name = "WcPlaId")
	private Long idPlanificacion;
	@Column(name = "AmPaisCod", length = 10)
	private String codigoPais;
	@Column(name = "AdPrvCod", length = 10)
	private String codigoProvincia;
	@Column(name = "AdCiuCod", length = 10)
	private String codigoCiudad;
	@Column(name = "AdCiuNom")
	private String nombreCiudad;
	@ManyToOne(optional = false)
	@JoinColumn(name = "WcPlaId", referencedColumnName = "WcPlaId", insertable = false, updatable = false)
	private Planificacion planificacion;
	@OneToMany(mappedBy = "ruta")
	private List<PlanificacionMarca> marcas;

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

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	public String getCodigoCiudad() {
		return codigoCiudad;
	}

	public void setCodigoCiudad(String codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}

	public Planificacion getPlanificacion() {
		return planificacion;
	}

	public void setPlanificacion(Planificacion planificacion) {
		this.planificacion = planificacion;
	}

	public List<PlanificacionMarca> getMarcas() {
		if (marcas == null) {
			marcas = new ArrayList<PlanificacionMarca>();
		}
		return marcas;
	}

	public void setMarcas(List<PlanificacionMarca> marcas) {
		this.marcas = marcas;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

}
package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

import ec.edu.puce.professorCheck.constantes.EnumEstado;

@Entity
@Table(name = "WcAnt")
@TableGenerator(table = "SECUENCIAS", name = "GEN_ANTICIPO", pkColumnName = "NOMBRE", pkColumnValue = "ANTICIPO", valueColumnName = "VALOR", allocationSize = 1)
public class Anticipo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6484381727772456948L;
	@Id
	@Column(name = "WcAntId")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_ANTICIPO")
	private Long id;
	@Column(name = "WcAntFec", length = 200)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Column(name = "WcAntCon", nullable = false, length = 2000)
	private String concepto;
	@Column(name = "WcAntEst")
	@Enumerated(EnumType.STRING)
	private EnumEstado estado;
	@Column(name = "AmScnCod", nullable = true)
	private Integer codigoSocio;
	@Column(name = "AmComCod", nullable = true)
	private Integer companiaCodigo;
	@Column(name = "NmEmpFlg", nullable = true, length = 1)
	private String flagEmpleado;

	@OneToMany(mappedBy = "anticipo")
	private List<AnticipoRuta> rutas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<AnticipoRuta> getRutas() {
		if (rutas == null) {
			rutas = new ArrayList<AnticipoRuta>();
		}
		return rutas;
	}

	public void setRutas(List<AnticipoRuta> rutas) {
		this.rutas = rutas;
	}

}
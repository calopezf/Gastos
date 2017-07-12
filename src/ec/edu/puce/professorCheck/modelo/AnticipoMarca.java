package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "WdAnM")
@TableGenerator(table = "SECUENCIAS", name = "GEN_ANTICIPO_MARCA", pkColumnName = "NOMBRE", pkColumnValue = "ANTICIPO_MARCA", valueColumnName = "VALOR", allocationSize = 1)
public class AnticipoMarca implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6030333926764970374L;

	@Id
	@Column(name = "WdAnMId")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_ANTICIPO_MARCA")
	private Long id;
	@Column(name = "WcAntId")
	private Long idAnticipo;
	@Column(name = "WdAnRId")
	private Long idRuta;
	@Column(name = "AmComCod")
	private Integer codigoCompania;
	@Column(name = "CmCCsCod", length = 200)
	private String codigoCentroCostos;
	@Column(name = "CmCCsNom")
	private String nombreCentroCostos;
	@Column(name = "WdAnMMnt", nullable = false)
	private BigDecimal monto;
	@ManyToOne(optional = false)
	@JoinColumn(name = "WdAnRId", referencedColumnName = "WdAnRId", insertable = false, updatable = false)
	private AnticipoRuta ruta;
	@ManyToOne(optional = false)
	@JoinColumn(name = "WcAntId", referencedColumnName = "WcAntId", insertable = false, updatable = false)
	private Anticipo anticipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAnticipo() {
		return idAnticipo;
	}

	public void setIdAnticipo(Long idAnticipo) {
		this.idAnticipo = idAnticipo;
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

	public BigDecimal getMonto() {
		return this.monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public AnticipoRuta getRuta() {
		return ruta;
	}

	public void setRuta(AnticipoRuta ruta) {
		this.ruta = ruta;
	}

	public Anticipo getAnticipo() {
		return anticipo;
	}

	public void setAnticipo(Anticipo anticipo) {
		this.anticipo = anticipo;
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
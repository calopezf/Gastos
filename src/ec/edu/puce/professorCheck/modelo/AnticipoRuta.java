package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "WdAnR")
@TableGenerator(table = "SECUENCIAS", name = "GEN_ANTICIPO_RUTA", pkColumnName = "NOMBRE", pkColumnValue = "ANTICIPO_RUTA", valueColumnName = "VALOR", allocationSize = 1)
public class AnticipoRuta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3480021461215670694L;

	@Id
	@Column(name = "WdAnRId")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_ANTICIPO_RUTA")
	private Long id;
	@Column(name = "WcAntId")
	private Long idAnticipo;
	@Column(name = "AmPaisCod", length = 10)
	private String codigoPais;
	@Column(name = "AdPrvCod", length = 10)
	private String codigoProvincia;
	@Column(name = "AdCiuCod", length = 10)
	private String codigoCiudad;
	@Column(name = "AdCiuNom")
	private String nombreCiudad;
	@ManyToOne(optional = false)
	@JoinColumn(name = "WcAntId", referencedColumnName = "WcAntId", insertable = false, updatable = false)
	private Anticipo anticipo;
	@OneToMany(mappedBy = "ruta")
	private List<AnticipoMarca> marcas;

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

	public Anticipo getAnticipo() {
		return anticipo;
	}

	public void setAnticipo(Anticipo anticipo) {
		this.anticipo = anticipo;
	}

	public List<AnticipoMarca> getMarcas() {
		if (marcas == null) {
			marcas = new ArrayList<AnticipoMarca>();
		}
		return marcas;
	}

	public void setMarcas(List<AnticipoMarca> marcas) {
		this.marcas = marcas;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

}
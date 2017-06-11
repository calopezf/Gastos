package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.edu.puce.professorCheck.constantes.EnumEstado;
import ec.edu.puce.professorCheck.constantes.EnumTipoParametro;
import ec.edu.puce.professorCheck.constantes.EnumTipoParametroMaestroProceso;

@Entity
@Table(name = "WcMPr")
public class ParametroMaestroProceso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "WcMPrCod")
	private String codigo;// de ParametroEnum

	@Column(name = "WcMPrNom")
	private String nombre;// de ParametroEnum

	@Enumerated(EnumType.STRING)
	@Column(name = "WcMPrTip", nullable = false)
	private EnumTipoParametroMaestroProceso tipo;

	@Column(name = "WcMPrDes", nullable = false)
	private String descripcion;

	@Enumerated(EnumType.STRING)
	@Column(name = "WcMPrEst", nullable = false)
	private EnumEstado estado;

	@Column(name = "WcMPrCodP", nullable = true)
	private String codigo_padre;
	@Transient
	private boolean registroNuevo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EnumTipoParametroMaestroProceso getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoParametroMaestroProceso tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EnumEstado getEstado() {
		return estado;
	}

	public void setEstado(EnumEstado estado) {
		this.estado = estado;
	}

	public boolean isRegistroNuevo() {
		return registroNuevo;
	}

	public void setRegistroNuevo(boolean registroNuevo) {
		this.registroNuevo = registroNuevo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public String getCodigo_padre() {
		return codigo_padre;
	}

	public void setCodigo_padre(String codigo_padre) {
		this.codigo_padre = codigo_padre;
	}

	@Override
	public String toString() {
		return "Parametro{" + "nombre=" + nombre + ", tipo=" + tipo
				+ ", descripcion=" + descripcion
				+ ", estado=" + estado + '}';
	}

}
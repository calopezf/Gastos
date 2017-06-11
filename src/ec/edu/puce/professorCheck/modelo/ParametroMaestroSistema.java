package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.edu.puce.professorCheck.constantes.EnumEstado;
import ec.edu.puce.professorCheck.constantes.EnumTipoParametro;

@Entity
@Table(name = "WcMSi")
public class ParametroMaestroSistema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "WcMSidCod")
	private String codigo;// de ParametroEnum

	@Column(name = "WcMSiNom")
	private String nombre;// de ParametroEnum

	@Enumerated(EnumType.STRING)
	@Column(name = "WcMSiTip", nullable = false)
	private EnumTipoParametro tipo;

	@Column(name = "WcMSiDes", nullable = false)
	private String descripcion;

	@Enumerated(EnumType.STRING)
	@Column(name = "WcMSiEst", nullable = false)
	private EnumEstado estado;

	@Transient
	private boolean registroNuevo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EnumTipoParametro getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoParametro tipo) {
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

	@Override
	public String toString() {
		return "Parametro{" + "nombre=" + nombre + ", tipo=" + tipo
				+ ", descripcion=" + descripcion
				+ ", estado=" + estado + '}';
	}

}
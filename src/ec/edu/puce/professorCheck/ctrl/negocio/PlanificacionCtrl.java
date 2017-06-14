package ec.edu.puce.professorCheck.ctrl.negocio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import ec.edu.puce.professorCheck.constantes.EnumEstado;
import ec.edu.puce.professorCheck.crud.ServicioCrud;
import ec.edu.puce.professorCheck.ctrl.BaseCtrl;
import ec.edu.puce.professorCheck.modelo.MaestroCentroCosto;
import ec.edu.puce.professorCheck.modelo.MaestroCiudad;
import ec.edu.puce.professorCheck.modelo.MaestroPais;
import ec.edu.puce.professorCheck.modelo.MaestroProvincia;
import ec.edu.puce.professorCheck.modelo.Planificacion;
import ec.edu.puce.professorCheck.modelo.SocioNegocio;
import ec.edu.puce.professorCheck.servicio.ServicioRol;
import ec.edu.puce.professorCheck.servicio.ServicioUsuario;

@ManagedBean(name = "planificacionCtrl")
@ViewScoped
public class PlanificacionCtrl extends BaseCtrl {

	/**
	 * 	
	 */
	private static final long serialVersionUID = 1L;
	// TODO serializable de la clase: Usuario
	@EJB
	private ServicioUsuario usuarioServicio;
	@EJB
	private ServicioCrud servicioCrud;
	@EJB
	private ServicioRol rolServicio;
	private Planificacion planificacion;
	private Planificacion planificacionFiltro;
	private List<Planificacion> planificacionLista;
	private List<SocioNegocio> socioNegocioLista;
	private List<MaestroCentroCosto> maestroCentroCostoLista;
	private List<MaestroCentroCosto> maestroCentroCostoListaSeleccionada;
	private List<MaestroPais> paisLista;
	private List<MaestroProvincia> provinciaLista;
	private List<MaestroCiudad> ciudadLista;
	private List<MaestroCiudad> ciudadListaSeleccionada;
	private SocioNegocio socioNegocio;
	private String pais;
	private String provincia;
	private String ciudad;

	@PostConstruct
	public void postConstructor() {
		this.pais = "169";
		this.planificacionFiltro = new Planificacion();
	}

	public Planificacion getPlanificacion() {
		if (planificacion == null) {
			String planificacionId = getHttpServletRequestParam("idPlanificacion");
			if (planificacionId == null) {
				planificacion = new Planificacion();
				planificacion.setEstado(EnumEstado.ACT);
			} else {
				planificacion = servicioCrud.findById(planificacionId,
						Planificacion.class);
			}
		}
		return planificacion;
	}

	public void setPlanificacion(Planificacion planificacion) {
		this.planificacion = planificacion;
	}

	public String guardar() {

		try {
			Planificacion planiEnBase = servicioCrud.findById(
					this.planificacion.getId(), Planificacion.class);
			if (planiEnBase == null) {
				servicioCrud.insert(planificacion);
			} else {
				servicioCrud.update(planificacion);
			}
			String m = getBundleMensajes("registro.guardado.correctamente",
					null);
			addInfoMessage(m, m);

		} catch (Exception e) {
			// e.printStackTrace();
			String m = getBundleMensajes("registro.noguardado.exception",
					new Object[] { e.getMessage() });
			addErrorMessage(m, m, null);
			return null;
		}

		return "/paginas/planificacion/planificacionLista";
	}

	public List<Planificacion> getPlanificacionLista() {
		if (this.planificacionLista == null) {
			Planificacion filtro = new Planificacion();
			planificacionLista = this.servicioCrud.findOrder(filtro);
		}
		return planificacionLista;
	}

	public void eliminarPlanificacion() {
		try {
			Planificacion planificacionData = (Planificacion) getExternalContext()
					.getRequestMap().get("item");
			servicioCrud.remove(planificacionData.getId(), Planificacion.class);
			addInfoMessage(
					getBundleMensajes("mensaje.informacion.elimina.exito", null),
					"");
			this.planificacionLista = null;
		} catch (Exception e) {
			addErrorMessage(null, e.getMessage(), "");
		}
	}

	public void setPlanificacionLista(List<Planificacion> planificacionLista) {
		this.planificacionLista = planificacionLista;
	}

	public void buscar() {
		this.planificacionLista = null;
	}

	public String editar() {
		Planificacion planificacionData = (Planificacion) getExternalContext()
				.getRequestMap().get("item");
		return "/paginas/planificacion/planificacion?faces-redirect=true&idMateria="
				+ planificacionData.getId();
	}

	public void nuevoEmpleado() {
		this.socioNegocio = new SocioNegocio();
	}

	public void seleccionarEmpleado() {
		this.socioNegocio = (SocioNegocio) getExternalContext().getRequestMap()
				.get("item");
	}

	public List<SocioNegocio> getSocioNegocioLista() {
		if (this.socioNegocioLista == null) {
			SocioNegocio filtro = new SocioNegocio();
			socioNegocioLista = this.servicioCrud.findOrder(filtro);
		}
		return socioNegocioLista;
	}

	public void setSocioNegocioLista(List<SocioNegocio> socioNegocioLista) {
		this.socioNegocioLista = socioNegocioLista;
	}

	public List<MaestroCentroCosto> getMaestroCentroCostoLista() {
		if (this.maestroCentroCostoLista == null) {
			MaestroCentroCosto filtro = new MaestroCentroCosto();
			maestroCentroCostoLista = this.servicioCrud.findOrder(filtro);
		}
		return maestroCentroCostoLista;
	}

	public void setMaestroCentroCostoLista(
			List<MaestroCentroCosto> maestroCentroCostoLista) {
		this.maestroCentroCostoLista = maestroCentroCostoLista;
	}

	public List<MaestroCiudad> getCiudadListaSeleccionada() {
		if (this.ciudadListaSeleccionada == null) {
			ciudadListaSeleccionada = new ArrayList<MaestroCiudad>();
		}
		return ciudadListaSeleccionada;
	}

	public void setCiudadListaSeleccionada(
			List<MaestroCiudad> ciudadListaSeleccionada) {

		this.ciudadListaSeleccionada = ciudadListaSeleccionada;
	}

	public void agregarCiudad() {
		MaestroCiudad filtro = new MaestroCiudad();
		if (pais != null && !pais.isEmpty() && provincia != null
				&& !provincia.isEmpty() && ciudad != null && !ciudad.isEmpty()) {
			filtro.setCodigoPais(pais);
			filtro.setCodigoProvincia(provincia);
			filtro.setCodigoCiudad(ciudad);
			ciudadLista = this.servicioCrud.findOrder(filtro);
			if (ciudadLista != null && !ciudadLista.isEmpty()) {
				this.ciudadListaSeleccionada.add(ciudadLista.get(0));
			}
		}
		addInfoMessage("Ciudad Agregada con éxito", "");
		this.provincia = null;
		this.ciudad = null;
	}

	public void eliminarCiudad() {
		try {
			MaestroCiudad ciudadData = (MaestroCiudad) getExternalContext()
					.getRequestMap().get("item");
			ciudadListaSeleccionada.remove(ciudadData);

			addInfoMessage(
					getBundleMensajes("mensaje.informacion.elimina.exito", null),
					"");
		} catch (Exception e) {
			addErrorMessage(null, e.getMessage(), "");
		}
	}

	public void eliminarCentroCostos() {
		try {
			MaestroCentroCosto maestroCentroData = (MaestroCentroCosto) getExternalContext()
					.getRequestMap().get("item");
			maestroCentroCostoListaSeleccionada.remove(maestroCentroData);

			addInfoMessage(
					getBundleMensajes("mensaje.informacion.elimina.exito", null),
					"");
		} catch (Exception e) {
			addErrorMessage(null, e.getMessage(), "");
		}
	}

	public List<MaestroPais> getPaisLista() {
		if (this.paisLista == null) {
			MaestroPais filtro = new MaestroPais();
			paisLista = this.servicioCrud.findOrder(filtro);
		}
		return paisLista;
	}

	public void setPaisLista(List<MaestroPais> paisLista) {
		this.paisLista = paisLista;
	}

	public void cambiaPais() {
		this.provinciaLista = null;
		this.ciudadLista = null;
	}

	public List<MaestroProvincia> getProvinciaLista() {
		if (this.provinciaLista == null) {
			MaestroProvincia filtro = new MaestroProvincia();
			if (pais != null && !pais.isEmpty()) {
				filtro.setCodigoPais(pais);
				provinciaLista = this.servicioCrud.findOrder(filtro);
			}

		}
		return provinciaLista;
	}

	public void setProvinciaLista(List<MaestroProvincia> provinciaLista) {
		this.provinciaLista = provinciaLista;
	}

	public List<MaestroCiudad> getCiudadLista() {
		if (this.ciudadLista == null) {
			MaestroCiudad filtro = new MaestroCiudad();
			if (pais != null && !pais.isEmpty() && provincia != null
					&& !provincia.isEmpty()) {
				filtro.setCodigoPais(pais);
				filtro.setCodigoProvincia(provincia);
				ciudadLista = this.servicioCrud.findOrder(filtro);
			}

		}
		return ciudadLista;
	}

	public void cambiaProvincia() {
		this.ciudadLista = null;
	}

	public List<MaestroCentroCosto> getMaestroCentroCostoListaSeleccionada() {
		if (maestroCentroCostoListaSeleccionada == null) {
			maestroCentroCostoListaSeleccionada = new ArrayList<MaestroCentroCosto>();
		}
		return maestroCentroCostoListaSeleccionada;
	}

	public void setMaestroCentroCostoListaSeleccionada(
			List<MaestroCentroCosto> maestroCentroCostoListaSeleccionada) {
		this.maestroCentroCostoListaSeleccionada = maestroCentroCostoListaSeleccionada;
	}

	public void agregarCentroCostos() {
		try {
			MaestroCentroCosto maestroCentroCostosData = (MaestroCentroCosto) getExternalContext()
					.getRequestMap().get("item");
			maestroCentroCostoListaSeleccionada.add(maestroCentroCostosData);

			addInfoMessage("Centro de Costos agregado con éxito", "");
		} catch (Exception e) {
			addErrorMessage(null, e.getMessage(), "");
		}
	}

	public void onDateSelect(SelectEvent event) {
	}

	public void setCiudadLista(List<MaestroCiudad> ciudadLista) {
		this.ciudadLista = ciudadLista;
	}

	public Planificacion getPlanificacionFiltro() {
		return planificacionFiltro;
	}

	public void setPlanificacionFiltro(Planificacion planificacionFiltro) {
		this.planificacionFiltro = planificacionFiltro;
	}

	public SocioNegocio getSocioNegocio() {
		return socioNegocio;
	}

	public void setSocioNegocio(SocioNegocio socioNegocio) {
		this.socioNegocio = socioNegocio;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

}

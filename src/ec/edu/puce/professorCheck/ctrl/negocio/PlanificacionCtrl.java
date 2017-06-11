package ec.edu.puce.professorCheck.ctrl.negocio;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ec.edu.puce.professorCheck.constantes.EnumEstado;
import ec.edu.puce.professorCheck.crud.ServicioCrud;
import ec.edu.puce.professorCheck.ctrl.BaseCtrl;
import ec.edu.puce.professorCheck.modelo.MaestroCentroCosto;
import ec.edu.puce.professorCheck.modelo.MaestroCiudad;
import ec.edu.puce.professorCheck.modelo.MaestroPais;
import ec.edu.puce.professorCheck.modelo.MaestroProvincia;
import ec.edu.puce.professorCheck.modelo.Materia;
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
	private List<MaestroPais> paisLista;
	private List<MaestroProvincia> provinciaLista;
	private List<MaestroCiudad> ciudadLista;

	@PostConstruct
	public void postConstructor() {
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

	public List<MaestroProvincia> getProvinciaLista() {
		return provinciaLista;
	}

	public void setProvinciaLista(List<MaestroProvincia> provinciaLista) {
		this.provinciaLista = provinciaLista;
	}

	public List<MaestroCiudad> getCiudadLista() {
		return ciudadLista;
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
}

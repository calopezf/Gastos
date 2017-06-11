package ec.edu.puce.professorCheck.ctrl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ec.edu.puce.professorCheck.constantes.EnumEstado;
import ec.edu.puce.professorCheck.crud.ServicioCrud;
import ec.edu.puce.professorCheck.modelo.ParametroMaestroProceso;
import ec.edu.puce.professorCheck.servicio.ServicioRol;
import ec.edu.puce.professorCheck.servicio.ServicioUsuario;

@ManagedBean(name = "parametroMaestroProcesoCtrl")
@ViewScoped
public class ParametroMaestroProcesoCtrl extends BaseCtrl {

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
	private ParametroMaestroProceso parametro;
	private ParametroMaestroProceso parametroFiltro;
	private List<ParametroMaestroProceso> parametros;

	@PostConstruct
	public void postConstructor() {
		this.parametroFiltro = new ParametroMaestroProceso();
	}

	public ParametroMaestroProceso getParametro() {
		if (parametro == null) {
			String parametroId = getHttpServletRequestParam("idParametro");
			if (parametroId == null) {
				parametro = new ParametroMaestroProceso();
				parametro.setEstado(EnumEstado.ACT);
			} else {
				parametro = servicioCrud.findById(parametroId,
						ParametroMaestroProceso.class);
			}
		}
		return parametro;
	}

	public void setParametro(ParametroMaestroProceso parametro) {
		this.parametro = parametro;
	}

	public void eliminarParametro() {
		try {
			ParametroMaestroProceso parametroData = (ParametroMaestroProceso) getExternalContext()
					.getRequestMap().get("item");
			servicioCrud.remove(parametroData.getCodigo(),
					ParametroMaestroProceso.class);
			addInfoMessage(
					getBundleMensajes("mensaje.informacion.elimina.exito", null),
					"");
			this.parametros = null;
		} catch (Exception e) {
			addErrorMessage(null, e.getMessage(), "");
		}
	}

	public String guardar() {

		try {
			ParametroMaestroProceso paramtroEnBase = servicioCrud.findById(
					this.parametro.getCodigo(), ParametroMaestroProceso.class);
			if (paramtroEnBase == null) {
				servicioCrud.insert(parametro);
			} else {
				servicioCrud.update(parametro);
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

		return "/paginas/parametrosMaestroProceso/parametroLista";
	}

	public String editar() {
		ParametroMaestroProceso parametroData = (ParametroMaestroProceso) getExternalContext()
				.getRequestMap().get("item");
		return "/paginas/parametrosMaestroProceso/parametro?faces-redirect=true&idParametro="
				+ parametroData.getCodigo();
	}

	public void buscar() {
		this.parametros = null;
	}

	public ParametroMaestroProceso getParametroFiltro() {
		return parametroFiltro;
	}

	public void setParametroFiltro(ParametroMaestroProceso parametroFiltro) {
		this.parametroFiltro = parametroFiltro;
	}

	public List<ParametroMaestroProceso> getParametros() {
		if (this.parametros == null) {
			parametros = this.servicioCrud.findOrder(this.parametroFiltro);
		}
		return parametros;
	}

	public void setParametros(List<ParametroMaestroProceso> parametros) {
		this.parametros = parametros;
	}

}

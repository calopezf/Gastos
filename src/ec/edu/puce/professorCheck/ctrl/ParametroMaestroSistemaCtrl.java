package ec.edu.puce.professorCheck.ctrl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ec.edu.puce.professorCheck.constantes.EnumEstado;
import ec.edu.puce.professorCheck.crud.ServicioCrud;
import ec.edu.puce.professorCheck.modelo.ParametroMaestroSistema;
import ec.edu.puce.professorCheck.servicio.ServicioRol;
import ec.edu.puce.professorCheck.servicio.ServicioUsuario;

@ManagedBean(name = "parametroMaestroSistemaCtrl")
@ViewScoped
public class ParametroMaestroSistemaCtrl extends BaseCtrl {

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
	private ParametroMaestroSistema parametro;
	private ParametroMaestroSistema parametroFiltro;
	private List<ParametroMaestroSistema> parametros;

	@PostConstruct
	public void postConstructor() {
		this.parametroFiltro = new ParametroMaestroSistema();
	}

	public ParametroMaestroSistema getParametro() {
		if (parametro == null) {
			String parametroId = getHttpServletRequestParam("idParametro");
			if (parametroId == null) {
				parametro = new ParametroMaestroSistema();
				parametro.setEstado(EnumEstado.ACT);
			} else {
				parametro = servicioCrud.findById(parametroId, ParametroMaestroSistema.class);
			}
		}
		return parametro;
	}

	public void setParametro(ParametroMaestroSistema parametro) {
		this.parametro = parametro;
	}

	public void eliminarParametro() {
		try {
			ParametroMaestroSistema parametroData = (ParametroMaestroSistema) getExternalContext()
					.getRequestMap().get("item");
			servicioCrud.remove(parametroData.getCodigo(), ParametroMaestroSistema.class);
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
			ParametroMaestroSistema paramtroEnBase = servicioCrud.findById(
					this.parametro.getCodigo(), ParametroMaestroSistema.class);
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

		return "/paginas/parametrosMaestroSistema/parametroLista";
	}

	public String editar() {
		ParametroMaestroSistema parametroData = (ParametroMaestroSistema) getExternalContext()
				.getRequestMap().get("item");
		return "/paginas/parametrosMaestroSistema/parametro?faces-redirect=true&idParametro="
				+ parametroData.getCodigo();
	}

	public void buscar() {
		this.parametros = null;
	}

	public ParametroMaestroSistema getParametroFiltro() {
		return parametroFiltro;
	}

	public void setParametroFiltro(ParametroMaestroSistema parametroFiltro) {
		this.parametroFiltro = parametroFiltro;
	}

	public List<ParametroMaestroSistema> getParametros() {
		if (this.parametros == null) {
			parametros = this.servicioCrud.findOrder(this.parametroFiltro);
		}
		return parametros;
	}

	public void setParametros(List<ParametroMaestroSistema> parametros) {
		this.parametros = parametros;
	}

}

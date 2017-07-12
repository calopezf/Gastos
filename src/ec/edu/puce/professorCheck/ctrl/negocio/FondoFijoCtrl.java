package ec.edu.puce.professorCheck.ctrl.negocio;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import ec.edu.puce.professorCheck.constantes.EnumEstado;
import ec.edu.puce.professorCheck.crud.ServicioCrud;
import ec.edu.puce.professorCheck.ctrl.BaseCtrl;
import ec.edu.puce.professorCheck.modelo.FondoFijo;
import ec.edu.puce.professorCheck.modelo.SeguimientoSyllabus;
import ec.edu.puce.professorCheck.modelo.SocioNegocio;
import ec.edu.puce.professorCheck.servicio.ServicioRol;
import ec.edu.puce.professorCheck.servicio.ServicioUsuario;

@ManagedBean(name = "fondoFijoCtrl")
@ViewScoped
public class FondoFijoCtrl extends BaseCtrl {

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
	private FondoFijo fondoFijo;
	private FondoFijo fondoFijoFiltro;
	private List<FondoFijo> fondosFijos;
	private SocioNegocio socioNegocio;
	private List<SocioNegocio> socioNegocioLista;

	@PostConstruct
	public void postConstructor() {
		this.fondoFijoFiltro = new FondoFijo();
	}

	public FondoFijo getFondoFijo() {
		if (fondoFijo == null) {
			String fondoId = getHttpServletRequestParam("idFondoFijo");
			if (fondoId == null) {
				fondoFijo = new FondoFijo();
				fondoFijo.setEstado(EnumEstado.ACT);
			} else {
				fondoFijo = servicioCrud.findById(
						Long.parseLong(fondoId),
						FondoFijo.class);
				SocioNegocio socioFiltro=new SocioNegocio();
				socioFiltro.setCodigo(fondoFijo.getCodigoSocio());
				List<SocioNegocio>socios=servicioCrud.findOrder(socioFiltro);
				if(socios!=null && !socios.isEmpty()){
					socioNegocio=socios.get(0);
				}
			}
		}
		return fondoFijo;
	}

	public void setFondoFijo(FondoFijo fondoFijo) {
		this.fondoFijo = fondoFijo;
	}

	public void eliminarFondoFijo() {
		try {
			FondoFijo fondoFijoData = (FondoFijo) getExternalContext()
					.getRequestMap().get("item");
			servicioCrud.remove(fondoFijoData.getId(), FondoFijo.class);
			addInfoMessage(
					getBundleMensajes("mensaje.informacion.elimina.exito", null),
					"");
			this.fondosFijos = null;
		} catch (Exception e) {
			addErrorMessage(null, e.getMessage(), "");
		}
	}

	public String guardar() {

		try {
			if (this.fondoFijo.getId() == null) {
				this.fondoFijo.setCodigoSocio(this.socioNegocio.getCodigo());
				servicioCrud.insert(fondoFijo);
			} else {
				this.fondoFijo.setCodigoSocio(this.socioNegocio.getCodigo());
				servicioCrud.update(fondoFijo);
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

		return "/paginas/fondoFijo/fondoFijoLista";
	}

	public String editar() {
		FondoFijo fondoFijoData = (FondoFijo) getExternalContext()
				.getRequestMap().get("item");
		return "/paginas/fondoFijo/fondoFijo?faces-redirect=true&idFondoFijo="
				+ fondoFijoData.getId();
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

	public void buscar() {
		this.fondosFijos = null;
	}

	public FondoFijo getFondoFijoFiltro() {
		return fondoFijoFiltro;
	}

	public void setFondoFijoFiltro(FondoFijo fondoFijoFiltro) {
		this.fondoFijoFiltro = fondoFijoFiltro;
	}

	public List<FondoFijo> getFondosFijos() {
		if (this.fondosFijos == null) {
			fondosFijos = this.servicioCrud.findOrder(this.fondoFijoFiltro);
		}
		return fondosFijos;
	}


	public void onDateSelect(SelectEvent event) {
	}
	public void setFondosFijos(List<FondoFijo> fondosFijos) {
		this.fondosFijos = fondosFijos;
	}

	public void nuevoEmpleado() {
		this.socioNegocio = new SocioNegocio();
	}

	public void seleccionarEmpleado() {
		this.socioNegocio = (SocioNegocio) getExternalContext().getRequestMap()
				.get("item");
	}

	public SocioNegocio getSocioNegocio() {
		return socioNegocio;
	}

	public void setSocioNegocio(SocioNegocio socioNegocio) {
		this.socioNegocio = socioNegocio;
	}

}

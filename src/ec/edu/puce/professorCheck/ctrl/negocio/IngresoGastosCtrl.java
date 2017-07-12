package ec.edu.puce.professorCheck.ctrl.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import ec.edu.puce.professorCheck.constantes.EnumEstado;
import ec.edu.puce.professorCheck.crud.ServicioCrud;
import ec.edu.puce.professorCheck.ctrl.BaseCtrl;
import ec.edu.puce.professorCheck.modelo.GastoCabecera;
import ec.edu.puce.professorCheck.modelo.GastoDetalle;
import ec.edu.puce.professorCheck.modelo.MaestroCentroCosto;
import ec.edu.puce.professorCheck.modelo.MaestroCiudad;
import ec.edu.puce.professorCheck.modelo.MaestroPais;
import ec.edu.puce.professorCheck.modelo.MaestroProvincia;
import ec.edu.puce.professorCheck.modelo.Planificacion;
import ec.edu.puce.professorCheck.modelo.PlanificacionMarca;
import ec.edu.puce.professorCheck.modelo.PlanificacionRuta;
import ec.edu.puce.professorCheck.modelo.SocioNegocio;
import ec.edu.puce.professorCheck.servicio.ServicioRol;
import ec.edu.puce.professorCheck.servicio.ServicioUsuario;

@ManagedBean(name = "ingresoGastosCtrl")
@ViewScoped
public class IngresoGastosCtrl extends BaseCtrl {

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
	private PlanificacionRuta rutaData;

	private GastoCabecera gastoCabecera;

	private List<GastoDetalle> listaGastos;

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful",
					file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

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
				planificacion = servicioCrud.findById(
						Long.parseLong(planificacionId), Planificacion.class);
				PlanificacionRuta planificacionRutaFiltro = new PlanificacionRuta();
				planificacionRutaFiltro.setIdPlanificacion(planificacion
						.getId());

				List<PlanificacionRuta> rutasLista = servicioCrud
						.findOrder(planificacionRutaFiltro);

				planificacion.setRutas(rutasLista);
				for (PlanificacionRuta ru : planificacion.getRutas()) {
					PlanificacionMarca planificacionMarcaFiltro = new PlanificacionMarca();
					planificacionMarcaFiltro.setIdPlanificacion(planificacion
							.getId());
					planificacionMarcaFiltro.setIdRuta(ru.getId());
					List<PlanificacionMarca> marcasLista = servicioCrud
							.findOrder(planificacionMarcaFiltro);
					ru.setMarcas(marcasLista);
				}

				SocioNegocio socioFiltro = new SocioNegocio();
				socioFiltro.setCodigo(planificacion.getCodigoSocio());
				List<SocioNegocio> socios = servicioCrud.findOrder(socioFiltro);
				if (socios != null && !socios.isEmpty()) {
					socioNegocio = socios.get(0);
				}
			}
		}
		return planificacion;
	}

	public void setPlanificacion(Planificacion planificacion) {
		this.planificacion = planificacion;
	}

	public String guardar() {

		try {
			if (this.planificacion.getId() == null) {
				this.planificacion
						.setCodigoSocio(this.socioNegocio.getCodigo());
				List<PlanificacionRuta> rutasPersist = planificacion.getRutas();
				planificacion.setRutas(null);
				this.planificacion = servicioCrud.insertReturn(planificacion);
				for (PlanificacionRuta r : rutasPersist) {
					r.setPlanificacion(planificacion);
					r.setIdPlanificacion(planificacion.getId());
					List<PlanificacionMarca> marcasPersist = r.getMarcas();
					r.setMarcas(null);
					r = servicioCrud.insertReturn(r);
					for (PlanificacionMarca m : marcasPersist) {
						m.setPlanificacion(planificacion);
						m.setIdPlanificacion(planificacion.getId());
						m.setRuta(r);
						m.setIdRuta(r.getId());
						servicioCrud.insertReturn(m);
					}
				}
			} else {
				this.planificacion
						.setCodigoSocio(this.socioNegocio.getCodigo());
				List<PlanificacionRuta> rutasPersist = planificacion.getRutas();
				planificacion.setRutas(null);
				this.planificacion = servicioCrud.update(planificacion);
				for (PlanificacionRuta r : rutasPersist) {
					r.setPlanificacion(planificacion);
					r.setIdPlanificacion(planificacion.getId());
					List<PlanificacionMarca> marcasPersist = r.getMarcas();
					r.setMarcas(null);
					r = servicioCrud.update(r);
					for (PlanificacionMarca m : marcasPersist) {
						m.setPlanificacion(planificacion);
						m.setRuta(r);
						m.setIdRuta(r.getId());
						servicioCrud.update(m);
					}
				}
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
		return "/paginas/planificacion/planificacion?faces-redirect=true&idPlanificacion="
				+ planificacionData.getId();
	}

	public String ingresarGasto() {
		Planificacion planificacionData = (Planificacion) getExternalContext()
				.getRequestMap().get("item");
		return "/paginas/planificacion/ingresoGasto?faces-redirect=true&idPlanificacion="
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
				PlanificacionRuta ruta = new PlanificacionRuta();
				ruta.setCodigoCiudad(ciudadLista.get(0).getCodigoCiudad());
				ruta.setCodigoPais(ciudadLista.get(0).getCodigoPais());
				ruta.setCodigoProvincia(ciudadLista.get(0).getCodigoProvincia());
				ruta.setNombreCiudad(ciudadLista.get(0).getNombreCiudad());
				ruta.setPlanificacion(this.planificacion);
				this.planificacion.getRutas().add(ruta);
			}
		}
		addInfoMessage("Ciudad Agregada con éxito", "");
		this.provincia = null;
		this.ciudad = null;
	}

	public void eliminarCiudad() {
		try {
			PlanificacionRuta rutaPlanificacionData = (PlanificacionRuta) getExternalContext()
					.getRequestMap().get("item");
			this.planificacion.getRutas().remove(rutaPlanificacionData);

			addInfoMessage(
					getBundleMensajes("mensaje.informacion.elimina.exito", null),
					"");
		} catch (Exception e) {
			addErrorMessage(null, e.getMessage(), "");
		}
	}

	public void eliminarCentroCostos() {
		try {
			PlanificacionRuta rutaPlanificacionData = (PlanificacionRuta) getExternalContext()
					.getRequestMap().get("item");
			PlanificacionMarca rutaPlanificacionMarca = (PlanificacionMarca) getExternalContext()
					.getRequestMap().get("item2");
			rutaPlanificacionData.getMarcas().remove(rutaPlanificacionMarca);

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

	public void previoAgregaMarca() {
		rutaData = (PlanificacionRuta) getExternalContext().getRequestMap()
				.get("item");
	}

	public void agregarCentroCostos() {
		try {
			MaestroCentroCosto maestroCentroCostosData = (MaestroCentroCosto) getExternalContext()
					.getRequestMap().get("item");
			PlanificacionMarca marca = new PlanificacionMarca();
			marca.setCodigoCentroCostos(maestroCentroCostosData
					.getCodigoCentroCostos());
			marca.setCodigoCompania(maestroCentroCostosData.getCodigoCompania());
			marca.setRuta(rutaData);
			marca.setPlanificacion(planificacion);
			marca.setNombreCentroCostos(maestroCentroCostosData
					.getNombreCentroCostos());
			rutaData.getMarcas().add(marca);
			int i = rutaData.getMarcas().size();
			double porcentaje = 100 / i;
			for (PlanificacionMarca marc : rutaData.getMarcas()) {
				marc.setPorcentaje(0.0);
				marc.setPorcentaje(porcentaje);
			}

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

	public PlanificacionRuta getRutaData() {
		if (rutaData == null) {
			rutaData = new PlanificacionRuta();
		}
		return rutaData;
	}

	public void setRutaData(PlanificacionRuta rutaData) {
		this.rutaData = rutaData;
	}

	public List<GastoDetalle> getListaGastos() {

		if (this.listaGastos == null) {
			GastoDetalle filtro = new GastoDetalle();
			listaGastos = this.servicioCrud.findOrder(filtro);
		}

		return listaGastos;
	}

	public void setListaGastos(List<GastoDetalle> listaGastos) {
		this.listaGastos = listaGastos;
	}

	public void agregarGasto() {
		MaestroCiudad filtro = new MaestroCiudad();
		if (pais != null && !pais.isEmpty() && provincia != null
				&& !provincia.isEmpty() && ciudad != null && !ciudad.isEmpty()) {
			filtro.setCodigoPais(pais);
			filtro.setCodigoProvincia(provincia);
			filtro.setCodigoCiudad(ciudad);
			ciudadLista = this.servicioCrud.findOrder(filtro);
			if (ciudadLista != null && !ciudadLista.isEmpty()) {
				PlanificacionRuta ruta = new PlanificacionRuta();
				ruta.setCodigoCiudad(ciudadLista.get(0).getCodigoCiudad());
				ruta.setCodigoPais(ciudadLista.get(0).getCodigoPais());
				ruta.setCodigoProvincia(ciudadLista.get(0).getCodigoProvincia());
				ruta.setNombreCiudad(ciudadLista.get(0).getNombreCiudad());
				ruta.setPlanificacion(this.planificacion);
				this.planificacion.getRutas().add(ruta);
			}
		}
		addInfoMessage("Ciudad Agregada con éxito", "");
		this.provincia = null;
		this.ciudad = null;
	}

}

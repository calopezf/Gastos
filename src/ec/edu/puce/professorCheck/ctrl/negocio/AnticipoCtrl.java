package ec.edu.puce.professorCheck.ctrl.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import ec.edu.puce.professorCheck.constantes.EnumEstado;
import ec.edu.puce.professorCheck.crud.ServicioCrud;
import ec.edu.puce.professorCheck.ctrl.BaseCtrl;
import ec.edu.puce.professorCheck.modelo.Anticipo;
import ec.edu.puce.professorCheck.modelo.AnticipoMarca;
import ec.edu.puce.professorCheck.modelo.AnticipoRuta;
import ec.edu.puce.professorCheck.modelo.MaestroCentroCosto;
import ec.edu.puce.professorCheck.modelo.MaestroCiudad;
import ec.edu.puce.professorCheck.modelo.MaestroPais;
import ec.edu.puce.professorCheck.modelo.MaestroProvincia;
import ec.edu.puce.professorCheck.modelo.SocioNegocio;
import ec.edu.puce.professorCheck.servicio.ServicioRol;
import ec.edu.puce.professorCheck.servicio.ServicioUsuario;

@ManagedBean(name = "anticipoCtrl")
@ViewScoped
public class AnticipoCtrl extends BaseCtrl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4285211335844445048L;
	// TODO serializable de la clase: Usuario
	@EJB
	private ServicioUsuario usuarioServicio;
	@EJB
	private ServicioCrud servicioCrud;
	@EJB
	private ServicioRol rolServicio;
	private Anticipo anticipo;
	private Anticipo anticipoFiltro;
	private List<Anticipo> anticipoLista;
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
	private AnticipoRuta rutaData;

	@PostConstruct
	public void postConstructor() {
		this.pais = "169";
		this.anticipoFiltro = new Anticipo();
	}

	public Anticipo getAnticipo() {
		if (anticipo == null) {
			String anticipoId = getHttpServletRequestParam("idAnticipo");
			if (anticipoId == null) {
				anticipo = new Anticipo();
				anticipo.setEstado(EnumEstado.ACT);
			} else {
				anticipo = servicioCrud.findById(
						Long.parseLong(anticipoId), Anticipo.class);
				AnticipoRuta anticipoRutaFiltro = new AnticipoRuta();
				anticipoRutaFiltro.setIdAnticipo(anticipo
						.getId());

				List<AnticipoRuta> rutasLista = servicioCrud
						.findOrder(anticipoRutaFiltro);

				anticipo.setRutas(rutasLista);
				for (AnticipoRuta ru : anticipo.getRutas()) {
					AnticipoMarca anticipoMarcaFiltro = new AnticipoMarca();
					anticipoMarcaFiltro.setIdAnticipo(anticipo
							.getId());
					anticipoMarcaFiltro.setIdRuta(ru.getId());
					List<AnticipoMarca> marcasLista = servicioCrud
							.findOrder(anticipoMarcaFiltro);
					ru.setMarcas(marcasLista);
				}
				
				SocioNegocio socioFiltro=new SocioNegocio();
				socioFiltro.setCodigo(anticipo.getCodigoSocio());
				List<SocioNegocio>socios=servicioCrud.findOrder(socioFiltro);
				if(socios!=null && !socios.isEmpty()){
					socioNegocio=socios.get(0);
				}
			}
		}
		return anticipo;
	}

	public void setAnticipo(Anticipo anticipo) {
		this.anticipo = anticipo;
	}

	public String guardar() {

		try {
			if (this.anticipo.getId() == null) {
				//this.anticipo.setCodigoSocio(this.socioNegocio.getCodigo());
				List<AnticipoRuta> rutasPersist = anticipo.getRutas();
				anticipo.setRutas(null);
				this.anticipo = servicioCrud.insertReturn(anticipo);
				for (AnticipoRuta r : rutasPersist) {
					r.setAnticipo(anticipo);
					r.setIdAnticipo(anticipo.getId());
					List<AnticipoMarca> marcasPersist = r.getMarcas();
					r.setMarcas(null);
					r = servicioCrud.insertReturn(r);
					for (AnticipoMarca m : marcasPersist) {
						m.setAnticipo(anticipo);
						m.setIdAnticipo(anticipo.getId());
						m.setRuta(r);
						m.setIdRuta(r.getId());
						servicioCrud.insertReturn(m);
					}
				}
			} else {
				//this.anticipo.setCodigoSocio(this.socioNegocio.getCodigo());
				List<AnticipoRuta> rutasPersist = anticipo.getRutas();
				anticipo.setRutas(null);
				this.anticipo = servicioCrud.update(anticipo);
				for (AnticipoRuta r : rutasPersist) {
					r.setAnticipo(anticipo);
					r.setIdAnticipo(anticipo.getId());
					List<AnticipoMarca> marcasPersist = r.getMarcas();
					r.setMarcas(null);
					r = servicioCrud.update(r);
					for (AnticipoMarca m : marcasPersist) {
						m.setAnticipo(anticipo);
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

		return "/paginas/anticipo/anticipoLista";
	}

	public List<Anticipo> getAnticipoLista() {
		if (this.anticipoLista == null) {
			Anticipo filtro = new Anticipo();
			anticipoLista = this.servicioCrud.findOrder(filtro);
		}
		return anticipoLista;
	}

	public void eliminarAnticipo() {
		try {
			Anticipo anticipoData = (Anticipo) getExternalContext()
					.getRequestMap().get("item");
			servicioCrud.remove(anticipoData.getId(), Anticipo.class);
			addInfoMessage(
					getBundleMensajes("mensaje.informacion.elimina.exito", null),
					"");
			this.anticipoLista = null;
		} catch (Exception e) {
			addErrorMessage(null, e.getMessage(), "");
		}
	}

	public void setAnticipoLista(List<Anticipo> anticipoLista) {
		this.anticipoLista = anticipoLista;
	}

	public void buscar() {
		this.anticipoLista = null;
	}

	public String editar() {
		Anticipo anticipoData = (Anticipo) getExternalContext()
				.getRequestMap().get("item");
		return "/paginas/anticipo/anticipo?faces-redirect=true&idAnticipo="
				+ anticipoData.getId();
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

	public void setMaestroCentroCostoLista(List<MaestroCentroCosto> maestroCentroCostoLista) {
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
				AnticipoRuta ruta = new AnticipoRuta();
				ruta.setCodigoCiudad(ciudadLista.get(0).getCodigoCiudad());
				ruta.setCodigoPais(ciudadLista.get(0).getCodigoPais());
				ruta.setCodigoProvincia(ciudadLista.get(0).getCodigoProvincia());
				ruta.setNombreCiudad(ciudadLista.get(0).getNombreCiudad());
				ruta.setAnticipo(this.anticipo);
				this.anticipo.getRutas().add(ruta);
			}
		}
		addInfoMessage("Ciudad Agregada con éxito", "");
		this.provincia = null;
		this.ciudad = null;
	}

	public void eliminarCiudad() {
		try {
			AnticipoRuta rutaAnticipoData = (AnticipoRuta) getExternalContext()
					.getRequestMap().get("item");
			this.anticipo.getRutas().remove(rutaAnticipoData);

			addInfoMessage(
					getBundleMensajes("mensaje.informacion.elimina.exito", null),
					"");
		} catch (Exception e) {
			addErrorMessage(null, e.getMessage(), "");
		}
	}

	public void eliminarCentroCostos() {
		try {
			AnticipoRuta rutaAnticipoData = (AnticipoRuta) getExternalContext()
					.getRequestMap().get("item");
			AnticipoMarca rutaAnticipoMarca = (AnticipoMarca) getExternalContext()
					.getRequestMap().get("item2");
			rutaAnticipoData.getMarcas().remove(rutaAnticipoMarca);

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
		rutaData = (AnticipoRuta) getExternalContext().getRequestMap()
				.get("item");
	}

	public void agregarCentroCostos(ActionEvent event) {
		try {
			MaestroCentroCosto maestroCentroCostosData = (MaestroCentroCosto) getExternalContext()
					.getRequestMap().get("item");
			AnticipoMarca marca = new AnticipoMarca();
			marca.setCodigoCentroCostos(maestroCentroCostosData
					.getCodigoCentroCostos());
			marca.setCodigoCompania(maestroCentroCostosData.getCodigoCompania());
			marca.setRuta(rutaData);
			marca.setAnticipo(anticipo);
			marca.setNombreCentroCostos(maestroCentroCostosData
					.getNombreCentroCostos());
			rutaData.getMarcas().add(marca);

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

	public Anticipo getAnticipoFiltro() {
		return anticipoFiltro;
	}

	public void setAnticipoFiltro(Anticipo anticipoFiltro) {
		this.anticipoFiltro = anticipoFiltro;
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

	public AnticipoRuta getRutaData() {
		if (rutaData == null) {
			rutaData = new AnticipoRuta();
		}
		return rutaData;
	}

	public void setRutaData(AnticipoRuta rutaData) {
		this.rutaData = rutaData;
	}

}

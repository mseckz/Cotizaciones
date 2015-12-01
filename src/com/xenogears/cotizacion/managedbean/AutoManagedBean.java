package com.xenogears.cotizacion.managedbean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.google.common.collect.Lists;
import com.xenogears.cotizacion.model.Auto;
import com.xenogears.cotizacion.model.ConfigVariable;
import com.xenogears.cotizacion.service.AutoService;
import com.xenogears.cotizacion.service.ConfigVariableService;
import com.xenogears.cotizacion.util.Constantes;
import com.xenogears.cotizacion.util.Util;

@ManagedBean
@SessionScoped
public class AutoManagedBean {
	
	private Auto auto;
	private List<Auto> autos;
	private List<ConfigVariable> tipoAuto;
	private List<ConfigVariable> tipoMarca;
	private List<ConfigVariable> tipoModelo;
	private Auto autoEliminar;
	private ArrayList<Integer> anioslist;
	private ArrayList<Integer> puertalist;
	private UploadedFile imagen;
	
	@ManagedProperty(value = "#{autoService}")
	AutoService autoServicio;

	@ManagedProperty(value = "#{configVariableService}")
	ConfigVariableService variableService;

	@PostConstruct
	public void init() {
		auto = new Auto();
		autos = new ArrayList<Auto>();
		tipoAuto = new ArrayList<ConfigVariable>();
		tipoMarca = new ArrayList<ConfigVariable>();
		tipoModelo = new ArrayList<ConfigVariable>();
		anioslist = new ArrayList<Integer>();
		puertalist = new ArrayList<Integer>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = 0; i < 21; i++) {
			anioslist.add(year - i);
		}
		puertalist.add(2);
		puertalist.add(4);

	}

	public String indexAuto() {
		auto = new Auto();
		return "/paginas/mantenimiento/auto/autoIndex.xhtml?faces-redirect=true";
	}

	public String grabar() throws IOException {

		if (auto.getIdAuto() == null) {
			String ultimoCodigo = autoServicio.getAutoRepository()
					.obtenerCodigo();
			Integer numeroCodigo = Integer.parseInt(ultimoCodigo.substring(4));
			String nuevoCodigo = "AU"
					+ String.format("%05d", (numeroCodigo + 1));
			System.out.println(nuevoCodigo);
			auto.setCodigoAuto(nuevoCodigo);

			ConfigVariable tipo = variableService.getConfigVarRepository()
					.findOne(auto.getIdTipoAuto());
			auto.setDescripcionTipo(tipo.getDescripcion());

			ConfigVariable modelo = variableService.getConfigVarRepository()
					.findOne(auto.getIdModelo());
			auto.setModelo(modelo.getDescripcion());

			ConfigVariable marca = variableService.getConfigVarRepository()
					.findOne(auto.getIdMarca());
			auto.setMarca(marca.getDescripcion());
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"", "Auto " + auto.getCodigoAuto()
							+ " registrado correctamente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			ConfigVariable tipo = variableService.getConfigVarRepository()
					.findOne(auto.getIdTipoAuto());
			auto.setDescripcionTipo(tipo.getDescripcion());

			ConfigVariable modelo = variableService.getConfigVarRepository()
					.findOne(auto.getIdModelo());
			auto.setModelo(modelo.getDescripcion());

			ConfigVariable marca = variableService.getConfigVarRepository()
					.findOne(auto.getIdMarca());
			auto.setMarca(marca.getDescripcion());
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"", "Auto " + auto.getCodigoAuto()
							+ " registrado correctamente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		autoServicio.getAutoRepository().save(auto);
		auto = new Auto();
		imagen = null;
		return null;
	}

	public void cargarAuto(Auto auto) {
		this.auto = auto;
	}

	public void cargarAutoEliminar(Auto auto) {
		this.autoEliminar = auto;
	}

	public void eliminarAuto() {
		autoEliminar.setFlagEstado(false);
		autoServicio.getAutoRepository().save(autoEliminar);
		autoEliminar = new Auto();
		RequestContext
				.getCurrentInstance()
				.execute(
						"{setTimeout(function() {PF('w_eliminarAutoDialog').hide();},1000);}");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
				"Auto " + "eliminado correctamente");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void limpiarForm() {
		auto = new Auto();
	}
	
	
	public void subirFoto(FileUploadEvent e) throws IOException{
		imagen = e.getFile();
		auto.setUrlFoto(Constantes.RUTA_FOTO_WEB + imagen.getFileName());
		Util.subirArchivo(imagen.getFileName(), imagen.getInputstream(),Constantes.RUTA_FOTO_FOTO_FISICA);
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public List<Auto> getAutos() {
		autos = Lists.newArrayList(autoServicio.getAutoRepository()
				.obtenerPorEstado(true));
		return autos;
	}

	public void setAutos(List<Auto> autos) {
		this.autos = autos;
	}

	public List<ConfigVariable> getTipoAuto() {
		tipoAuto = Lists.newArrayList(variableService.getConfigVarRepository()
				.obtenerPorid(Constantes.ID_TABLA_TIPO_AUTO));
		return tipoAuto;
	}

	public void setTipoAuto(List<ConfigVariable> tipoAuto) {
		this.tipoAuto = tipoAuto;
	}

	public List<ConfigVariable> getTipoMarca() {
		tipoMarca = Lists.newArrayList(variableService.getConfigVarRepository()
				.obtenerPorid(Constantes.ID_TABLA_MARCA));
		return tipoMarca;
	}

	public void setTipoMarca(List<ConfigVariable> tipoMarca) {
		this.tipoMarca = tipoMarca;
	}

	public List<ConfigVariable> getTipoModelo() {
		tipoModelo = Lists.newArrayList(variableService
				.getConfigVarRepository().obtenerPorid(
						Constantes.ID_TABLA_MODELO_AUTO));
		return tipoModelo;
	}

	public void setTipoModelo(List<ConfigVariable> tipoModelo) {
		this.tipoModelo = tipoModelo;
	}

	public AutoService getAutoServicio() {
		return autoServicio;
	}

	public void setAutoServicio(AutoService autoServicio) {
		this.autoServicio = autoServicio;
	}

	public ConfigVariableService getVariableService() {
		return variableService;
	}

	public void setVariableService(ConfigVariableService variableService) {
		this.variableService = variableService;
	}

	public Auto getAutoEliminar() {
		return autoEliminar;
	}

	public void setAutoEliminar(Auto autoEliminar) {
		this.autoEliminar = autoEliminar;
	}

	public ArrayList<Integer> getAnioslist() {
		return anioslist;
	}

	public void setAnioslist(ArrayList<Integer> anioslist) {
		this.anioslist = anioslist;
	}

	public ArrayList<Integer> getPuertalist() {
		return puertalist;
	}

	public void setPuertalist(ArrayList<Integer> puertalist) {
		this.puertalist = puertalist;
	}

	public UploadedFile getImagen() {
		return imagen;
	}

	public void setImagen(UploadedFile imagen) {
		this.imagen = imagen;
	}

}

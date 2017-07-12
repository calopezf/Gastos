package ec.edu.puce.professorCheck.constantes;

public enum EnumEstadoGasto {

	AP("aprobado"), RC("rechazado");

	private String etiqueta;

	EnumEstadoGasto(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

}
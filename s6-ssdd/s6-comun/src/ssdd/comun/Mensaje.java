package ssdd.comun;

import java.io.Serializable;

public class Mensaje implements Serializable {

	private static final long serialVersionUID = 643146247177534068L;
	private String emisor;
	private String texto;
	
	public String getEmisor() {
		return emisor;
	}
	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}

}

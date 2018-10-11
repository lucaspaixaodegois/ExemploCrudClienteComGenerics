package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Telefone extends DefaultEntity<Telefone> {
	
	private static final long serialVersionUID = -8839605705217664226L;
	
	private String codigoArea;
	private String numero;
	
	@ManyToOne
	private Cliente cliente;

	public String getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}

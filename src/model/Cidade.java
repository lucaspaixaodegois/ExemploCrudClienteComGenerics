package model;

import javax.persistence.Entity;

@Entity
public class Cidade extends DefaultEntity<Cidade> {
	
	private static final long serialVersionUID = -477803445713636937L;
	
	private String nome;
	private String estado;
	private String pais;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}

package model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends DefaultEntity<Cliente> {

	private static final long serialVersionUID = 6762636530489302919L;
	
	private String cpf;
	private String nome;
	private String endereco;
	private String email;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cliente")
	private List<Telefone> listaTelefone;
	
	@Column(columnDefinition="Date")
	private LocalDate dataAniversaio;
	
	public Cliente() {
		
	}

	public Cliente(String cpf, String nome, String endereco, String email, LocalDate dataAniversario) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
		this.dataAniversaio = dataAniversario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataAniversaio() {
		return dataAniversaio;
	}

	public void setDataAniversaio(LocalDate dataAniversaio) {
		this.dataAniversaio = dataAniversaio;
	}

	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}
	
}

package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Administrador {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String usuario;
	private String password;

	@OneToMany(cascade= CascadeType.ALL, mappedBy="administrador")
	private List<Noticia> noticiasList;

	public Administrador() {
		super();
	}

	public Administrador(int id, String usuario, String password, List<Noticia> noticiasList) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.noticiasList = noticiasList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Noticia> getNoticiasList() {
		return noticiasList;
	}

	public void setNoticiasList(List<Noticia> noticiasList) {
		this.noticiasList = noticiasList;
	}
	
	@Override
	public String toString() {
		return "Administrador [id=" + id + ", usuario=" + usuario + ", password=" + password + ", noticiasList="
				+ noticiasList + "]";
	}
}

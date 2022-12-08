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
	private int idAdministrador;
	private String usuario;
	private String password;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="administrador")
	private List<Noticia> noticiasList;
	
	public Administrador() {
		super();
	}
	public Administrador(int idAdministrador, String usuario, String password) {
		super();
		this.idAdministrador = idAdministrador;
		this.usuario = usuario;
		this.password = password;
	}
	public int getIdAdministrador() {
		return idAdministrador;
	}
	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
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
	@Override
	public String toString() {
		return "Administradores [idAdministrador=" + idAdministrador + ", usuario=" + usuario + ", password="
				+ password + "]";
	}
	
}

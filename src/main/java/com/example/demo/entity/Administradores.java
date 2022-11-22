package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administradores {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAdministradores;
	private String usuario;
	private String password;
	public Administradores() {
		super();
	}
	public Administradores(int idAdministradores, String usuario, String password) {
		super();
		this.idAdministradores = idAdministradores;
		this.usuario = usuario;
		this.password = password;
	}
	public int getIdAdministradores() {
		return idAdministradores;
	}
	public void setIdAdministradores(int idAdministradores) {
		this.idAdministradores = idAdministradores;
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
		return "Administradores [idAdministradores=" + idAdministradores + ", usuario=" + usuario + ", password="
				+ password + "]";
	}
	
}

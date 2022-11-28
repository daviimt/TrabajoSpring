package com.example.demo.models;

public class AdministradorModel {
	private int idAdministradores;
	private String usuario;
	private String password;
	public AdministradorModel() {
		super();
	}
	public AdministradorModel(int idAdministradores, String usuario, String password) {
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

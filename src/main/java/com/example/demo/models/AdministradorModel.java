package com.example.demo.models;

public class AdministradorModel {
	private int idAdministrador;
	private String usuario;
	private String password;
	public AdministradorModel() {
		super();
	}
	public AdministradorModel(int idAdministrador, String usuario, String password) {
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

package com.example.demo.models;

public class AdministradorModel {
	private int id;
	private String usuario;
	private String password;
	
	public AdministradorModel() {
		super();
	}
	public AdministradorModel(int id, String usuario, String password) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.password = password;
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
	
	@Override
	public String toString() {
		return "AdministradorModel [id=" + id + ", usuario=" + usuario + ", password=" + password + "]";
	}
	
	
}

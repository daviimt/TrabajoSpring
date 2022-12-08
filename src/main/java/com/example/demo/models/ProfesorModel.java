package com.example.demo.models;

public class ProfesorModel {
	private int idProfesor;
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	
	public ProfesorModel() {
		super();
	}
	public ProfesorModel(int id, String nombre, String apellidos, String email, String password) {
		super();
		this.idProfesor = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
	}
	public int getIdProfesor() {
		return idProfesor;
	}
	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "ProfesorModel [idProfesor=" + idProfesor + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", email=" + email + ", password=" + password + "]";
	}

	
}

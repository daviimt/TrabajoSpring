package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Profesores {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idProfesores;
	private String nombre;
	private String apellidos;
	private String email;
	private String usuario;
	private String password;
	
	public Profesores() {
		super();
	}
	public Profesores(int id, String nombre, String apellidos, String email, String usuario, String password) {
		super();
		this.idProfesores = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.usuario = usuario;
		this.password = password;
	}
	public int getId() {
		return idProfesores;
	}
	public void setId(int id) {
		this.idProfesores = id;
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
		return "Profesores [idProfesores=" + idProfesores + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", usuario=" + usuario + ", password=" + password + "]";
	}
	
	
}

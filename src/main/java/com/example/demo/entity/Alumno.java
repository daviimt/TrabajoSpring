package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Alumno {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAlumnos;
	private String nombre;
	private String apellidos;
	private String email;
	private String usuario;
	private String password;
	private String foto;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="alumno")
	private List<Matricula> matriculaList;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="alumno")
	private List<Comentario> comentarioList;
	
	public Alumno() {
		super();
	}
	public Alumno(int idAlumno, String nombre, String apellidos, String email, String usuario, String password,
			String foto) {
		super();
		this.idAlumnos = idAlumno;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.usuario = usuario;
		this.password = password;
		this.foto = foto;
	}
	public int getIdAlumno() {
		return idAlumnos;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumnos = idAlumno;
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "Alumnos [idAlumno=" + idAlumnos + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", usuario=" + usuario + ", password=" + password + ", foto=" + foto + "]";
	}
	
}

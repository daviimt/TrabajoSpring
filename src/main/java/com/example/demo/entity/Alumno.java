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
	private int idAlumno;
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="alumno")
	private List<Matricula> matriculaList;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="alumno")
	private List<Comentario> comentarioList;
	
	public Alumno() {
		super();
	}
	public Alumno(int idAlumno, String nombre, String apellidos, String email, String password) {
		super();
		this.idAlumno = idAlumno;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
	}
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
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
		return "Alumnos [idAlumno=" + idAlumno + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", password=" + password + "]";
	}
}

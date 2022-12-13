package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Alumno {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="alumno")
	private List<Matricula> matriculaList;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="alumno")
	private List<Comentario> comentarioList;
	
	@OneToOne
	@JoinColumn(name="idUser", referencedColumnName="id")
	private Usuario usuario;
	
	public Alumno() {
		super();
	}

	public Alumno(int id, String nombre, String apellidos, String email, String password, List<Matricula> matriculaList,
			List<Comentario> comentarioList) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.matriculaList = matriculaList;
		this.comentarioList = comentarioList;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Matricula> getMatriculaList() {
		return matriculaList;
	}

	public void setMatriculaList(List<Matricula> matriculaList) {
		this.matriculaList = matriculaList;
	}

	public List<Comentario> getComentarioList() {
		return comentarioList;
	}

	public void setComentarioList(List<Comentario> comentarioList) {
		this.comentarioList = comentarioList;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", password=" + password + ", matriculaList=" + matriculaList + ", comentarioList=" + comentarioList
				+ "]";
	}

	
}

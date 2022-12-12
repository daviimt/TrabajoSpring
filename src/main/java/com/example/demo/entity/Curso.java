package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Curso {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nombre;
	private String descripcion;
	private int nivel;
	private String fechaInicio;
	private String fechaFin;
	
	@ManyToOne
	@JoinColumn(name="idProfesor")
	private Profesor idProfesor;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="curso")
	private List<Comentario> comentarioList;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="curso")
	private List<Matricula> matriculaList;
	
	public Curso() {
		super();
	}

	public Curso(int id, String nombre, String descripcion, int nivel, String fechaInicio, String fechaFin,
			Profesor idProfesor, List<Comentario> comentarioList, List<Matricula> matriculaList) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.idProfesor = idProfesor;
		this.comentarioList = comentarioList;
		this.matriculaList = matriculaList;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Profesor getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Profesor idProfesor) {
		this.idProfesor = idProfesor;
	}

	public List<Comentario> getComentarioList() {
		return comentarioList;
	}

	public void setComentarioList(List<Comentario> comentarioList) {
		this.comentarioList = comentarioList;
	}

	public List<Matricula> getMatriculaList() {
		return matriculaList;
	}

	public void setMatriculaList(List<Matricula> matriculaList) {
		this.matriculaList = matriculaList;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", nivel=" + nivel
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", idProfesor=" + idProfesor
				+ ", comentarioList=" + comentarioList + ", matriculaList=" + matriculaList + "]";
	}

	
}

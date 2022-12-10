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
	private int idCursos;
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


	public Curso(int idCursos, String nombre, String descripcion, int nivel, String fechaInicio, String fechaFin,
			Profesor profesor) {
		super();
		this.idCursos = idCursos;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.idProfesor = idProfesor;
	}


	public int getIdCursos() {
		return idCursos;
	}


	public void setIdCursos(int idCursos) {
		this.idCursos = idCursos;
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


	public Profesor getProfesor() {
		return idProfesor;
	}


	public void setProfesor(Profesor profesor) {
		this.idProfesor = profesor;
	}


	@Override
	public String toString() {
		return "Curso [idCursos=" + idCursos + ", nombre=" + nombre + ", descripcion=" + descripcion + ", nivel="
				+ nivel + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", profesor=" + idProfesor + "]";
	}
	
	
}

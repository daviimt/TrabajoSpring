package com.example.demo.models;

import java.util.Date;

public class CursoModel {
	
	private int idCursos;
	private String nombre;
	private String descripcion;
	private int nivel;
	private int idProfesor;
	private String fechaInicio;
	private String fechaFin;
	
	public CursoModel() {
		super();
	}
	public CursoModel(int idCurso, String nombre, String descripcion, int nivel, int idProfesor, String fechaInicio,
			String fechaFin) {
		super();
		this.idCursos = idCurso;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.idProfesor = idProfesor;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	public int getIdCurso() {
		return idCursos;
	}
	public void setIdCurso(int idCurso) {
		this.idCursos = idCurso;
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
	public int getIdProfesor() {
		return idProfesor;
	}
	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
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
	@Override
	public String toString() {
		return "Cursos [idCurso=" + idCursos + ", nombre=" + nombre + ", descripcion=" + descripcion + ", nivel=" + nivel
				+ ", idProfesor=" + idProfesor + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
	
}

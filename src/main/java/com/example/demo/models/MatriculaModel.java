package com.example.demo.models;

public class MatriculaModel {
	
	private int idMatricula;
	private int idAlumno;
	private int idCurso;
	private int valoracion;
	
	public MatriculaModel() {
		super();
	}
	public MatriculaModel(int idMatricula, int idAlumno, int idCurso, int valoracion) {
		super();
		this.idMatricula = idMatricula;
		this.idAlumno = idAlumno;
		this.idCurso = idCurso;
		this.valoracion = valoracion;
	}
	public int getIdMatriculas() {
		return idMatricula;
	}
	public void setIdMatriculas(int idMatricula) {
		this.idMatricula = idMatricula;
	}
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	@Override
	public String toString() {
		return "Matriculas [idMatricula=" + idMatricula + ", idAlumno=" + idAlumno + ", idCurso=" + idCurso
				+ ", valoracion=" + valoracion + "]";
	}
	
}

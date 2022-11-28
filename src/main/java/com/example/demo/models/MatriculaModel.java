package com.example.demo.models;

public class MatriculaModel {
	
	private int idMatriculas;
	private int idAlumno;
	private int idCurso;
	private int valoracion;
	
	public MatriculaModel() {
		super();
	}
	public MatriculaModel(int idMatriculas, int idAlumno, int idCurso, int valoracion) {
		super();
		this.idMatriculas = idMatriculas;
		this.idAlumno = idAlumno;
		this.idCurso = idCurso;
		this.valoracion = valoracion;
	}
	public int getIdMatriculas() {
		return idMatriculas;
	}
	public void setIdMatriculas(int idMatriculas) {
		this.idMatriculas = idMatriculas;
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
		return "Matriculas [idMatriculas=" + idMatriculas + ", idAlumno=" + idAlumno + ", idCurso=" + idCurso
				+ ", valoracion=" + valoracion + "]";
	}
	
}

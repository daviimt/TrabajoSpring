package com.example.demo.models;

public class MatriculaModel {
	
	private int id;
	private int idAlumno;
	private int idCurso;
	private int valoracion;
	
	public MatriculaModel() {
		super();
	}

	public MatriculaModel(int id, int idAlumno, int idCurso, int valoracion) {
		super();
		this.id = id;
		this.idAlumno = idAlumno;
		this.idCurso = idCurso;
		this.valoracion = valoracion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "MatriculaModel [id=" + id + ", idAlumno=" + idAlumno + ", idCurso=" + idCurso + ", valoracion="
				+ valoracion + "]";
	}
	
}

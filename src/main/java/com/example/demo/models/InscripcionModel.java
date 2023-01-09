package com.example.demo.models;

public class InscripcionModel {
	
	private int idCurso;
	private int idAlumno;
	private boolean inscrito;
	
	public InscripcionModel(int idCurso, int idAlumno, boolean inscrito) {
		super();
		this.idCurso = idCurso;
		this.idAlumno = idAlumno;
		this.inscrito = inscrito;
	}
	public InscripcionModel() {
		super();
	}
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	public boolean isInscrito() {
		return inscrito;
	}
	public void setInscrito(boolean inscrito) {
		this.inscrito = inscrito;
	}
	
	@Override
	public String toString() {
		return "InscripcionModel [idCurso=" + idCurso + ", idAlumno=" + idAlumno + ", inscrito=" + inscrito + "]";
	}
	
	
}

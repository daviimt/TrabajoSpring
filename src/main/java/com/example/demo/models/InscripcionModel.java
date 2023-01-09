package com.example.demo.models;

public class InscripcionModel {
	
	private CursoModel curso;
	private AlumnoModel alumno;
	private boolean inscrito;
	
	public InscripcionModel() {
		super();
	}

	public InscripcionModel(CursoModel curso, AlumnoModel alumno, boolean inscrito) {
		super();
		this.curso = curso;
		this.alumno = alumno;
		this.inscrito = inscrito;
	}

	public CursoModel getCurso() {
		return curso;
	}

	public void setCurso(CursoModel curso) {
		this.curso = curso;
	}

	public AlumnoModel getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoModel alumno) {
		this.alumno = alumno;
	}

	public boolean isInscrito() {
		return inscrito;
	}

	public void setInscrito(boolean inscrito) {
		this.inscrito = inscrito;
	}

	@Override
	public String toString() {
		return "InscripcionModel [curso=" + curso + ", alumno=" + alumno + ", inscrito=" + inscrito + "]";
	}
	
	
	
	
}

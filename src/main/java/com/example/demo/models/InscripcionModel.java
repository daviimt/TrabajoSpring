package com.example.demo.models;

public class InscripcionModel {

	private CursoModel curso;
	private AlumnoModel alumno;
	private boolean inscrito;
	private boolean comentario;
	private boolean finalizado;

	public InscripcionModel() {
		super();
	}

	public InscripcionModel(CursoModel curso, AlumnoModel alumno, boolean inscrito) {
		super();
		this.curso = curso;
		this.alumno = alumno;
		this.inscrito = inscrito;
	}

	public InscripcionModel(CursoModel curso, AlumnoModel alumno, boolean inscrito, boolean comentario) {
		super();
		this.curso = curso;
		this.alumno = alumno;
		this.inscrito = inscrito;
		this.comentario = comentario;
	}

	public InscripcionModel(CursoModel curso, AlumnoModel alumno, boolean inscrito, boolean comentario,
			boolean finalizado) {
		super();
		this.curso = curso;
		this.alumno = alumno;
		this.inscrito = inscrito;
		this.comentario = comentario;
		this.finalizado = finalizado;
	}

	public boolean isComentario() {
		return comentario;
	}

	public void setComentario(boolean comentario) {
		this.comentario = comentario;
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

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	@Override
	public String toString() {
		return "InscripcionModel [curso=" + curso + ", alumno=" + alumno + ", inscrito=" + inscrito + ", comentario="
				+ comentario + ", finalizado=" + finalizado + "]";
	}

}

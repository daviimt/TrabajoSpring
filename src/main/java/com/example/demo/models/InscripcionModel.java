package com.example.demo.models;

import com.example.demo.entity.Matricula;

public class InscripcionModel {

	private CursoModel curso;
	private AlumnoModel alumno;
	private MatriculaModel matricula;
	private boolean inscrito;
	private boolean comentario;
	private boolean finalizado;
	private int notaMedia;


	public InscripcionModel() {
		super();
	}

	public InscripcionModel( AlumnoModel alumno, int notaMedia) {
		super();
		this.alumno = alumno;
		this.notaMedia = notaMedia;
	}



	public InscripcionModel(CursoModel curso, AlumnoModel alumno, MatriculaModel matricula) {
		super();
		this.curso = curso;
		this.alumno = alumno;
		this.matricula = matricula;
	}

	public InscripcionModel(CursoModel curso, AlumnoModel alumno, MatriculaModel matricula, boolean inscrito,
			boolean comentario, boolean finalizado) {
		super();
		this.curso = curso;
		this.alumno = alumno;
		this.matricula = matricula;
		this.inscrito = inscrito;
		this.comentario = comentario;
		this.finalizado = finalizado;
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

	public MatriculaModel getMatricula() {
		return matricula;
	}

	public void setMatricula(MatriculaModel matricula) {
		this.matricula = matricula;
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

	public int getNotaMedia() {
		return notaMedia;
	}
	
	public void setNotaMedia(int notaMedia) {
		this.notaMedia = notaMedia;
	}

	@Override
	public String toString() {
		return "InscripcionModel [curso=" + curso + ", alumno=" + alumno + ", matricula=" + matricula + ", inscrito="
				+ inscrito + ", comentario=" + comentario + ", finalizado=" + finalizado + ", notaMedia=" + notaMedia
				+ "]";
	}

}

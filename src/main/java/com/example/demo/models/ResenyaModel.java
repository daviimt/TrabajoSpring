package com.example.demo.models;

public class ResenyaModel {
	
	ComentarioModel comentario;
	AlumnoModel alumno;
	
	public ResenyaModel(ComentarioModel comentario, AlumnoModel alumno) {
		super();
		this.comentario = comentario;
		this.alumno = alumno;
	}

	public ResenyaModel() {
		super();
	}

	public ComentarioModel getComentario() {
		return comentario;
	}

	public void setComentario(ComentarioModel comentario) {
		this.comentario = comentario;
	}

	public AlumnoModel getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoModel alumno) {
		this.alumno = alumno;
	}

	@Override
	public String toString() {
		return "ResenyaModel [comentario=" + comentario + ", alumno=" + alumno + "]";
	}
	
	
	
	
}

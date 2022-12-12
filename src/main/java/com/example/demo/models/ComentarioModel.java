package com.example.demo.models;

public class ComentarioModel {

	private int id;
	private int idAlumno;
	private int idCurso;
	private String comentario;
	
	public ComentarioModel() {
		super();
	}

	public ComentarioModel(int id, int idAlumno, int idCurso, String comentario) {
		super();
		this.id = id;
		this.idAlumno = idAlumno;
		this.idCurso = idCurso;
		this.comentario = comentario;
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

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "ComentarioModel [id=" + id + ", idAlumno=" + idAlumno + ", idCurso=" + idCurso + ", comentario="
				+ comentario + "]";
	}
}

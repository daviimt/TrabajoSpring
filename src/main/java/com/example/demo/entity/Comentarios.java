package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comentarios {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idComentarios;
	private int idAlumno;
	private int idCurso;
	private String comentario;
	
	public Comentarios() {
		super();
	}
	public Comentarios(int idComentarios, int idAlumno, int idCurso, String comentario) {
		super();
		this.idComentarios = idComentarios;
		this.idAlumno = idAlumno;
		this.idCurso = idCurso;
		this.comentario = comentario;
	}
	public int getIdComentarios() {
		return idComentarios;
	}
	public void setIdComentarios(int idComentarios) {
		this.idComentarios = idComentarios;
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
		return "Comentarios [idComentarios=" + idComentarios + ", idAlumno=" + idAlumno + ", idCurso=" + idCurso
				+ ", comentario=" + comentario + "]";
	}
	
}

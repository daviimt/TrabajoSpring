package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comentario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String comentario;
	
	@ManyToOne
	@JoinColumn(name="cursoId")
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name="alumnoId")
	private Alumno alumno;

	public Comentario() {
		super();
	}

	public Comentario(int id, String comentario, Curso curso, Alumno alumno) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.curso = curso;
		this.alumno = alumno;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", comentario=" + comentario + ", curso=" + curso + ", alumno=" + alumno + "]";
	}


}

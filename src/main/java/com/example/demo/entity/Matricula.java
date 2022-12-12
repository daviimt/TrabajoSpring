package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Matricula {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int valoracion;

	@ManyToOne
	@JoinColumn(name = "cursoId")
	private Curso curso;

	@ManyToOne
	@JoinColumn(name = "alumnoId")
	private Alumno alumno;

	public Matricula() {
		super();
	}

	public Matricula(int id, int valoracion, Curso curso, Alumno alumno) {
		super();
		this.id = id;
		this.valoracion = valoracion;
		this.curso = curso;
		this.alumno = alumno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
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
		return "Matricula [id=" + id + ", valoracion=" + valoracion + ", curso=" + curso + ", alumno=" + alumno + "]";
	}
	
	
}

package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Noticia {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String titulo;
	private String descripcion;
	private String imagen;
	
	@ManyToOne
	@JoinColumn(name="usuarioId")
	private Usuario usuario;
	
	public Noticia() {
		super();
	}

	public Noticia(int id, String titulo, String descripcion, String imagen, Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Noticia [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", imagen=" + imagen
				+ ", usuario=" + usuario + "]";
	}
	
}

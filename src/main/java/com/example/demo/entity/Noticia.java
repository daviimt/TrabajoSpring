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
	private int idNoticias;
	private String titulo;
	private String descripcion;
	private String imagen;
	
	@ManyToOne
	@JoinColumn(name="administradorId")
	private Administrador administrador;
	
	public Noticia() {
		super();
	}

	public Noticia(int idNoticias, String titulo, String descripcion, String imagen, Administrador administrador) {
		super();
		this.idNoticias = idNoticias;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.administrador = administrador;
	}

	public int getIdNoticias() {
		return idNoticias;
	}

	public void setIdNoticias(int idNoticias) {
		this.idNoticias = idNoticias;
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

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	@Override
	public String toString() {
		return "Noticia [idNoticias=" + idNoticias + ", titulo=" + titulo + ", descripcion=" + descripcion + ", imagen="
				+ imagen + ", administrador=" + administrador + "]";
	}

	
	
	
	
	
}

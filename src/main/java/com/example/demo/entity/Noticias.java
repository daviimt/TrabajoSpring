package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Noticias {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idNoticias;
	private String titulo;
	private String descripcion;
	private String imagen;
	private int idAdministrador;
	
	public Noticias() {
		super();
	}
	public Noticias(int idNoticias, String titulo, String descripcion, String imagen, int idAdministrador) {
		super();
		this.idNoticias = idNoticias;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.idAdministrador = idAdministrador;
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
	public int getIdAdministrador() {
		return idAdministrador;
	}
	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
	}
	@Override
	public String toString() {
		return "Noticias [idNoticias=" + idNoticias + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", imagen=" + imagen + ", idAdministrador=" + idAdministrador + "]";
	}
	
	
}

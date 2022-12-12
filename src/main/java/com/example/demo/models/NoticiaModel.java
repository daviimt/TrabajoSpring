package com.example.demo.models;


public class NoticiaModel {
	private int id;
	private String titulo;
	private String descripcion;
	private String imagen;
	private int idAdministrador;
	
	public NoticiaModel() {
		super();
	}

	public NoticiaModel(int id, String titulo, String descripcion, String imagen, int idAdministrador) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.idAdministrador = idAdministrador;
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

	public int getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	@Override
	public String toString() {
		return "NoticiaModel [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", imagen=" + imagen
				+ ", idAdministrador=" + idAdministrador + "]";
	}
	
}

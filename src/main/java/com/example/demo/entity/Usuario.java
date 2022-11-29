package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "nombreUsuario", unique = true, nullable = false)
	private String nombreUsuario;

	@Column(name = "contra", nullable = false)
	@Size(max = 100)
	private String contra;

	private boolean activado;

	private String rol;

	public Usuario() {
		super();
	}

	public Usuario(long id, String nombreUsuario, @Size(max = 100) String contra, boolean activado, String rol) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.contra = contra;
		this.activado = activado;
		this.rol = rol;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public boolean isActivado() {
		return activado;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", contra=" + contra + ", activado="
				+ activado + ", rol=" + rol + "]";
	}

}

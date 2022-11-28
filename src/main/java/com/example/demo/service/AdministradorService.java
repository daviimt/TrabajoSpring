package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Administrador;
import com.example.demo.models.AdministradorModel;

public interface AdministradorService {

	public abstract List<AdministradorModel> ListAllAdmin();
	public abstract Administrador addAdmin(AdministradorModel adminModel);
	public abstract int removeAdmin(int id);
	public abstract Administrador updateAdmin(AdministradorModel adminModel);
	public abstract Administrador transform(AdministradorModel adminModel);
	public abstract AdministradorModel transform(Administrador admin);
}

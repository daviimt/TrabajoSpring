package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Administrador;
import com.example.demo.entity.Alumno;
import com.example.demo.models.AlumnoModel;

public interface alumnoService {

	public abstract List<AlumnoModel> ListAllAdmin();
	public abstract Administrador addAdmin(AlumnoModel adminModel);
	public abstract int removeAdmin(int id);
	public abstract Alumno updateAdmin(AlumnoModel adminModel);
	public abstract Alumno transform(AlumnoModel adminModel);
	public abstract AlumnoModel transform(Alumno admin);
}

package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Administrador;
import com.example.demo.entity.Alumno;
import com.example.demo.models.AlumnoModel;

public interface AlumnoService {

	public abstract List<AlumnoModel> ListAllAlumnos();
	public abstract Alumno addAlumno(AlumnoModel alumnoModel);
	public abstract int removeAlumno(int id);
	public abstract Alumno updateAlumno(AlumnoModel alumnoModel);
	public abstract Alumno transform(AlumnoModel alumnoModel);
	public abstract AlumnoModel transform(Alumno alumno);
}
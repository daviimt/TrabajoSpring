package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Alumno;
import com.example.demo.models.AlumnoModel;
import com.example.demo.models.CursoModel;

public interface AlumnoService {

	public abstract List<AlumnoModel> ListAllAlumnos();
	public abstract Alumno addAlumno(AlumnoModel alumnoModel);
	public abstract int removeAlumno(int id);
	public abstract Alumno updateAlumno(AlumnoModel alumnoModel);
	public abstract Alumno transform(AlumnoModel alumnoModel);
	public abstract AlumnoModel transform(Alumno alumno);
	public abstract AlumnoModel findStudent(String email);
	public abstract AlumnoModel findStudent(int id);
	public abstract List<CursoModel> findCursosBasicos();
	public abstract List<CursoModel> findCursosMedios();
	public abstract List<CursoModel> findCursosAvanzados();

}

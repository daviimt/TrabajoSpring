package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Alumno;
import com.example.demo.models.AlumnoModel;

public interface AlumnoService {

	public abstract List<AlumnoModel> ListAllAlumnos();
	public abstract Alumno addAlumno(AlumnoModel alumnoModel);
	public abstract int removeAlumno(int id);
	public abstract Alumno updateAlumno(AlumnoModel alumnoModel);
	public abstract Alumno transform(AlumnoModel alumnoModel);
	public abstract AlumnoModel transform(Alumno alumno);
<<<<<<< HEAD
	public abstract AlumnoModel findStudent(String email);
=======
	public abstract AlumnoModel findStudent(int id);
	public abstract AlumnoModel findStudent(String username);
>>>>>>> 17a420a2bf973b65fabd3a259ec30c8995de5aca
}

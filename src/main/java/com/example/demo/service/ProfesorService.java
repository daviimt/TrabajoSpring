package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Profesor;
import com.example.demo.models.ProfesorModel;

public interface ProfesorService {

	public abstract List<ProfesorModel> listAllProfesores();
	public abstract Profesor addProfesor(ProfesorModel profModel);
	public abstract int removeProfesor(int id);
	public abstract Profesor updateProfesor(ProfesorModel profModel);
	public abstract Profesor transform(ProfesorModel profModel);
	public abstract ProfesorModel transform(Profesor profesor);
}

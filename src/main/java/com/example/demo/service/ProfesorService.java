 package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Profesor;
import com.example.demo.models.CursoModel;
import com.example.demo.models.ProfesorModel;

public interface ProfesorService {

	public abstract List<ProfesorModel> listAllProfesores();
	public abstract Profesor addProfesor(ProfesorModel profModel);
	public abstract int removeProfesor(int id);
	public abstract Profesor updateProfesor(ProfesorModel profModel);
	public abstract Profesor transform(ProfesorModel profModel);
	public abstract ProfesorModel transform(Profesor profesor);
	public abstract ProfesorModel findProfesor(String email); 
	public abstract ProfesorModel findProfesor(int id);
	public abstract List<CursoModel> findCursosByIdProfesor(ProfesorModel profesor);
	public abstract List<CursoModel> findCursosAcabados();
	public abstract List<CursoModel> findCursosSinEmpezar();
	public abstract List<CursoModel> findCursosImpartiendose();
	public abstract List<CursoModel> findCursosFechas(String fechaInic, String fechaFin);
	
}

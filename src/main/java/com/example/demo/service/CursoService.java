package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Curso;
import com.example.demo.models.CursoModel;

public interface CursoService {

	public abstract List<CursoModel> ListAllCursos();
	public abstract Curso addCurso(CursoModel cursoModel);
	public abstract int removeCurso(int id);
	public abstract Curso updateCurso(CursoModel cursoModel);
	public abstract CursoModel findCurso(int id);
	public abstract Curso transform(CursoModel cursoModel);
	public abstract CursoModel transform(Curso curso);
	public abstract List<Curso> findCursoByIdProfesor(int id);
}

package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Curso;
import com.example.demo.models.CursoModel;

public interface CursoService {

	public abstract List<CursoModel> ListAllCurso();
	public abstract Curso addCurso(CursoModel cursoModel);
	public abstract int removeCurso(int id);
	public abstract Curso updateCurso(CursoModel cursoModel);
	public abstract CursoModel findCurso(int i);
	public abstract Curso transform(CursoModel cursoModel);
	public abstract CursoModel transform(Curso curso);
}

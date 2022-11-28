package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Curso;
import com.example.demo.models.CursoModel;

public interface cursoService {

	public abstract List<CursoModel> ListAllMatriculas();
	public abstract Curso addMatricula(CursoModel matriculaModel);
	public abstract int removeMatricula(int id);
	public abstract Curso updateMatricula(CursoModel matriculaModel);
	public abstract Curso transform(CursoModel matriculaModel);
	public abstract CursoModel transform(Curso matricula);
}

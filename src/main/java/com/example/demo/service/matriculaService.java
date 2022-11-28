package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Matricula;
import com.example.demo.models.MatriculaModel;

public interface matriculaService {
	public abstract List<MatriculaModel> ListAllMatriculas();
	public abstract Matricula addMatricula(MatriculaModel matriculaModel);
	public abstract int removeMatricula(int id);
	public abstract Matricula updateMatricula(MatriculaModel matriculaModel);
	public abstract Matricula transform(MatriculaModel matriculaModel);
	public abstract MatriculaModel transform(Matricula matricula);
}

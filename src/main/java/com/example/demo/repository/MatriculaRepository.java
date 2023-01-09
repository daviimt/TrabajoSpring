package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Curso;
import com.example.demo.entity.Matricula;
import com.example.demo.models.MatriculaModel;

@Repository("matriculaRepository")
public interface MatriculaRepository extends JpaRepository<Matricula, Serializable>{
	public abstract List<Matricula> findBycursoId(int cursoId);
}

package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Curso;
import com.example.demo.entity.Profesor;
import com.example.demo.models.CursoModel;
import com.example.demo.models.ProfesorModel;
@Repository("cursoRepository")
public interface CursoRepository extends JpaRepository<Curso, Serializable>{
	public abstract List<Curso> findByIdProfesor(int idProfesor);
	public abstract List<Curso> findByIdProfesor(Profesor profesor);
}

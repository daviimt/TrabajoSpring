package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Profesor;
import com.example.demo.entity.Usuario;

@Repository("profesorRepository")
public interface ProfesorRepository extends JpaRepository<Profesor, Serializable>{
	public abstract Profesor findByEmail(String email);
}

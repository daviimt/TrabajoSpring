package com.example.demo.repository;

import java.io.Serializable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Alumno;
import com.example.demo.entity.Usuario;



@Repository("alumnoRepository")
public interface AlumnoRepository extends JpaRepository<Alumno, Serializable>{

	public abstract Alumno findByEmail(String email);

}

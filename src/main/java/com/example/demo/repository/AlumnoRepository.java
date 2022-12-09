package com.example.demo.repository;

import java.io.Serializable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Alumno;
import com.example.demo.entity.Usuario;



@Repository("alumnoRepository")
public interface AlumnoRepository extends JpaRepository<Alumno, Serializable>{
<<<<<<< HEAD
	public abstract Alumno findByEmail(String email);
=======
	public abstract Alumno findByEmail(String username);
>>>>>>> 17a420a2bf973b65fabd3a259ec30c8995de5aca
}

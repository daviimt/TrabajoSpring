package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Noticia;
import com.example.demo.entity.Profesor;

@Repository("noticiaRepository")
public interface NoticiaRepository extends JpaRepository<Noticia, Serializable>{
	public abstract Noticia findByTitulo(String titulo);
}

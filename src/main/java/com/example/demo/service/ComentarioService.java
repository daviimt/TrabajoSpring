package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Comentario;
import com.example.demo.models.ComentarioModel;

public interface ComentarioService {


	public abstract List<ComentarioModel> ListAllComentario();
	public abstract Comentario addComentario(ComentarioModel comentarioModel);
	public abstract int removeComentario(int id);
	public abstract Comentario updateComentario(ComentarioModel comentarioModel);
	public abstract Comentario transform(ComentarioModel comentarioModel);
	public abstract ComentarioModel transform(Comentario comentario);
}

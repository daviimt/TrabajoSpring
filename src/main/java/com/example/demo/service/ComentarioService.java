package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Comentario;
import com.example.demo.models.ComentarioModel;

public interface ComentarioService {


	public abstract List<ComentarioModel> ListAllAdmin();
	public abstract Comentario addAdmin(ComentarioModel adminModel);
	public abstract int removeAdmin(int id);
	public abstract Comentario updateAdmin(ComentarioModel adminModel);
	public abstract Comentario transform(ComentarioModel adminModel);
	public abstract ComentarioModel transform(Comentario admin);
}

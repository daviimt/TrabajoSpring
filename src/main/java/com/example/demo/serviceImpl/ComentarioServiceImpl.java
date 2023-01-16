package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Comentario;
import com.example.demo.models.ComentarioModel;
import com.example.demo.repository.ComentarioRepository;
import com.example.demo.service.ComentarioService;
@Service("comentarioService")
public class ComentarioServiceImpl implements ComentarioService{

	@Autowired
	@Qualifier("comentarioRepository")
	private ComentarioRepository comentarioRepository;

	@Override
	public List<ComentarioModel> ListAllComentario() {
		return comentarioRepository.findAll().stream()
				.map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Comentario addComentario(ComentarioModel comentarioModel) {
		return comentarioRepository.save(transform(comentarioModel));
	}

	@Override
	public int removeComentario(int id) {
		comentarioRepository.deleteById(id);
		return 0;
	}

	@Override
	public Comentario updateComentario(ComentarioModel comentarioModel) {
		return comentarioRepository.save(transform(comentarioModel));
	}

	@Override
	public Comentario transform(ComentarioModel comentarioModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(comentarioModel, Comentario.class);
	}

	@Override
	public ComentarioModel transform(Comentario comentario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(comentario, ComentarioModel.class);
	}

	@Override
	public List<ComentarioModel> ListComentarioCurso(int idCurso) {
		List<ComentarioModel> listComentariosCurso=new ArrayList();
		List<ComentarioModel> listComentarios= ListAllComentario();
		
		for(ComentarioModel c:listComentarios) {
			if(c.getIdCurso()==idCurso)
				listComentariosCurso.add(c);
		}
		
		return listComentariosCurso;
	}
	
	
	

}

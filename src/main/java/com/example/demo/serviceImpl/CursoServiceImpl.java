package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.demo.entity.Curso;
import com.example.demo.models.CursoModel;
import com.example.demo.repository.CursoRepository;
import com.example.demo.service.CursoService;

public class CursoServiceImpl implements CursoService{

	@Autowired
	@Qualifier("cursoRepository")
	private CursoRepository cursoRepository;
	
	@Override
	public List<CursoModel> ListAllCurso() {
		return cursoRepository.findAll().stream()
				.map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Curso addCurso(CursoModel cursoModel) {
		return cursoRepository.save(transform(cursoModel));
	}

	@Override
	public int removeCurso(int id) {
		cursoRepository.deleteById(id);
		return 0;
	}

	@Override
	public Curso updateCurso(CursoModel cursoModel) {
		return cursoRepository.save(transform(cursoModel));
	}

	@Override
	public Curso transform(CursoModel cursoModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cursoModel, Curso.class);
	}

	@Override
	public CursoModel transform(Curso curso) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(curso, CursoModel.class);
	}

}

package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Matricula;
import com.example.demo.models.MatriculaModel;
import com.example.demo.repository.MatriculaRepository;
import com.example.demo.service.MatriculaService;

@Service("matriculaService")
public class MatriculaServiceImpl implements MatriculaService{

	@Autowired
	@Qualifier("matriculaRepository")
	private MatriculaRepository matriculaRepository;

	@Override
	public List<MatriculaModel> listAllMatriculas() {
		return matriculaRepository.findAll().stream().map(c -> transform(c)).collect(Collectors.toList());
	}

	@Override
	public Matricula addMatricula(MatriculaModel matriculaModel) {
		return matriculaRepository.save(transform(matriculaModel));
	}

	@Override
	public int removeMatricula(int id) {
		matriculaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Matricula updateMatricula(MatriculaModel matriculaModel) {
		return matriculaRepository.save(transform(matriculaModel));
	}
	
	@Override
	public Matricula transform(MatriculaModel courseModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(courseModel, Matricula.class);
	}

	@Override
	public MatriculaModel transform(Matricula course) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(course, MatriculaModel.class);
	}
}

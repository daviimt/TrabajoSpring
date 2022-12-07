package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Alumno;
import com.example.demo.models.AlumnoModel;
import com.example.demo.repository.AlumnoRepository;
import com.example.demo.service.AlumnoService;
@Service("alumnoServiceImpl")
public class AlumnoServiceImpl implements AlumnoService{

	@Autowired
	@Qualifier("alumnoRepository")
	private AlumnoRepository alumnoRepository;
	
	@Override
	public List<AlumnoModel> ListAllAlumnos() {
		return alumnoRepository.findAll().stream()
				.map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Alumno addAlumno(AlumnoModel alumnoModel) {
		return alumnoRepository.save(transform(alumnoModel));
	}

	@Override
	public int removeAlumno(int id) {
		alumnoRepository.deleteById(id);
		return 0;
	}

	@Override
	public Alumno updateAlumno(AlumnoModel alumnoModel) {
		return alumnoRepository.save(transform(alumnoModel));
	}

	@Override
	public Alumno transform(AlumnoModel alumnoModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(alumnoModel, Alumno.class);
	}

	@Override
	public AlumnoModel transform(Alumno alumno) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(alumno, AlumnoModel.class);
	}

	@Override
	public AlumnoModel findStudent(int id) {
		return transform(alumnoRepository.findById(id).orElse(null));
	}
	

}

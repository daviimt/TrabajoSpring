package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Profesor;
import com.example.demo.models.ProfesorModel;
import com.example.demo.repository.ProfesorRepository;
import com.example.demo.service.ProfesorService;

@Service("profesorService")
public class ProfesorServiceImpl implements ProfesorService{

	@Autowired
	@Qualifier("profesorRepository")
	private ProfesorRepository profesorRepository;

	@Override
	public List<ProfesorModel> listAllProfesores() {
		return profesorRepository.findAll().stream().map(c -> transform(c)).collect(Collectors.toList());
	}

	@Override
	public Profesor addProfesor(ProfesorModel profesorModel) {
		return profesorRepository.save(transform(profesorModel));
	}

	@Override
	public int removeProfesor(int id) {
		profesorRepository.deleteById(id);
		return 0;
	}

	@Override
	public Profesor updateProfesor(ProfesorModel profesorModel) {
		return profesorRepository.save(transform(profesorModel));
	}
	
	@Override
	public Profesor transform(ProfesorModel courseModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(courseModel, Profesor.class);
	}

	@Override
	public ProfesorModel transform(Profesor course) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(course, ProfesorModel.class);
	}

	@Override
	public ProfesorModel findProfesor(String email) {
		return transform(profesorRepository.findByEmail(email));
	}

}

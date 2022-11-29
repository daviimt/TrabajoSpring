package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.demo.entity.Administrador;
import com.example.demo.models.AdministradorModel;
import com.example.demo.repository.AdministradorRepository;
import com.example.demo.service.AdministradorService;

public class AdministradorServiceImpl implements AdministradorService {

	@Autowired
	@Qualifier("administradorRepository")
	private AdministradorRepository administradorRepository;
	@Override
	public List<AdministradorModel> ListAllAdmin() {
		return administradorRepository.findAll().stream()
				.map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Administrador addAdmin(AdministradorModel adminModel) {
		return administradorRepository.save(transform(adminModel));
	}

	@Override
	public int removeAdmin(int id) {
		administradorRepository.deleteById(id);
		return 0;
	}

	@Override
	public Administrador updateAdmin(AdministradorModel adminModel) {
		return administradorRepository.save(transform(adminModel));
	}

	@Override
	public Administrador transform(AdministradorModel adminModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(adminModel, Administrador.class);
	}

	@Override
	public AdministradorModel transform(Administrador admin) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(admin, AdministradorModel.class);
	}

}

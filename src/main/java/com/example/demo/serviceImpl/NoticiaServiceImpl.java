package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Noticia;
import com.example.demo.models.NoticiaModel;
import com.example.demo.repository.NoticiaRepository;
import com.example.demo.service.NoticiaService;

@Service("noticiaService")
public class NoticiaServiceImpl implements NoticiaService {

	@Autowired
	@Qualifier("noticiaRepository")
	private NoticiaRepository noticiaRepository;
	
	@Override
	public List<NoticiaModel> ListAllNoticias() {
		return noticiaRepository.findAll().stream()
				.map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Noticia addNoticia(NoticiaModel noticiaModel) {
		return noticiaRepository.save(transform(noticiaModel));
	}

	@Override
	public int removeNoticia(int id) {
		noticiaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Noticia updateNoticia(NoticiaModel noticiaModel) {
		return noticiaRepository.save(transform(noticiaModel));
	}

	@Override
	public Noticia transform(NoticiaModel noticiaModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(noticiaModel, Noticia.class);
	}

	@Override
	public NoticiaModel transform(Noticia noticia) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(noticia, NoticiaModel.class);
	}

}

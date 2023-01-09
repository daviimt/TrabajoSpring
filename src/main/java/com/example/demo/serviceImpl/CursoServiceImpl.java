package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Curso;
import com.example.demo.entity.Usuario;
import com.example.demo.models.AlumnoModel;
import com.example.demo.models.CursoModel;
import com.example.demo.models.InscripcionModel;
import com.example.demo.models.MatriculaModel;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.MatriculaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.AlumnoService;
import com.example.demo.service.CursoService;
import com.example.demo.service.MatriculaService;
@Service("cursoService")
public class CursoServiceImpl implements CursoService{

	@Autowired
	@Qualifier("cursoRepository")
	private CursoRepository cursoRepository;
	
	@Autowired
	@Qualifier("matriculaService")
	private MatriculaService matriculaService;
	
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;
	
	@Override
	public List<CursoModel> ListAllCursos() {
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
	public CursoModel findCurso(int id) {
		return transform(cursoRepository.findById(id).orElse(null));
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

	@Override
	public List<InscripcionModel> listInscripcion(AlumnoModel alumno,List<CursoModel>listCursos) {
		
		List<InscripcionModel> listInscrip=new ArrayList();
		List<MatriculaModel> matr = matriculaService.listAllMatriculas();
		
		UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u=usuarioRepository.findByUsername(userDetails.getUsername());
		
		for(CursoModel c :listCursos) {
			InscripcionModel insc=new InscripcionModel();
			for(MatriculaModel matricula :matr) {
				if(matricula.getIdCurso()==c.getId() && matricula.getIdAlumno()==(u.getId()+1)) {
					insc=new InscripcionModel(c,alumno,true);
					break;
				}else {
					insc=new InscripcionModel(c,alumno,false);
				}
			}
			
			listInscrip.add(insc);
		}
		return listInscrip;
	}


}

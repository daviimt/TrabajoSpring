package com.example.demo.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import com.example.demo.models.ProfesorModel;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.CursoService;
import com.example.demo.service.MatriculaService;
import com.example.demo.service.ProfesorService;
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
	
	@Autowired
	@Qualifier("profesorService")
	private ProfesorService profesorService;
	
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
		List<CursoModel> listCursosAcabados=findCursosAcabados();
		
		Calendar c1 = Calendar.getInstance();
		Calendar calen = new GregorianCalendar();

		int dia = calen.get(Calendar.DATE);
		int mes = (calen.get(Calendar.MONTH) + 1);
		int annio = calen.get(Calendar.YEAR);

		LocalDate fechaActual = LocalDate.of(annio, mes, dia);
		
		MatriculaModel m = new MatriculaModel();
		matr.add(m);
		
		UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u=usuarioRepository.findByUsername(userDetails.getUsername());
		
		for(CursoModel c :listCursos) {
			InscripcionModel insc=new InscripcionModel();
			boolean comentario= listCursosAcabados.contains(c);
			boolean finalizado=false;
			String[] fechafin = c.getFechaFin().split("-");
			LocalDate fechaCurso = LocalDate.of(Integer.parseInt(fechafin[0]), Integer.parseInt(fechafin[1]),
					Integer.parseInt(fechafin[2]));
			
			if (fechaCurso.isBefore(fechaActual)) {
					finalizado=true;		
			}
			
			
			for(MatriculaModel matricula :matr) {
				
				if(matricula.getIdCurso()==c.getId() && matricula.getIdAlumno()==(u.getId()+1)) {
					insc=new InscripcionModel(c,alumno,matricula,true,comentario,finalizado);
					break;
				}else {
					insc=new InscripcionModel(c,alumno,matricula,false,comentario,finalizado);
				}
			}
			
			listInscrip.add(insc);
		}
		return listInscrip;
	}
	
	@Override
	public List<CursoModel> findCursosAcabados() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		
		List<CursoModel> curso = ListAllCursos();
		
		Calendar c1 = Calendar.getInstance();
		Calendar c = new GregorianCalendar();

		int dia = c.get(Calendar.DATE);
		int mes = (c.get(Calendar.MONTH) + 1);
		int annio = c.get(Calendar.YEAR);

		LocalDate fechaActual = LocalDate.of(annio, mes, dia);

		List<CursoModel> cursoAcabado = new ArrayList();
		for (CursoModel cur : curso) {
			String[] fechafin = cur.getFechaFin().split("-");
			LocalDate fechaCurso = LocalDate.of(Integer.parseInt(fechafin[0]), Integer.parseInt(fechafin[1]),
					Integer.parseInt(fechafin[2]));
			if (fechaCurso.isBefore(fechaActual)) {
				cursoAcabado.add(cur);
			}
		}

		return cursoAcabado;
	}


}

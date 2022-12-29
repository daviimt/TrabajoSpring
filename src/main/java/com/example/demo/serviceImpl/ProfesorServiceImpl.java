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
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Profesor;
import com.example.demo.entity.Usuario;
import com.example.demo.models.CursoModel;
import com.example.demo.models.ProfesorModel;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.ProfesorRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.ProfesorService;

@Service("profesorService")
public class ProfesorServiceImpl implements ProfesorService{

	private static final String COURSES_PROFESOR_VIEW = "cursosProfesor";
	
	@Autowired
	@Qualifier("profesorRepository")
	private ProfesorRepository profesorRepository;
	
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	@Qualifier("cursoRepository")
	private CursoRepository cursoRepository;
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	@Override
	public List<ProfesorModel> listAllProfesores() {
		return profesorRepository.findAll().stream().map(c -> transform(c)).collect(Collectors.toList());
	}

	@Override
	public Profesor addProfesor(ProfesorModel profesorModel) {
		profesorModel.setPassword(usuarioService.passwordEncoder().encode(profesorModel.getPassword()));
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
	public Profesor transform(ProfesorModel profesorModel) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(profesorModel, Profesor.class);
	}

	@Override
	public ProfesorModel transform(Profesor profesor) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(profesor, ProfesorModel.class);
	}

	@Override
	public ProfesorModel findProfesor(String email) {
		return transform(profesorRepository.findByEmail(email));
	}
	@Override
	public ProfesorModel findProfesor(int id) {
		return transform(profesorRepository.findById(id));
	}
	
	@Override
	public List<CursoModel> findCursosByIdProfesor(ProfesorModel profesor) {
		ModelMapper modelMapper = new ModelMapper();
		System.out.println("cursos profesor");
		System.out.println(cursoRepository.findByIdProfesor(transform(profesor)).stream()
				.map(c -> modelMapper.map(c, CursoModel.class)).collect(Collectors.toList()));
		return cursoRepository.findByIdProfesor(transform(profesor)).stream()
				.map(c -> modelMapper.map(c, CursoModel.class)).collect(Collectors.toList());
	}
	
	@Override
	public List<CursoModel> findCursosAcabados() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		ProfesorModel profe = findProfesor(u.getId()+1);
		System.out.println("profe");
		System.out.println(profe);
		List<CursoModel> curso = findCursosByIdProfesor(profe);
		System.out.println("controller filtro curso");
		System.out.println(curso);
		Calendar c1 = Calendar.getInstance();
		Calendar c = new GregorianCalendar();

		int dia = c.get(Calendar.DATE);
		int mes = (c.get(Calendar.MONTH)+1);
		int annio = c.get(Calendar.YEAR);
		System.out.println(dia+"/"+mes+"/"+annio);
		
		LocalDate fechaActual=LocalDate.of(annio, mes, dia);
		
		
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
		List<CursoModel> cursoAcabado=new ArrayList();
		for(CursoModel cur: curso) {
			System.out.println(cur.getFechaFin());
			String[]fechafin=cur.getFechaFin().split("-");
			LocalDate fechaCurso=LocalDate.of(Integer.parseInt(fechafin[0]), Integer.parseInt(fechafin[1]), Integer.parseInt(fechafin[2]));
			if(fechaCurso.isBefore(fechaActual)) {
				cursoAcabado.add(cur);
			}
		}
		
		return cursoAcabado;
	}
	
	@Override
	public List<CursoModel> findCursosImpartiendose() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		ProfesorModel profe = findProfesor(u.getId()+1);
		System.out.println("profe");
		System.out.println(profe);
		List<CursoModel> curso = findCursosByIdProfesor(profe);
		System.out.println("controller filtro curso");
		System.out.println(curso);
		Calendar c1 = Calendar.getInstance();
		Calendar c = new GregorianCalendar();

		int dia = c.get(Calendar.DATE);
		int mes = (c.get(Calendar.MONTH)+1);
		int annio = c.get(Calendar.YEAR);
		System.out.println(dia+"/"+mes+"/"+annio);
		
		LocalDate fechaActual=LocalDate.of(annio, mes, dia);
		
		
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
		List<CursoModel> cursosImpartiendose=new ArrayList();
		for(CursoModel cur: curso) {
			System.out.println(cur.getFechaFin());
			String[]fechafin=cur.getFechaFin().split("-");
			String[]fechainic=cur.getFechaInicio().split("-");
			LocalDate fechaCursofin=LocalDate.of(Integer.parseInt(fechafin[0]), Integer.parseInt(fechafin[1]), Integer.parseInt(fechafin[2]));
			LocalDate fechaCursoinic=LocalDate.of(Integer.parseInt(fechainic[0]), Integer.parseInt(fechainic[1]), Integer.parseInt(fechainic[2]));
			if(fechaCursoinic.isBefore(fechaActual)&&fechaCursofin.isAfter(fechaActual)) {
				cursosImpartiendose.add(cur);
			}
		}
		
		return cursosImpartiendose;
	}
	
	@Override
	public List<CursoModel> findCursosSinEmpezar() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		ProfesorModel profe = findProfesor(u.getId()+1);
		System.out.println("profe");
		System.out.println(profe);
		List<CursoModel> curso = findCursosByIdProfesor(profe);
		System.out.println("controller filtro curso");
		System.out.println(curso);
		Calendar c1 = Calendar.getInstance();
		Calendar c = new GregorianCalendar();

		int dia = c.get(Calendar.DATE);
		int mes = (c.get(Calendar.MONTH)+1);
		int annio = c.get(Calendar.YEAR);
		System.out.println(dia+"/"+mes+"/"+annio);
		
		LocalDate fechaActual=LocalDate.of(annio, mes, dia);
		
		
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
		List<CursoModel> cursoSinEmpezar=new ArrayList();
		for(CursoModel cur: curso) {
			System.out.println(cur.getFechaFin());
			String[]fechaIni=cur.getFechaInicio().split("-");
			LocalDate fechaCurso=LocalDate.of(Integer.parseInt(fechaIni[0]), Integer.parseInt(fechaIni[1]), Integer.parseInt(fechaIni[2]));
			if(fechaCurso.isAfter(fechaActual)) {
				cursoSinEmpezar.add(cur);
			}
		}
		
		return cursoSinEmpezar;
	}
}

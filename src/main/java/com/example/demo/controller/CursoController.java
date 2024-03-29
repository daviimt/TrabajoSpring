package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Matricula;
import com.example.demo.entity.Usuario;
import com.example.demo.models.AlumnoModel;
import com.example.demo.models.CursoModel;
import com.example.demo.models.InscripcionModel;
import com.example.demo.models.MatriculaModel;
import com.example.demo.models.ProfesorModel;
import com.example.demo.repository.MatriculaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.AlumnoService;
import com.example.demo.service.CursoService;
import com.example.demo.service.MatriculaService;
import com.example.demo.service.ProfesorService;

@Controller
@RequestMapping("/cursos")
public class CursoController {
	private static final String COURSES_VIEW = "cursos";
	private static final String COURSES_PROFESOR_VIEW = "cursosProfesor";
	private static final String COURSES_ALUMNO_VIEW = "cursosAlumno";
	private static final String FORM_VIEW = "formCurso";
	private static final String FORM_PROFESOR_VIEW = "formCursoProfesor";
	private static final String INSCRITOS_CURSO = "inscritos";
	private static final String RANKING_CURSOS = "cursosRanking";
	
	@Autowired
	@Qualifier("cursoService")
	private CursoService cursoService;

	@Autowired
	@Qualifier("matriculaService")
	private MatriculaService matriculaService;

	@Autowired
	@Qualifier("profesorService")
	private ProfesorService profesorService;

	@Autowired
	@Qualifier("alumnoService")
	private AlumnoService alumnoService;

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	@Qualifier("matriculaRepository")
	private MatriculaRepository matriculaRepository;

//Método redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/cursos/listCursos");
	}
	
	@GetMapping(value = { "/listCursos", "/listCursos/{id}" })
	public ModelAndView listCursos(@PathVariable(name = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView(COURSES_VIEW);
		if (id == null)
			mav.addObject("cursos", cursoService.ListAllCursos());
		else {
			ProfesorModel profesor = profesorService.findProfesor(id);
			mav.addObject("cursos", profesorService.findCursosByIdProfesor(profesor));
		}
		return mav;
	}
	
//Añadir o actualizar curso
	@PostMapping("/addCurso")
	public String addCurso(@ModelAttribute("curso") CursoModel cursoModel, RedirectAttributes flash) {
		if (cursoModel.getId() == 0) {
			cursoService.addCurso(cursoModel);
			flash.addFlashAttribute("success", "Curso insertado con éxito");
		} else {
			cursoService.updateCurso(cursoModel);
			flash.addFlashAttribute("success", "Curso modificado con éxito");
		}
		return "redirect:/cursos/listCursos";
	}

//Borrar curso
	@GetMapping("/deleteCurso/{id}")
	public String deleteCurso(@PathVariable("id") int id, RedirectAttributes flash) {
		if (cursoService.removeCurso(id) == 0)
			flash.addFlashAttribute("success", "Curso eliminado con éxito");
		else
			flash.addFlashAttribute("error", "No se pudo eliminar el curso");
		return "redirect:/cursos/listCursos";
	}

//Formulario curso
	@GetMapping(value = { "/formCurso", "/formCurso/{id}" })
	public String formCurso(@PathVariable(name = "id", required = false) Integer id, Model model) {

		model.addAttribute("profesores", profesorService.listAllProfesores());
		if (id == null) {
			model.addAttribute("curso", new CursoModel());
		} else {
			model.addAttribute("curso", cursoService.findCurso(id));
		}
		return FORM_VIEW;
	}
	
	@GetMapping(value = { "/listCursosProfesor", "/listCursosProfesor/{id}" })
	public ModelAndView listCursosProfesor(@PathVariable(name = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
		ModelAndView mavError = new ModelAndView("/error/403");

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		
		if (id == null)
			mav.addObject("cursos", cursoService.ListAllCursos());
		else {
			if ((u.getId() + 1) == id) {
				ProfesorModel profesor = profesorService.findProfesor(id);
				mav.addObject("cursos", profesorService.findCursosByIdProfesor(profesor));
			} else {
				return mavError;
			}
		}
		return mav;
	}

//Metodo para insertar y actualizar el curso del profesor
	@GetMapping(value = { "/formCursoProfesor", "/formCursoProfesor/{id}" })
	public String formCursoProfesor(@PathVariable(name = "id", required = false) Integer id, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		model.addAttribute("profesor", profesorService.findProfesor(userDetails.getUsername()));

		if (id == null) {
			model.addAttribute("curso", new CursoModel());
		} else {
			if ((u.getId() + 1) == id) {
				model.addAttribute("curso", cursoService.findCurso(id));				
			} else {
				return "/error/403";
			}
		}
		return FORM_PROFESOR_VIEW;
	}

	
	@PostMapping("/addCursoProfesor")
	public String addCursoProfesor(@ModelAttribute("curso") CursoModel cursoModel, RedirectAttributes flash) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());

		if (cursoModel.getId() == 0) {
			cursoService.addCurso(cursoModel);
			flash.addFlashAttribute("success", "Curso insertado con éxito");
		} else {
			cursoService.updateCurso(cursoModel);
			flash.addFlashAttribute("success", "Curso modificado con éxito");
		}
		return "redirect:/cursos/listCursosProfesor/" + (u.getId() + 1);
	}

	@GetMapping("/listCursosEmail/{email}")
	public String listCursoEmail(@PathVariable(name = "email", required = false) String email, Model model) {
		Usuario u = usuarioRepository.findByUsername(email);
		int id = u.getId() + 1;

		return "redirect:/cursos/listCursosProfesor/" + id;
	}

	@GetMapping("/deleteCursoProfesor/{id}")
	public String deleteCursoProfesor(@PathVariable("id") int id, RedirectAttributes flash) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());

		if (cursoService.removeCurso(id) == 0)
			flash.addFlashAttribute("success", "Curso eliminado con éxito");
		else
			flash.addFlashAttribute("error", "No se pudo eliminar el curso");
		return "redirect:/cursos/listCursosProfesor/" + (u.getId() + 1);
	}

	@GetMapping("/listCursosAlumno")
	public ModelAndView listCursosAlumno() {
		ModelAndView mav = new ModelAndView(COURSES_ALUMNO_VIEW);
		List<CursoModel> cursos = cursoService.ListAllCursos();

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		AlumnoModel alumno = alumnoService.findStudent(u.getId() + 1);
		List<InscripcionModel> listInscrip = cursoService.listInscripcion(alumno,cursos);
		
		mav.addObject("inscripciones", listInscrip);
		mav.addObject("usuarioId", u.getId() + 1);
		
		return mav;
	}
	
	@GetMapping("/inscritos/{id}")
	public ModelAndView inscritosCurso(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(INSCRITOS_CURSO);
		ModelAndView mavError = new ModelAndView("/error/403");
		
		List<CursoModel> cursosAcabados=cursoService.findCursosAcabados();
		List<Matricula> listMatriculas = matriculaRepository.findBycursoId(id);
		List<InscripcionModel> listInscritos=new ArrayList();
		
		CursoModel c=cursoService.findCurso(id);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		
		if ((u.getId() + 1) == c.getIdProfesor() || u.getUsername().equals("admin@admin.com")) {
		
		boolean cond=cursosAcabados.contains(c);
		System.out.println(cond);
		
		for(Matricula m : listMatriculas) {
			AlumnoModel a = alumnoService.findStudent(matriculaService.transform(m).getIdAlumno());
			InscripcionModel ins=new InscripcionModel(c,a,matriculaService.transform(m));
			listInscritos.add(ins);
		}
		mav.addObject("idProfesor", u.getId()+1);
		mav.addObject("inscritos", listInscritos);
		mav.addObject("idCurso", id);
		mav.addObject("finalizado",cond);
		return mav;
		
		} else {
			return mavError;
		}
	}
	
	@GetMapping("/ordenarNotas/{id}")
	public ModelAndView ordenarNotas(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(INSCRITOS_CURSO);
		ModelAndView mavError = new ModelAndView("/error/403");
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		CursoModel c=cursoService.findCurso(id);
		
		if (u.getUsername().equals("admin@admin.com")) {
			
		List<CursoModel> cursosAcabados=cursoService.findCursosAcabados();
		List<Matricula> listMatriculas = matriculaRepository.findBycursoId(id);
		
		List<InscripcionModel> listInscritos=new ArrayList();
		
		
		boolean cond=cursosAcabados.contains(c);
		System.out.println(cond);
		List<Matricula> listMatriculasOrdeadas=listMatriculas.stream().sorted(Comparator.comparing(Matricula::getValoracion).reversed()).collect(Collectors.toList());
		for(Matricula m : listMatriculasOrdeadas) {
			AlumnoModel a = alumnoService.findStudent(matriculaService.transform(m).getIdAlumno());
			InscripcionModel ins=new InscripcionModel(c,a,matriculaService.transform(m));
			listInscritos.add(ins);
		}

		mav.addObject("idProfesor", u.getId()+1);
		mav.addObject("inscritos", listInscritos);
		mav.addObject("idCurso", id);
		mav.addObject("finalizado",cond);
		return mav;
		} else {
			return mavError;
		}
	}
	
	@GetMapping("/cursosRanking")
	public ModelAndView cursosRanking() {
		ModelAndView mav = new ModelAndView(RANKING_CURSOS);
		List<CursoModel> cursos=cursoService.ListAllCursos();
		List<InscripcionModel> listInscritos=new ArrayList();
		
		
		for(CursoModel curso : cursos) {
			List<Matricula> listMatriculas = matriculaRepository.findBycursoId(curso.getId());
			InscripcionModel curs=new InscripcionModel(curso,listMatriculas.size());
			listInscritos.add(curs);
		}
		List<InscripcionModel> listMatriculasOrdeadas=listInscritos.stream().sorted(Comparator.comparing(InscripcionModel::getNumeroMatriculas).reversed()).collect(Collectors.toList());

		mav.addObject("inscritos", listMatriculasOrdeadas);
		return mav;
	}
	
//filtro cursos por FECHAS
	@GetMapping("/filtroCursosAcabados")
	public ModelAndView filtroCursosAcabados() {
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
		List<CursoModel> cus = profesorService.findCursosAcabados();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		mav.addObject("idProfesor", u.getId()+1);
		mav.addObject("cursos", cus);
		return mav;
	}

	@GetMapping("/filtroCursosSinEmpezar")
	public ModelAndView filtroCursosSinEmpezar() {
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
		List<CursoModel> cus = profesorService.findCursosSinEmpezar();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		mav.addObject("idProfesor", u.getId()+1);
		mav.addObject("cursos", cus);
		return mav;
	}

	@GetMapping("/filtroCursosImpartiendose")
	public ModelAndView filtroCursosImpartiendose() {
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
		List<CursoModel> cus = profesorService.findCursosImpartiendose();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		mav.addObject("idProfesor", u.getId()+1);
		mav.addObject("cursos", cus);
		return mav;
	}

	@GetMapping("/filtroCursosFechas")
	public ModelAndView filtroCursosFechas(@ModelAttribute("fechaInicio") String fechaInicio,
			@ModelAttribute("fechaFin") String fechaFin, RedirectAttributes flash) {
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		
		List<CursoModel> cus = profesorService.findCursosFechas(fechaInicio, fechaFin);
		mav.addObject("idProfesor", u.getId()+1);
		mav.addObject("cursos", cus);
		return mav;
	}
	
//filtros cursos por NIVELES
	@GetMapping("/filtroCursosBasicos")
	public ModelAndView filtroCursosBasicos() {
		ModelAndView mav = new ModelAndView(COURSES_ALUMNO_VIEW);
		List<CursoModel> cursos = alumnoService.findCursosBasicos();
		List<MatriculaModel> matr = matriculaService.listAllMatriculas();

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		AlumnoModel alumno = alumnoService.findStudent(u.getId() + 1);

		List<InscripcionModel> listInscrip = cursoService.listInscripcion(alumno,cursos);

		mav.addObject("usuarioId", u.getId() + 1);
		mav.addObject("inscripciones", listInscrip);
		mav.addObject("matriculas", matr);
		return mav;
	}

	@GetMapping("/filtroCursosMedios")
	public ModelAndView filtroCursosMedios() {
		ModelAndView mav = new ModelAndView(COURSES_ALUMNO_VIEW);
		List<CursoModel> cursos = alumnoService.findCursosMedios();
		List<MatriculaModel> matr = matriculaService.listAllMatriculas();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		AlumnoModel alumno = alumnoService.findStudent(u.getId() + 1);

		List<InscripcionModel> listInscrip = cursoService.listInscripcion(alumno,cursos);

		mav.addObject("usuarioId", u.getId() + 1);
		mav.addObject("inscripciones", listInscrip);
		mav.addObject("matriculas", matr);
		return mav;
	}

	@GetMapping("/filtroCursosAvanzados")
	public ModelAndView filtroCursosAvanzados() {
		ModelAndView mav = new ModelAndView(COURSES_ALUMNO_VIEW);
		List<CursoModel> cursos = alumnoService.findCursosAvanzados();
		List<MatriculaModel> matr = matriculaService.listAllMatriculas();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		AlumnoModel alumno = alumnoService.findStudent(u.getId() + 1);

		List<InscripcionModel> listInscrip = cursoService.listInscripcion(alumno,cursos);

		mav.addObject("usuarioId", u.getId() + 1);
		mav.addObject("inscripciones", listInscrip);
		mav.addObject("matriculas", matr);
		return mav;
	}

}

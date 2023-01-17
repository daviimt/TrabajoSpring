package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

import com.example.demo.entity.Alumno;
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

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
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

	@GetMapping(value = { "/listCursosProfesor", "/listCursosProfesor/{id}" })
	public ModelAndView listCursosProfesor(@PathVariable(name = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);

		if (id == null)
			mav.addObject("cursos", cursoService.ListAllCursos());
		else {
			ProfesorModel profesor = profesorService.findProfesor(id);
			mav.addObject("cursos", profesorService.findCursosByIdProfesor(profesor));
		}
		return mav;
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

	// metodo para insertar y actualizar el curso del profesor
	@GetMapping(value = { "/formCursoProfesor", "/formCursoProfesor/{id}" })
	public String formCursoProfesor(@PathVariable(name = "id", required = false) Integer id, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("profesor", profesorService.findProfesor(userDetails.getUsername()));

		if (id == null) {
			model.addAttribute("curso", new CursoModel());
		} else {
			model.addAttribute("curso", cursoService.findCurso(id));
		}
		return FORM_PROFESOR_VIEW;
	}

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

	// Metodo de borrar
	@GetMapping("/deleteCurso/{id}")
	public String deleteCurso(@PathVariable("id") int id, RedirectAttributes flash) {
		if (cursoService.removeCurso(id) == 0)
			flash.addFlashAttribute("success", "Curso eliminado con éxito");
		else
			flash.addFlashAttribute("error", "No se pudo eliminar el curso");
		return "redirect:/cursos/listCursos";
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

	@GetMapping("/inscritos/{id}")
	public ModelAndView inscritosCurso(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(INSCRITOS_CURSO);
		List<CursoModel> cursosAcabados=cursoService.findCursosAcabados();
		List<Matricula> listMatriculas = matriculaRepository.findBycursoId(id);
		List<AlumnoModel> listAlumnos = new ArrayList();
		CursoModel c=cursoService.findCurso(id);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		
		boolean cond=cursosAcabados.contains(c);
		System.out.println(cond);
		
		for(Matricula m : listMatriculas) {
			AlumnoModel a = alumnoService.findStudent(matriculaService.transform(m).getIdAlumno());
			listAlumnos.add(a);
		}
		mav.addObject("idProfesor", u.getId()+1);
		mav.addObject("alumnos", listAlumnos);
		mav.addObject("idCurso", id);
		mav.addObject("finalizado",cond);
		return mav;
	}
	
	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/cursos/listCursos");
	}

	@GetMapping("/filtroCursosAcabados")
	public ModelAndView filtroCursosAcabadors() {
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
		List<CursoModel> cus = profesorService.findCursosAcabados();
		mav.addObject("cursos", cus);
		return mav;
	}

	@GetMapping("/filtroCursosSinEmpezar")
	public ModelAndView filtroCursosSinEmpezar() {
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
		List<CursoModel> cus = profesorService.findCursosSinEmpezar();
		mav.addObject("cursos", cus);
		return mav;
	}

	@GetMapping("/filtroCursosImpartiendose")
	public ModelAndView filtroCursosImpartiendose() {
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
		List<CursoModel> cus = profesorService.findCursosImpartiendose();
		mav.addObject("cursos", cus);
		return mav;
	}

	@GetMapping("/filtroCursosFechas")
	public ModelAndView filtroCursosFechas(@ModelAttribute("fechaInicio") String fechaInicio,
			@ModelAttribute("fechaFin") String fechaFin, RedirectAttributes flash) {
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
		
		List<CursoModel> cus = profesorService.findCursosFechas(fechaInicio, fechaFin);

		mav.addObject("cursos", cus);
		return mav;
	}

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

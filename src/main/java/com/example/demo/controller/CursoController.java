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

import com.example.demo.entity.Usuario;
import com.example.demo.models.CursoModel;
import com.example.demo.models.ProfesorModel;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.CursoService;
import com.example.demo.service.ProfesorService;

@Controller
@RequestMapping("/cursos")
public class CursoController {
	private static final String COURSES_VIEW = "cursos";
	private static final String COURSES_PROFESOR_VIEW = "cursosProfesor";
	private static final String FORM_VIEW = "formCurso";
	private static final String FORM_PROFESOR_VIEW = "formCursoProfesor";
	
	@Autowired
	@Qualifier("cursoService")
	private CursoService cursoService;

	@Autowired
	@Qualifier("profesorService")
	private ProfesorService profesorService;
	
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;


//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value={"/listCursos", "/listCursos/{id}"})
	public ModelAndView listCursos(@PathVariable(name = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView(COURSES_VIEW);
		if(id==null)
			mav.addObject("cursos", cursoService.ListAllCursos());
		else {
			ProfesorModel profesor=profesorService.findProfesor(id);
			mav.addObject("cursos", profesorService.findCursosByIdProfesor(profesor));
		}
		return mav;
	}
	
	@GetMapping(value={"/listCursosProfesor", "/listCursosProfesor/{id}"})
	public ModelAndView listCursosProfesor(@PathVariable(name = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView(COURSES_PROFESOR_VIEW);
	
		if(id==null)
			mav.addObject("cursos", cursoService.ListAllCursos());
		else {
			ProfesorModel profesor=profesorService.findProfesor(id);
			System.out.println(profesor);
			mav.addObject("cursos", profesorService.findCursosByIdProfesor(profesor));
		}
		return mav;
	}


	@GetMapping(value = { "/formCurso", "/formCurso/{id}" })
	public String formCurso(@PathVariable(name = "id", required = false) Integer id, Model model) {
		
		model.addAttribute("profesores", profesorService.listAllProfesores());
		if (id == null) {
			model.addAttribute("curso", new CursoModel());
		}else {
			model.addAttribute("curso", cursoService.findCurso(id));
		}
		return FORM_VIEW;
	}
	
	//metodo para insertar y actualizar el curso del profesor
	@GetMapping(value = {"/formCursoProfesor","/formCursoProfesor/{id}" })
	public String formCursoProfesor(@PathVariable(name = "id", required = false) Integer id, Model model) {
		UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("profesor", profesorService.findProfesor(userDetails.getUsername()));
		
		if (id == null) {
			model.addAttribute("curso", new CursoModel());
		}else {
			model.addAttribute("curso", cursoService.findCurso(id));
		}
		return FORM_PROFESOR_VIEW;
	}
	
	@PostMapping("/addCurso")
	public String addCurso(@ModelAttribute("curso") CursoModel cursoModel, 
			RedirectAttributes flash) {
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
	public String addCursoProfesor(@ModelAttribute("curso") CursoModel cursoModel, 
			RedirectAttributes flash) {
		UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u=usuarioRepository.findByUsername(userDetails.getUsername());
		
		if (cursoModel.getId() == 0) {
			cursoService.addCurso(cursoModel);
			flash.addFlashAttribute("success", "Curso insertado con éxito");
		} else {
			cursoService.updateCurso(cursoModel);
			flash.addFlashAttribute("success", "Curso modificado con éxito");
		}
		return "redirect:/cursos/listCursosProfesor/"+(u.getId()+1);
	}
	
	@GetMapping("/listCursosEmail/{email}")
	public String listCursoEmail(@PathVariable(name = "email", required = false) String email, Model model) {
			Usuario u=usuarioRepository.findByUsername(email);
			int id=u.getId()+1;
			
		return "redirect:/cursos/listCursosProfesor/"+id;
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
		UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u=usuarioRepository.findByUsername(userDetails.getUsername());
		
		if (cursoService.removeCurso(id) == 0)
			flash.addFlashAttribute("success", "Curso eliminado con éxito");
		else
			flash.addFlashAttribute("error", "No se pudo eliminar el curso");
		return "redirect:/cursos/listCursosProfesor/"+(u.getId()+1);
	}
	
	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/cursos/listCursos");
	}
	
	@GetMapping(value={"/filtroCurso/{option}"})
	public ModelAndView filtroCurso(@PathVariable("option") int option, RedirectAttributes flash) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		ProfesorModel profesor = profesorService.findProfesor(u.getId()+1);
		List<CursoModel> curso = profesorService.findCursosByIdProfesor(profesor);
		System.out.println("controller filtro curso");
		System.out.println(curso);
		Calendar c1 = Calendar.getInstance();
		Calendar c = new GregorianCalendar();

		int dia = c.get(Calendar.DATE);
		int mes = (c.get(Calendar.MONTH)+1);
		int annio = c.get(Calendar.YEAR);
		System.out.println(dia+"/"+mes+"/"+annio);
		
		LocalDate fechaActual=LocalDate.of(annio, mes, dia);
		
		if (option == 0) {
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
			
			System.out.println(cursoAcabado);
			mav.addObject("cursos", cursoAcabado);
			return mav; 

		} else if (option == 1) {
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
			
			System.out.println(cursoSinEmpezar);
			mav.addObject("cursos", cursoSinEmpezar);
			return mav; 

		} else {
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
			
			System.out.println(cursosImpartiendose);
			mav.addObject("cursos", cursosImpartiendose);
			return mav; 
		}

	}
}

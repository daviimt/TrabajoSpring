package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.models.AlumnoModel;
import com.example.demo.service.AlumnoService;
import com.example.demo.service.CursoService;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {
	// Constante Cadena con el nombre de la vista
	private static final String STUDENTS_VIEW = "alumnos";
	private static final String FORM_VIEW = "formAlumno";
	// Inyectamos el servicio
	@Autowired
	@Qualifier("alumnoService")
	private AlumnoService alumnoService;

	@Autowired
	@Qualifier("cursoService")
	private CursoService courseService;

	// Metodo para listar alumnos
	@GetMapping("/listAlumnos")
	public ModelAndView listAlumnos() {
		ModelAndView mav = new ModelAndView(STUDENTS_VIEW);
		mav.addObject("alumnos", alumnoService.ListAllAlumnos());
		return mav;
	}

	// Metodo para borrar
	@GetMapping("/deleteAlumno/{idAlumno}")
	public String removeCurso(@PathVariable("idAlumno") int id, RedirectAttributes flash) {
		if (alumnoService.removeAlumno(id) == 0) {
			flash.addFlashAttribute("success", "Alumno eliminado con éxito");
		} else
			flash.addFlashAttribute("error", "No se ha podido eliminar el alumno");
		return "redirect:/alumnos/listAlumnos";
	}
	
	@PostMapping("/addAlumno")
	public String addAlumno(@Valid @ModelAttribute("alumno") AlumnoModel studentModel, BindingResult bindingResult,
			RedirectAttributes flash, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("curso", courseService.ListAllCursos());
			return FORM_VIEW;
		} else {
			alumnoService.updateAlumno(studentModel);
			flash.addFlashAttribute("success", "Alumno actualizado satisfactoriamente");
			return "redirect:/alumnos/listAlumnos";
		}
	}
	@PreAuthorize("hasAuthority('ROL_ALUMNO')") 
	@GetMapping("/formAlumno/{idAlumno}")
	public String formAlumno(@PathVariable(name = "idAlumno", required = false) Integer id, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); 
		AlumnoModel a = alumnoService.findStudent(username);
		if(id == a.getIdAlumno()) {
			model.addAttribute("alumno",alumnoService.findStudent(id));
		}
		return FORM_VIEW;
	}
	
	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/alumnos/listAlumnos");
	}
}


package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.example.demo.serviceImpl.UsuarioService;

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
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	// Metodo para listar alumnos
	@GetMapping("/listAlumnos")
	public ModelAndView listAlumnos() {
		ModelAndView mav = new ModelAndView(STUDENTS_VIEW);
		mav.addObject("alumnos", alumnoService.ListAllAlumnos());
		return mav;
	}

	// Metodo para borrar
	@GetMapping("/deleteAlumno/{id}")
	public String removeCurso(@PathVariable("id") int id, RedirectAttributes flash) {
		AlumnoModel a = alumnoService.findStudent(id);
		if (alumnoService.removeAlumno(id) == 0) {
			try {
				usuarioService.deleteUser(a.getEmail());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			return "redirect:/home";
		}
	}
	
	@GetMapping("/formAlumno/{id}")
	public String formCurso(@PathVariable(name = "id", required = false) Integer id, Model model) {
			model.addAttribute("alumno", alumnoService.findStudent(id));
		return FORM_VIEW;
	}
	
	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/alumnos/listAlumnos");
	}
}


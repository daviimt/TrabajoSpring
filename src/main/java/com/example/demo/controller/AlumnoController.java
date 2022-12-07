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

import com.example.demo.models.AlumnoModel;
import com.example.demo.service.AlumnoService;
import com.example.demo.service.CursoService;

@Controller
@RequestMapping("/students")
public class AlumnoController {
	// Constante Cadena con el nombre de la vista
	private static final String STUDENTS_VIEW = "alumno";
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
		mav.addObject("students", alumnoService.ListAllAlumnos());
		return mav;
	}

	// Metodo para borrar
	@PostMapping("/deleteAlumno/{id}")
	public String removeCurso(@PathVariable("id") int id, RedirectAttributes flash) {
		if (alumnoService.removeAlumno(id) == 0) {
			flash.addFlashAttribute("success", "Alumno eliminado con éxito");
		} else
			flash.addFlashAttribute("error", "No se ha podido eliminar el alumno");
		return "redirect:/students/listStudent";
	}
	
	@PostMapping("/addAlumno")
	public String addAlumno(@Valid @ModelAttribute("student") AlumnoModel studentModel, BindingResult bindingResult,
			RedirectAttributes flash, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("curso", courseService.ListAllCurso());
			return FORM_VIEW;
		} else {
			alumnoService.updateAlumno(studentModel);
			flash.addFlashAttribute("success", "Alumno actualizado satisfactoriamente");
			return "redirect:/student/liststudent";
		}
	}
	
	@GetMapping("/formAlumno/{id}")
	public String formCurso(@PathVariable(name = "id", required = false) Integer id, Model model) {

		model.addAttribute("curso", courseService.ListAllCurso());
		if (id == null) {
			model.addAttribute("alumno", new AlumnoModel());
		} else {
			model.addAttribute("alumno", alumnoService.findStudent(id));
		}
		return FORM_VIEW;
	}
}


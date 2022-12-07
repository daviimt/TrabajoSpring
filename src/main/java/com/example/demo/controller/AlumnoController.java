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
	private static final String STUDENTS_VIEW = "students";
	private static final String FORM_VIEW = "formStudent";

	// Inyectamos el servicio
	@Autowired
	@Qualifier("studentService")
	private AlumnoService alumnoService;

	@Autowired
	@Qualifier("courseService")
	private CursoService courseService;

	// Metodo para listar alumnos
	@GetMapping("/listStudents")
	public ModelAndView listStudents() {
		ModelAndView mav = new ModelAndView(STUDENTS_VIEW);
		mav.addObject("students", alumnoService.ListAllAlumnos());
		return mav;
	}

	// Metodo para borrar
	@PostMapping("/deleteStudent/{id}")
	public String removeCourse(@PathVariable("id") int id, RedirectAttributes flash) {
		if (alumnoService.removeAlumno(id) == 0) {
			flash.addFlashAttribute("success", "Estudiante eliminado con éxito");
		} else
			flash.addFlashAttribute("error", "No se ha podido eliminar el estudiante");
		return "redirect:/students/listStudent";
	}
	
	@PostMapping("/addStudent")
	public String addStudent(@Valid @ModelAttribute("student") AlumnoModel studentModel, BindingResult bindingResult,
			RedirectAttributes flash, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("courses", courseService.ListAllCurso());
			return FORM_VIEW;
		} else {
			alumnoService.updateAlumno(studentModel);
			flash.addFlashAttribute("success", "Estudiante actualizado satisfactoriamente");
			return "redirect:/student/liststudent";
		}
	}
	
	@GetMapping("/formStudent/{id}")
	public String formCourse(@PathVariable(name = "id", required = false) Integer id, Model model) {

		model.addAttribute("courses", courseService.ListAllCurso());
		if (id == null) {
			model.addAttribute("student", new AlumnoModel());
		} else {
			model.addAttribute("student", alumnoService.findStudent(id));
		}
		return FORM_VIEW;
	}
}


package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.example.demo.models.CursoModel;
import com.example.demo.service.CursoService;

@Controller
@RequestMapping("/courses")
public class CursoController {
	private static final String COURSES_VIEW = "courses";
	private static final String FORM_VIEW = "formCourse";

	@Autowired
	@Qualifier("cursoService")
	private CursoService cursoService;

	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/courses/listCourses");
	}

//	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/listCourses")
	public ModelAndView listCourses() {
		//UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//String userName = userDetails.getUsername();
		ModelAndView mav = new ModelAndView(COURSES_VIEW);
		mav.addObject("courses", cursoService.ListAllCurso());
		//mav.addObject("usuario", userName);
		return mav;
	}

	// Generalmente cuando se hace alguna operacion POST con algun registro
	// Se muestra un mensaje de la operacion y se redirecciona al listado
	@PostMapping("/addCourse")
	public String addCourse(@ModelAttribute("course") CursoModel cursoModel, RedirectAttributes flash) {

		if (cursoModel.getIdCurso() == 0) {
			cursoService.addCurso(cursoModel);
			flash.addFlashAttribute("succes", "Curso insertado con éxito");

		} else {
			cursoService.updateCurso(cursoModel);
			flash.addFlashAttribute("succes", "Curso modificado con éxito");
		}
		return "redirect:/courses/listCourses";
	}

	// Metodo de borrar
	@PostMapping("/deleteCourse/{id}")
	public String deleteCourse(@PathVariable("id") int id, RedirectAttributes flash) {

		if (cursoService.removeCurso(id) == 0)
			flash.addFlashAttribute("succes", "Curso eliminado con éxito");
		else
			flash.addFlashAttribute("error", "no se pudo eliminar el curso");
		return "redirect:/courses/listCourses";
	}

	@GetMapping(value = { "/formCourse", "/formCourse/{id}" })
	public String formCourse(@PathVariable(name = "id", required = false) Integer id, Model model) {

		if (id == null)
			model.addAttribute("course", new CursoModel());
		else
			model.addAttribute("course", cursoService.findCurso(id));
		return FORM_VIEW;
	}

}

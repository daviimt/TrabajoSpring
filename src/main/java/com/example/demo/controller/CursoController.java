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
	private static final String COURSES_VIEW = "curso";
	private static final String FORM_VIEW = "formCurso";

	@Autowired
	@Qualifier("cursoService")
	private CursoService cursoService;

	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/courses/listCurso");
	}

//	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/listCursos")
	public ModelAndView listCurso() {
		ModelAndView mav = new ModelAndView(COURSES_VIEW);
		mav.addObject("courses", cursoService.ListAllCurso());
		return mav;
	}

	// Generalmente cuando se hace alguna operacion POST con algun registro
	// Se muestra un mensaje de la operacion y se redirecciona al listado
	@PostMapping("/addCurso")
	public String addCurso(@ModelAttribute("curso") CursoModel cursoModel, RedirectAttributes flash) {

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
	@PostMapping("/deleteCurso/{id}")
	public String deleteCurso(@PathVariable("id") int id, RedirectAttributes flash) {

		if (cursoService.removeCurso(id) == 0)
			flash.addFlashAttribute("succes", "Curso eliminado con éxito");
		else
			flash.addFlashAttribute("error", "No se pudo eliminar el curso");
		return "redirect:/courses/listCursos";
	}

	@GetMapping(value = { "/formCurso", "/formCurso/{id}" })
	public String formCurso(@PathVariable(name = "id", required = false) Integer id, Model model) {

		if (id == null)
			model.addAttribute("curso", new CursoModel());
		else
			model.addAttribute("curso", cursoService.findCurso(id));
		return FORM_VIEW;
	}

}

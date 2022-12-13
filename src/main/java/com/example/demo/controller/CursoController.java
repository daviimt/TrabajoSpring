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
import com.example.demo.service.ProfesorService;

@Controller
@RequestMapping("/cursos")
public class CursoController {
	private static final String COURSES_VIEW = "cursos";
	private static final String FORM_VIEW = "formCurso";

	@Autowired
	@Qualifier("cursoService")
	private CursoService cursoService;

	@Autowired
	@Qualifier("profesorService")
	private ProfesorService profesorService;

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value={"/listCursos", "/listCursos/{id}"})
	public ModelAndView listCursos(@PathVariable(name = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView(COURSES_VIEW);
		if(id==null)
			mav.addObject("cursos", cursoService.ListAllCursos());
		else
			mav.addObject("cursos", cursoService.findByIdProfesor(id.intValue()));
		return mav;
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
	
	
	// Metodo de borrar
	@GetMapping("/deleteCurso/{id}")
	public String deleteCurso(@PathVariable("id") int id, RedirectAttributes flash) {
		if (cursoService.removeCurso(id) == 0)
			flash.addFlashAttribute("success", "Curso eliminado con éxito");
		else
			flash.addFlashAttribute("error", "No se pudo eliminar el curso");
		return "redirect:/cursos/listCursos";
	}
	
	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/cursos/listCursos");
	}
}

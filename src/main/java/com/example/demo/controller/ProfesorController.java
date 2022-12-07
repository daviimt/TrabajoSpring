package com.example.demo.controller;

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

import com.example.demo.models.ProfesorModel;
import com.example.demo.service.ProfesorService;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {
	private static final String PROFESOR_VIEW = "profesor";
	private static final String FORM_VIEW = "formProfesor";
	
	//Inyectamos el servicio de profesor
	@Autowired
	@Qualifier("profesorService")
	private ProfesorService profesorService;
	
	//Mostrar profesores
	@GetMapping("/listProfesores")
	public ModelAndView listProfesores() {
		ModelAndView mav = new ModelAndView(PROFESOR_VIEW);
		mav.addObject("profesores", profesorService.listAllProfesores());
		return mav;
	}
	
	//Metodo de borrar 
	@PostMapping("/deleteProfesor/{id}")
	public String removeProfesor(@PathVariable("id")int id, RedirectAttributes flash) {
		if(profesorService.removeProfesor(id)==0) {
			flash.addFlashAttribute("success","Profesor eliminado con éxito");	
		}else
			flash.addFlashAttribute("error","No se ha podido eliminar el profesor");	
		return "redirect:/profesores/listProfesores";
	}
	//Formulario
	@GetMapping({"/formProfesor","/formProfesor/{id}"})
	public String formProfesor(@PathVariable(name="id",required=false) Integer id,Model model){
		if(id==null) {
			model.addAttribute("profesor",new ProfesorModel());
		}else {
			model.addAttribute("profesor",profesorService.findProfesor(id));
		}
		return FORM_VIEW;
	}
	//Metodo add / update Profesor
	@PostMapping("/addProfesor")
	public String addProfesor(@ModelAttribute("profesor") ProfesorModel profesorModel,
			BindingResult bindingResult, RedirectAttributes flash, Model model) 
	{
		if(bindingResult.hasErrors()) {
			return FORM_VIEW;
		}
		else {
			if(profesorModel.getId()==0) {
				profesorService.addProfesor(profesorModel);
				flash.addFlashAttribute("success", "Profesor insertado con éxito");
			}else {
				profesorService.updateProfesor(profesorModel);
				flash.addFlashAttribute("success", "Profesor modificado con éxito");
			}
			return "redirect:/profesores/listProfesores";
		}
	}
}

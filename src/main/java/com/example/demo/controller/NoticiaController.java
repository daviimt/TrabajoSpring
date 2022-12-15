package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.demo.models.NoticiaModel;
import com.example.demo.models.ProfesorModel;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.NoticiaService;
import com.example.demo.service.ProfesorService;
import com.example.demo.serviceImpl.UsuarioService;

@Controller
@RequestMapping("/noticias")
public class NoticiaController {
	private static final String NOTICIAS_VIEW = "noticias";
	private static final String FORM_VIEW = "formNoticia";
	
	//Inyectamos el servicio
	@Autowired
	@Qualifier("noticiaService")
	private NoticiaService noticiaService;
	
	//Mostrar noticias
	@GetMapping("/listNoticias")
	public ModelAndView listNoticias() {
		ModelAndView mav = new ModelAndView(NOTICIAS_VIEW);
		mav.addObject("noticias", noticiaService.ListAllNoticias());
		return mav;
	}
	
	//Metodo add / update noticias
	@PostMapping("/addNoticias")
	public String addNoticias(@ModelAttribute("noticia") NoticiaModel noticiaModel,
			 RedirectAttributes flash) {
			if(noticiaModel.getId()==0) {
					noticiaService.addNoticia(noticiaModel);
					flash.addFlashAttribute("success","Noticia creada con éxito");	
			}else {
				noticiaService.updateNoticia(noticiaModel);
				flash.addFlashAttribute("success", "Noticia modificada con éxito");
			}
			return "redirect:/noticias/listNoticias";
	}
	
	//Formulario
	@GetMapping(value={"/formNoticia","/formNoticia/{id}"})
	public String formNoticias(@PathVariable(name="id",required=false) Integer id,Model model){
		if(id==null) {
			model.addAttribute("noticia",new NoticiaModel());
		}else {
			model.addAttribute("noticia", noticiaService.findNoticia(id));
		}
		return FORM_VIEW;
	}

	//Metodo de borrar 
	@GetMapping("/deleteNoticias/{id}")
	public String deleteNoticias(@PathVariable("id")int id, RedirectAttributes flash) {
		if(noticiaService.removeNoticia(id)==0) {
			flash.addFlashAttribute("success","Noticia eliminada con éxito");	
		}else
			flash.addFlashAttribute("error","No se ha podido eliminar la noticia");	
		
		return "redirect:/noticias/listNoticias";
	}
	
	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/noticias");
	}
}

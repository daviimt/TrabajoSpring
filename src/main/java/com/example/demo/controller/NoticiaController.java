package com.example.demo.controller;




import java.io.File;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Usuario;
import com.example.demo.models.NoticiaModel;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.NoticiaService;
import com.example.demo.upload.FileSystemStorageService;
import com.example.demo.upload.StorageService;

@Controller
@RequestMapping("/noticias")
public class NoticiaController {
	private static final String NOTICIAS_VIEW = "noticias";
	private static final String FORM_VIEW = "formNoticia";
	private static final String NOTICIAS_ALUM_VIEW = "noticiasAlumnos";
	
	//Inyectamos el servicio
	@Autowired
	@Qualifier("noticiaService")
	private NoticiaService noticiaService;
	
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	@Qualifier("storageService")
	private StorageService storageService;
	//Mostrar noticias
	@GetMapping("/listNoticias")
	public ModelAndView listNoticias() {
		ModelAndView mav = new ModelAndView(NOTICIAS_VIEW);
		mav.addObject("noticias", noticiaService.ListAllNoticias());
		return mav;
	}
	
	@GetMapping("/listNoticiasAlumnos")
	public ModelAndView listNoticiasAlumnos() {
		ModelAndView mav = new ModelAndView(NOTICIAS_ALUM_VIEW);
		mav.addObject("noticias", noticiaService.ListAllNoticias());
		return mav;
	}
	
	//Metodo add / update noticias
	@PostMapping("/addNoticia")
	public String addNoticias(@Valid @ModelAttribute("noticia") NoticiaModel noticiaModel,Model model,
			 RedirectAttributes flash,BindingResult bindingResult,@RequestParam("file")MultipartFile file) {
		
		UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u=usuarioRepository.findByUsername(userDetails.getUsername());
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("noticias",noticiaService.ListAllNoticias());
			return FORM_VIEW;
			
		}else {
			String imagen=storageService.store(file,noticiaModel.getId());
			noticiaModel.setImagen(imagen);
			
			if(noticiaModel.getId()==0) {
				noticiaModel.setUsuarioId(u.getId());
				noticiaService.addNoticia(noticiaModel);
				flash.addFlashAttribute("success","Noticia creada con éxito");	
			}else {
				noticiaService.updateNoticia(noticiaModel);
				flash.addFlashAttribute("success", "Noticia modificada con éxito");
			}
			return "redirect:/noticias/listNoticias";
		
		}
	
	}
	
	//Formulario
	@GetMapping(value={"/formNoticia","/formNoticia/{id}"})
	public String formNoticias(@PathVariable(name="id",required=false) Integer id,Model model){
		if(id==null) {
			model.addAttribute("noticia",new NoticiaModel());
		}else {
			File foto=new File("http://localhost:8080/images/"+noticiaService.findNoticia(id).getImagen());
			System.out.println(noticiaService.findNoticia(id).getImagen());
			model.addAttribute("noticia", noticiaService.findNoticia(id));
		}
		return FORM_VIEW;
	}

	//Metodo de borrar 
	@GetMapping("/deleteNoticia/{id}")
	public String deleteNoticias(@PathVariable("id")int id, RedirectAttributes flash) {
		File foto=new File("http://localhost:8080/images/"+noticiaService.findNoticia(id).getImagen());
		System.out.println(noticiaService.findNoticia(id).getImagen());
		if(foto.exists()) {
			System.out.println(foto.getAbsolutePath());
			foto.delete();
		}
		if(noticiaService.removeNoticia(id)==0) {
			flash.addFlashAttribute("success","Noticia eliminada con éxito");	
		}else
			flash.addFlashAttribute("error","No se ha podido eliminar la noticia");	
		
		return "redirect:/noticias/listNoticias";
	}
	
	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/noticias/listNoticias");
	}
	
//	@GetMapping("/files/{filename:.+}")
//	@ResponseBody
//	public ResponseEntity<Resource>serveFile(@PathVariable String filename){
//		Resource file=storageService.loadAsResource(filename);
//		return ResponseEntity.ok().body(file);
//	}
}

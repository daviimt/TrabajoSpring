package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Usuario;
import com.example.demo.models.ComentarioModel;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.ComentarioService;

@Controller 
@RequestMapping("/comentario")
public class ComentarioController {
	@Autowired()
	@Qualifier("comentarioService")
	private ComentarioService comentarioService;
	
	@Autowired()
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;
	
	private static final String FORM_COMENTARIO = "formComentario";
	
	@PostMapping("/addComentario")
	public String addComentario(@ModelAttribute("comentario") ComentarioModel comentarioModel, RedirectAttributes flash) {
			comentarioService.addComentario(comentarioModel);
			flash.addFlashAttribute("success", "Comentario insertado con éxito");
		return "redirect:/cursos/listCursosAlumnos";
	}
	
	@GetMapping("/formComentario/{idCurso}")
	public String formComentario(@PathVariable(name = "idCurso", required = true) Integer idCurso, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		
		ComentarioModel comentario=new ComentarioModel();
		comentario.setIdCurso(idCurso);
		comentario.setIdAlumno((u.getId()+1));
		model.addAttribute("comentario", comentario);
		
		return FORM_COMENTARIO;
	}
}

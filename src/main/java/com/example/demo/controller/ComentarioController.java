package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Usuario;
import com.example.demo.models.AlumnoModel;
import com.example.demo.models.ComentarioModel;
import com.example.demo.models.CursoModel;
import com.example.demo.models.InscripcionModel;
import com.example.demo.models.ResenyaModel;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.AlumnoService;
import com.example.demo.service.ComentarioService;
import com.example.demo.service.CursoService;

@Controller 
@RequestMapping("/comentario")
public class ComentarioController {
	@Autowired()
	@Qualifier("comentarioService")
	private ComentarioService comentarioService;
	
	@Autowired()
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;
	
	@Autowired()
	@Qualifier("cursoService")
	private CursoService cursoService;
	
	@Autowired()
	@Qualifier("alumnoService")
	private AlumnoService alumnoService;
	
	private static final String FORM_COMENTARIO = "formComentario";
	
	@PostMapping("/addComentario")
	public String addComentario(@ModelAttribute("comentario") ComentarioModel comentarioModel, RedirectAttributes flash) {
			comentarioService.addComentario(comentarioModel);
			flash.addFlashAttribute("success", "Comentario insertado con éxito");
		return "redirect:/cursos/listCursosAlumno";
	}
	
	@GetMapping("/formComentario/{idCurso}")
	public ModelAndView formComentario(@PathVariable(name = "idCurso", required = true) Integer idCurso, Model model) {
		ModelAndView mav = new ModelAndView(FORM_COMENTARIO);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());
		ComentarioModel comentario=new ComentarioModel();
		comentario.setIdCurso(idCurso);
		comentario.setIdAlumno((u.getId()+1));
		AlumnoModel alumno=alumnoService.findStudent(comentario.getIdAlumno());
		List<CursoModel>cursos=cursoService.ListAllCursos();
		
		List<InscripcionModel> inscritos =cursoService.listInscripcion(alumno, cursos);
		List<ComentarioModel>comentarios=comentarioService.ListComentarioCurso(idCurso);
		List<ResenyaModel> resenyas=new ArrayList();
		
		boolean inscrito=false;
		boolean finalizado=false;
		for(InscripcionModel insc:inscritos) {
			inscrito=false;
			if(idCurso==insc.getCurso().getId() && (u.getId()+1)==insc.getAlumno().getId() && insc.isFinalizado()){
				inscrito=true;
				finalizado=true;
			}
		}
		
		for(ComentarioModel co:comentarios) {
			ResenyaModel rm=new ResenyaModel(co,alumnoService.findStudent(comentario.getIdAlumno()));
			resenyas.add(rm);
		}

		mav.addObject("inscrito",inscrito);
		mav.addObject("finalizado",finalizado);
		mav.addObject("resenyas",resenyas);
		
		model.addAttribute("comentario", comentario);
		
		return mav;
	}
}

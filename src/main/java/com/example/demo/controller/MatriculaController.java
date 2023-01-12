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

import com.example.demo.entity.Matricula;
import com.example.demo.entity.Noticia;
import com.example.demo.entity.Usuario;
import com.example.demo.models.CursoModel;
import com.example.demo.models.MatriculaModel;
import com.example.demo.models.NoticiaModel;
import com.example.demo.repository.NoticiaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.CursoService;
import com.example.demo.service.MatriculaService;
import com.example.demo.service.NoticiaService;
import com.example.demo.upload.FileSystemStorageService;
import com.example.demo.upload.StorageService;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

	@Autowired
	@Qualifier("cursoService")
	private CursoService cursoService;
	// Inyectamos el servicio
	@Autowired
	@Qualifier("matriculaService")
	private MatriculaService matriculaService;

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	@Autowired
	@Qualifier("noticiaRepository")
	private NoticiaRepository noticiaRepository;

	@Autowired
	@Qualifier("storageService")
	private StorageService storageService;

	
	// Metodo add / update noticias
	@GetMapping("/addMatricula/{cursoId}")
	public String addNoticias(@PathVariable("cursoId") int cursoId, RedirectAttributes flash) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());

		MatriculaModel matricula = new MatriculaModel();
		matricula.setIdAlumno(u.getId()+1);
		matricula.setIdCurso(cursoId);
		matricula.setValoracion(0);
		
		matriculaService.addMatricula(matricula);
		flash.addFlashAttribute("success", "Matrícula creada con éxito");
		
		return "redirect:/cursos/listCursosAlumno";

	}
	
	@PostMapping("/calificar/{idCurso}/{idAlumno}")
	public String calificar(@PathVariable(name = "idCurso", required = true) Integer idCurso,@PathVariable(name = "idAlumno", required = true) Integer idAlumno,@ModelAttribute("valoracion") int valoracion, RedirectAttributes flash) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());

		MatriculaModel m=matriculaService.findMatricula(idCurso, idAlumno);
		m.setValoracion(valoracion);
		matriculaService.updateMatricula(m);
		flash.addFlashAttribute("success", "Nota insertada con éxito");
		
		return "redirect:/cursos/listCursosProfesor/";
	}
}

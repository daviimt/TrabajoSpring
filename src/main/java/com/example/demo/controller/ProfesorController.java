package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Usuario;
import com.example.demo.models.ProfesorModel;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.ProfesorService;
import com.example.demo.serviceImpl.UsuarioService;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {
	private static final String PROFESOR_VIEW = "profesores";
	private static final String FORM_VIEW = "formProfesor";

	// Inyectamos el servicio de profesor
	@Autowired
	@Qualifier("profesorService")
	private ProfesorService profesorService;

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	// Mostrar profesores
	@GetMapping("/listProfesores")
	public ModelAndView listProfesores() {
		ModelAndView mav = new ModelAndView(PROFESOR_VIEW);
		mav.addObject("profesores", profesorService.listAllProfesores());
		return mav;
	}

	// Metodo add / update Profesor
	@PostMapping("/addProfesor")
	public String addProfesor(@ModelAttribute("profesor") ProfesorModel profesorModel, RedirectAttributes flash) {
		if (profesorModel.getId() == 0) {
			Usuario user = new Usuario();
			user.setUsername(profesorModel.getEmail());
			user.setPassword(profesorModel.getPassword());
			user.setRole("ROL_PROFESOR");
			Usuario userExist = usuarioService.registrar(user);
			if (userExist != null) {
				profesorModel.setUsuario(user);
				profesorService.addProfesor(profesorModel);
				flash.addFlashAttribute("success", "Profesor creado con éxito");
			} else {
				return "redirect:/profesores/formProfesor?error";
			}

		} else {
			profesorService.updateProfesor(profesorModel);
			flash.addFlashAttribute("success", "Profesor modificado con éxito");
			return "redirect:/home";
		}
		return "redirect:/profesores/listProfesores";
	}

	// Formulario
	@GetMapping(value = { "/formProfesor", "/formProfesor/{id}" })
	public String formProfesor(@PathVariable(name = "id", required = false) Integer id, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = usuarioRepository.findByUsername(userDetails.getUsername());

			if (id == null) {
				model.addAttribute("profesor", new ProfesorModel());
			} else {
				if ((u.getId() + 1) == id) {
					model.addAttribute("profesor", profesorService.findProfesor(id));
				} else {
					return "/error/403";
				}
			}
			return FORM_VIEW;
	}

	@GetMapping("/formProfesorEmail/{email}")
	public String formProfesorEmail(@PathVariable(name = "email", required = false) String email, Model model) {
		Usuario u = usuarioRepository.findByUsername(email);
		int i = u.getId();
		int id = i + 1;
		return "redirect:/profesores/formProfesor/" + id;
	}

	// Metodo de borrar
	@GetMapping("/deleteProfesor/{id}")
	public String deleteProfesor(@PathVariable("id") int id, RedirectAttributes flash) {
		ProfesorModel p = profesorService.findProfesor(id);
		if (profesorService.removeProfesor(id) == 0) {
			try {
				usuarioService.deleteUser(p.getEmail());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flash.addFlashAttribute("success", "Profesor eliminado con éxito");
		} else
			flash.addFlashAttribute("error", "No se ha podido eliminar el profesor");

		return "redirect:/profesores/listProfesores";
	}

	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/profesores/listProfesores");
	}
}

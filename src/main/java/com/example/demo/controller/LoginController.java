package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Usuario;
import com.example.demo.service.AlumnoService;
import com.example.demo.serviceImpl.UsuarioService;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("alumnoServiceImpl")
	private AlumnoService alumnoService;
	
	
	@GetMapping("/home")
	public String inicio(Model model) {
		return "index1";
	}
	
	@GetMapping("/auth/login")
	public String login (Model model,@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false)String logout) {
		model.addAttribute("user",new Usuario());
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return "login1";
	}
	
	@GetMapping("/auth/registerForm")
	public String registerForm(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "registro1";
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute Alumno alumno,RedirectAttributes flash) {
		alumnoService.addAlumno(alumnoService.transform(alumno));
		Usuario user = new Usuario();
		user.setUsername(alumno.getEmail());
		user.setPassword(alumno.getPassword());
		usuarioService.registrar(user);
		flash.addFlashAttribute("success","User registered successfully");
		
		return "redirect:/auth/login";
	}
	
}

package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Usuario;
import com.example.demo.service.AlumnoService;
import com.example.demo.service.NoticiaService;
import com.example.demo.serviceImpl.UsuarioService;

@Controller
@RequestMapping("/")
public class LoginController {
	private static final String HOME_VIEW = "home";
	private static final String LOGIN_VIEW = "login";
	private static final String REGISTER_VIEW = "register";
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("alumnoService")
	private AlumnoService alumnoService;
	
	@Autowired
	@Qualifier("noticiaService")
	private NoticiaService noticiaService;
	
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/home");
	}
	
	@GetMapping("/home")
	public ModelAndView inicio(Model model) {
		ModelAndView mav = new ModelAndView(HOME_VIEW);
		mav.addObject("noticias", noticiaService.ListAllNoticias());
		return mav;
	}
	
	@GetMapping("/auth/login")
	public String login (Model model,@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false)String logout) {
		model.addAttribute("user",new Usuario());
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return LOGIN_VIEW;
	}
	
	@GetMapping("/auth/registerForm")
	public String registerForm(Model model, @RequestParam(name="error",required=false)String error) {
		model.addAttribute("alumno", new Alumno());
		model.addAttribute("error",error);
		return REGISTER_VIEW;
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute Alumno alumno,RedirectAttributes flash) {
		Usuario user = new Usuario();
		user.setUsername(alumno.getEmail());
		user.setPassword(alumno.getPassword());
		user.setRole("ROL_ALUMNO");
		Usuario userExist=usuarioService.registrar(user);
		if(userExist != null) {
			alumno.setUsuario(user);
			alumnoService.addAlumno(alumnoService.transform(alumno));
			flash.addFlashAttribute("success","User registered successfully");
			return "redirect:/auth/login";
		}else {
			return "redirect:/auth/registerForm?error";
		}
	}
	
}

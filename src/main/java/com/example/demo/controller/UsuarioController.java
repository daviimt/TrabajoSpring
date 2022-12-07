package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.serviceImpl.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	//Inyectamos el servicio de profesor
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;
	
	//Metodo de activar/descativar 
	@PostMapping("/activarUsuario/{username}")
	public String activate(@PathVariable("username")String username, RedirectAttributes flash) {
		if(usuarioService.activar(username)==1) {
			flash.addFlashAttribute("success","Usuario activado con éxito");
		}else if(usuarioService.activar(username)==0) {
			flash.addFlashAttribute("success","Usuario desactivado con éxito");
		}else
			flash.addFlashAttribute("error","No se ha podido activar/desactivar el usuario");	
		return "redirect:/usuarios/listUsuarios";
	}
	
}

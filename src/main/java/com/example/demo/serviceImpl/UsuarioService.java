package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

import ch.qos.logback.core.helpers.Transform;

@Service("usuarioService")
public class UsuarioService implements UserDetailsService {

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);

		UserBuilder builder = null;

		if (usuario != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(usuario.getPassword());
			builder.authorities(new SimpleGrantedAuthority(usuario.getRole()));

		} else
			throw new UsernameNotFoundException("Usuario no encontrado");
		return builder.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public Usuario registrar(Usuario usuario) {
		List<Usuario>usuarios=usuarioRepository.findAll();
		for(Usuario u: usuarios) {
			if(u.getUsername().equals(usuario.getUsername())) {
				return null;
			}
		}
		usuario.setPassword(passwordEncoder().encode(usuario.getPassword()));
		usuario.setEnabled(false);
		usuario.setRole(usuario.getRole());
		return usuarioRepository.save(usuario);
	}
	
	public int activar(String username) {
		int a=0;
		Usuario u=usuarioRepository.findByUsername(username);
		Usuario user=new Usuario();
		user.setPassword(passwordEncoder().encode(u.getPassword()));
		user.setUsername(u.getUsername());
		user.setId(u.getId());
		
		if(u.isEnabled()==false) {
			user.setEnabled(true);
			a=1;
		}else {
			user.setEnabled(false);
			a=0;
		}
		user.setRole(u.getRole());
		
		usuarioRepository.save(user);
		return a;
	}
	
	public void deleteUser(String username) throws Exception {
		Usuario u = usuarioRepository.findByUsername(username);
		usuarioRepository.delete(u);
	}
	
	public List<Usuario> listAllUsuarios() {
		return usuarioRepository.findAll().stream().collect(Collectors.toList());
	}
	public Usuario findUsuario(String email) {
		
		return usuarioRepository.findByUsername(email);
	}
}

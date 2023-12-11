package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.UsuarioModel;
import com.example.demo.Repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public ArrayList<UsuarioModel> obtenerUsuarios(){
		return(ArrayList<UsuarioModel>) usuarioRepository.findAll();
		
	}
	
	public Optional<UsuarioModel> autenticarUsuario(String usuario, String contraseña) {
	    System.out.println("Intento de autenticación - Usuario: " + usuario + ", Contraseña: " + contraseña);

	    Optional<UsuarioModel> usuarioOptional = usuarioRepository.findByusuario(usuario);

	    if (usuarioOptional.isPresent()) {
	        UsuarioModel usuarioModel = usuarioOptional.get();
	        if (contraseña.equals(usuarioModel.getPassword())) {
	            System.out.println("Autenticación exitosa para el usuario: " + usuario);
	            return Optional.of(usuarioModel);
	        }
	    }

	    System.out.println("Autenticación fallida para el usuario: " + usuario);
	    return Optional.empty();
	}

	
	public UsuarioModel guardarUsuario(UsuarioModel usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Optional<UsuarioModel> obtenerPorId(Long id){
		return usuarioRepository.findById(id);
	}
	

	
	public boolean eliminarUsuario(Long id) {
		try {
			usuarioRepository.deleteById(id);
			return true;	
		} catch(Exception err){
			return false;
		}
	}

}

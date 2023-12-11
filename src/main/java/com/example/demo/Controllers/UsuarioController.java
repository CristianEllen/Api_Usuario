package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.UsuarioModel;
import com.example.demo.Service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping()
	public ArrayList<UsuarioModel> obtenerUsuarios(){
		return usuarioService.obtenerUsuarios();
	}
	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioModel loginForm) {
        Optional<UsuarioModel> usuario = usuarioService.autenticarUsuario(loginForm.getUsuario(), loginForm.getPassword());

        if (usuario.isPresent()) {
            return ResponseEntity.ok("Autenticación exitosa");
        } else {
            return ResponseEntity.status(401).body("Error de autenticación");
        }
    }

	@PostMapping()
	public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
		return this.usuarioService.guardarUsuario(usuario);
	}
	
	@GetMapping (path = "/{id}")
	public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id")Long id){
		return this.usuarioService.obtenerPorId(id);
	}
	

	
	@DeleteMapping(path ="/{id}")
		public String eliminarPorId(@PathVariable("id")Long id) {
			boolean ok = this.usuarioService.eliminarUsuario(id);
			if(ok) {
				return "Se eliminó el uduario con id " + id;	
			}else {
				return "No pudo eliminar el usuario con id " +id;
			}
		}
	
}

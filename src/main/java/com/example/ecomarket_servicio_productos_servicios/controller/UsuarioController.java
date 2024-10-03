package com.example.ecomarket_servicio_productos_servicios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomarket_servicio_productos_servicios.model.Usuario;
import com.example.ecomarket_servicio_productos_servicios.service.UsuarioService;
import com.google.firebase.auth.FirebaseAuthException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        try {
            String uid = usuarioService.crearUsuario(usuario);
            return ResponseEntity.ok("Usuario creado con UID: " + uid);
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(500).body("Error al crear usuario: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String userId) {
        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorId(userId);
            return ResponseEntity.ok(usuario);
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/actualizar/{userId}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable String userId, @RequestBody Usuario usuario) {
        try {
            usuario.setUserId(userId);  // Asegurarse de actualizar el usuario correcto
            String uid = usuarioService.actualizarUsuario(usuario);
            return ResponseEntity.ok("Usuario actualizado con UID: " + uid);
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(500).body("Error al actualizar usuario: " + e.getMessage());
        }
    }

    @DeleteMapping("/deshabilitar/{userId}")
    public ResponseEntity<Void> deshabilitarUsuario(@PathVariable String userId) {
        try {
            usuarioService.deshabilitarUsuario(userId);
            return ResponseEntity.noContent().build();
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(500).build();
        }
    }

    // Obtener todos los usuarios
    @GetMapping()
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(500).build();
        }
    }
}

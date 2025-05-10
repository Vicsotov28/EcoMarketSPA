package com.ecomarketspa.Controller;

import com.ecomarketspa.Model.Usuario;
import com.ecomarketspa.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // 1. GET: Listar todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // 2. GET: Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // 3. POST: Crear usuario
    @PostMapping
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    // 4. DELETE: Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (usuarioService.obtenerUsuarioPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
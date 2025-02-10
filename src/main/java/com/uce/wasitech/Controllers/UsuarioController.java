package com.uce.wasitech.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.uce.wasitech.Entities.Usuario;
import com.uce.wasitech.Repositories.UsuarioRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    /**
     * Obtiene la lista de usuarios (sin validación de seguridad).
     */
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }
    
    /**
     * Obtiene un usuario por su ID (sin validación de seguridad).
     */
    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario con el ID: " + id));
    }
    
    /**
     * Crea un nuevo usuario (sin validación de seguridad).
     */
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        return usuarioRepository.save(usuario);
    }
    
    /**
     * Actualiza los datos de un usuario (sin validación de seguridad).
     */
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario detallesUsuario) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario con el ID: " + id));

        usuario.setNombre(detallesUsuario.getNombre());
        usuario.setApellido(detallesUsuario.getApellido());
        usuario.setEmail(detallesUsuario.getEmail());
        usuario.setTelefono(detallesUsuario.getTelefono());

        if (detallesUsuario.getPassword() != null && !detallesUsuario.getPassword().trim().isEmpty()) {
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1, 1024, 1, detallesUsuario.getPassword());
            usuario.setPassword(hash);
        }

        return usuarioRepository.save(usuario);
    }
    
    /**
     * Elimina un usuario por su ID (sin validación de seguridad).
     */
    @DeleteMapping("/{id}")
    public String borrarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario con el ID: " + id));
        usuarioRepository.delete(usuario);
        return "El usuario con el ID: " + id + " fue eliminado correctamente";
    }
}

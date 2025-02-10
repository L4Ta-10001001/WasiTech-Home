package com.uce.wasitech.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uce.wasitech.Entities.Usuario;
import com.uce.wasitech.Repositories.UsuarioRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Inicio de sesión sin seguridad, solo verifica usuario y contraseña.
     */
    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioOptional.isPresent()) {
            Usuario usuarioLogueado = usuarioOptional.get();
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

            if (argon2.verify(usuarioLogueado.getPassword(), usuario.getPassword())) {
                return "LOGIN_SUCCESS"; // Mensaje simple en vez de JWT
            }
        }
        return "LOGIN_FAIL";
    }
}

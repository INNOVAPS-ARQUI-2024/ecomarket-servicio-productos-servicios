package com.example.ecomarket_servicio_productos_servicios.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecomarket_servicio_productos_servicios.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;

@Service
public class UsuarioService {

    // Obtener un usuario por su UID
    public Usuario obtenerUsuarioPorId(String userId) throws FirebaseAuthException {
        UserRecord userRecord = FirebaseAuth.getInstance().getUser(userId);

        Usuario usuario = new Usuario();
        usuario.setUserId(userRecord.getUid());
        usuario.setName(userRecord.getDisplayName());
        usuario.setEmail(userRecord.getEmail());
        usuario.setPhone(userRecord.getPhoneNumber());
        usuario.setActive(!userRecord.isDisabled());
        usuario.setCreatedAt(new Date(userRecord.getUserMetadata().getCreationTimestamp()));
        usuario.setUpdatedAt(new Date(userRecord.getUserMetadata().getLastSignInTimestamp()));

        return usuario;
    }

    // Crear un nuevo usuario
    public String crearUsuario(Usuario usuario) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(usuario.getEmail())
                .setPassword("your-default-password")  // Cambia esta parte según sea necesario
                .setDisplayName(usuario.getName())
                .setPhoneNumber(usuario.getPhone())
                .setDisabled(!usuario.isActive());

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        return userRecord.getUid();
    }

    // Actualizar un usuario
    public String actualizarUsuario(Usuario usuario) throws FirebaseAuthException {
        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(usuario.getUserId())
                .setDisplayName(usuario.getName())
                .setEmail(usuario.getEmail())
                .setPhoneNumber(usuario.getPhone())
                .setDisabled(!usuario.isActive());

        UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
        return userRecord.getUid();
    }

    // Deshabilitar un usuario
    public void deshabilitarUsuario(String userId) throws FirebaseAuthException {
        FirebaseAuth.getInstance().updateUser(
                new UserRecord.UpdateRequest(userId)
                        .setDisabled(true)
        );
    }

    // Obtener una lista de todos los usuarios de Firebase
    public List<Usuario> obtenerTodosLosUsuarios() throws FirebaseAuthException {
        List<Usuario> usuarios = new ArrayList<>();

        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        while (page != null) {
            for (UserRecord userRecord : page.getValues()) {
                Usuario usuario = new Usuario();
                usuario.setUserId(userRecord.getUid());
                usuario.setName(userRecord.getDisplayName());
                usuario.setEmail(userRecord.getEmail());
                usuario.setPhone(userRecord.getPhoneNumber());
                usuario.setActive(!userRecord.isDisabled());
                usuario.setCreatedAt(new Date(userRecord.getUserMetadata().getCreationTimestamp()));
                usuario.setUpdatedAt(new Date(userRecord.getUserMetadata().getLastSignInTimestamp()));
                usuarios.add(usuario);
            }
            page = page.getNextPage();
        }

        return usuarios;
    }
}

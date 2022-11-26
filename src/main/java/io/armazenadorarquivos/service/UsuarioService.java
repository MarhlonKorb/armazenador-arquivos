package io.armazenadorarquivos.service;

import io.armazenadorarquivos.entities.Usuario;
import io.armazenadorarquivos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public Optional<Usuario> getUsuarioById(Integer id){
        return usuarioRepository.findById(id);
    }
    
    public Usuario cadastrar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
    public void deletar(Integer id){
        usuarioRepository.deleteById(id);
    }
}

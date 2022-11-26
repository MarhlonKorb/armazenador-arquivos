package io.armazenadorarquivos.rest;

import io.armazenadorarquivos.entities.Usuario;
import io.armazenadorarquivos.service.UsuarioService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    final
    UsuarioService usuarioService;
    
    public UsuarioController (UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Usuario> getUsuario (@PathVariable Integer id) {
        Optional<Usuario> usuarioEncontrado = usuarioService.getUsuarioById(id);
        if (usuarioEncontrado.isPresent()) {
            return ResponseEntity.of(usuarioEncontrado);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario (@RequestBody Usuario usuario) {
        try{
        usuarioService.cadastrar(usuario);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Falha ao cadastrar o usuário ".concat(usuario.getNome()));
        }
        return ResponseEntity.ok().body(usuario);
    }
    
    @PutMapping
    public String alterarUsuario () {
        return "Teste";
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> excluirUsuario (@PathVariable Integer id) {
          Optional<Usuario> usuarioEncontrado = usuarioService.getUsuarioById(id);
          if (usuarioEncontrado.isPresent()){
              usuarioService.deletar(usuarioEncontrado.get().getId());
              return ResponseEntity.ok().body(usuarioEncontrado.get());
          }
        return ResponseEntity.notFound().build();
    }
}

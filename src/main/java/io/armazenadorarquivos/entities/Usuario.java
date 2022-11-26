package io.armazenadorarquivos.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome")
    private String nome;
    
    @OneToMany(targetEntity = Arquivo.class, fetch = FetchType.EAGER)
    private List<Arquivo> arquivo;
    
    public Integer getId () {
        return id;
    }
    
    public void setId (Integer id) {
        this.id = id;
    }
    
    public String getNome () {
        return nome;
    }
    
    public void setNome (String nome) {
        this.nome = nome;
    }
}

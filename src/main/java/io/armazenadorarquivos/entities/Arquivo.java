package io.armazenadorarquivos.entities;

import jakarta.persistence.*;

@Entity
public class Arquivo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "url")
    private String url;
    
    public Arquivo (String nome, String url) {
        this.nome = nome;
        this.url = url;
    }
    
    public Arquivo () {
    
    }
    
    public String getNome () {
        return this.nome;
    }
    
    public void setNome (String nome) {
        this.nome = nome;
    }
    
    public String getUrl () {
        return this.url;
    }
    
    public void setUrl (String url) {
        this.url = url;
    }
    
}

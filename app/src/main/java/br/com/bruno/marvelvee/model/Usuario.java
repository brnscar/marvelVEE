package br.com.bruno.marvelvee.model;

import java.util.List;

public class Usuario {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return getId() + " - " + getNome();
    }

//    public List<Usuario> getUsuarios() {
//        String usuarios = getUsuarios();
//        return usuarios;
//    }
}

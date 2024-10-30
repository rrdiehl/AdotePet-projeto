/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.model;

/**
 *
 * @author rrdiehl
 */
public class Login {

    private String email;
    private String senha;
    private int id;

    public Login(String email, String senha, int id) {
        this.email = email;
        this.senha = senha;
        this.id = id;
    }

    public Login() {
    }
      
    

    //getters e setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.controller;

import server.db.service.LoginService;
import server.model.Login;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author rrdiehl
 */
public class LoginController {
    private final LoginService service = new LoginService();

    /**
     * LoginController
     *
     */
    public LoginController() {};

    /**
     * cadastrar
     *
     * @param login Login
     * @return String
     */
    public String cadastrar( Login login )
    {
        try
        {
            service.add( login );
        }

        catch ( Exception e )
        {
            return( "Erro ao cadastrar usuário: " + e.getMessage() );
        }

        return "Usuário cadastrado com sucesso!" ;
    }

    /**
     * buscarTodosLogin
     *
     * @return  List<Login>
     */
    public List<Login> buscarTodosLogin()
    {
        List<Login> users = new ArrayList<>();

        try
        {
            users = service.getLogin();
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao buscar users" );
        }
        
        return users;
    }
    
      /**
     * buscarLoginPorId
     *
     * @param id int
     * @return Login
     */
    public Login buscarLoginPorId( int id )
    {
        try
        {
            return service.getLoginById( id );
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao buscar login com o id " + id );
            return null;
        }
    }

    /**
     * deletarRegistro
     *
     * @param login Login
     * @return String
     */
    public String deletarRegistro( Login login )
    {
        try
        {
            service.delete( login );
        }

        catch ( Exception e )
        {
            return "Erro ao deletar o login: " + e.getMessage();
        }

        return "login deletado com sucesso!";
    }

    /**
     * atualizarLogin
     *
     * @param Login login
     * @return String
     */
    public String atualizarLogin( Login login )
    {
        try
        {
            service.udpate( login );
        }

        catch ( Exception e )
        {
            return "Erro ao atualizar o login: " + e.getMessage();
        }

        return "login atualizado com sucesso!";
    }
    
        /**
     * login
     * 
     * @param login
     * @return 
     */
    public boolean login( Login login )
    {
        try
        {
            return service.login(login);
        }
        
        catch( Exception e )
        {
            System.out.println("Erro ao fazer login: " + e);
            return false;
        }
    }
    
}

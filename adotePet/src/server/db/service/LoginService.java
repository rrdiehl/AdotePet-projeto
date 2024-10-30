/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.db.service;

import java.util.List;
import server.db.repository.LoginRepository;
import server.model.Login;
import server.db.Transactions.LoginTransactions;

public class LoginService implements LoginRepository {

    private LoginTransactions transaction = new LoginTransactions();

    @Override
    public void add(Login login) throws Exception {
        boolean loginAlreadyExist = transaction.getLoginById(login.getId()) != null;

        if (loginAlreadyExist) {
            throw new Exception("O id de login já é utilizado em outro registro!");
        } else {
            transaction.add(login);
        }
    }

    /**
     * update
     *
     * @param tutor Tutor
     */
    @Override
    public void udpate(Login login) throws Exception {
        boolean loginNotExist = transaction.getLoginById(login.getId()) == null;

        if (loginNotExist) {
            throw new Exception("Não foi possível atualizar o login pois ele não existe!");
        } else {
            transaction.update(login);
        }
    }

    /**
     * delete
     *
     * @param tutor Tutor
     */
    @Override
    public void delete(Login login) throws Exception {

        boolean loginNotExist = transaction.getLoginById(login.getId()) == null;

        if (loginNotExist) {
            throw new Exception("Não foi possível deletar o login pois ele não existe!");
        } else {
            transaction.delete(login);
        }
    }

    /**
     * getTutorById
     *
     * @param id int
     * @return Tutor
     */
    @Override
    public Login getLoginById(int id) {
        return (Login) transaction.getLoginById(id);

    }

    /**
     * getTutores
     *
     * @return List<Tutor>
     */
    @Override
    public List<Login> getLogin() {
        return transaction.getLogin();
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
            return transaction.validarLogin(login);
        }
        
        catch( Exception e )
        {
            System.out.println("Erro ao fazer login");
            return false;
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.utilities;

import server.model.Login;
import server.db.DatabaseConfiguration;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author rrdiehl
 */
public class ValidacaoLogin {

    public boolean validarLogin(Login login) {
        boolean validacao = false;

        try {
            DatabaseConfiguration.abreConexao();
            PreparedStatement stmt;

            String wSql = "SELECT * FROM login WHERE email = ? AND senha = md5(?)";

            stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getSenha());
            
            validacao = true;
            
            ResultSet resultado = stmt.executeQuery();

        } catch (SQLException e) {
            System.out.println("Erro ao validar e-mail e senha: " + e);
        } finally {
            DatabaseConfiguration.closeConnection(DatabaseConfiguration.con);
        }
        
        return validacao;
    }
}

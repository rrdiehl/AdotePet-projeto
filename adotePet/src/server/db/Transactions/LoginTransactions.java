package server.db.Transactions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import server.model.Login;
import server.db.DatabaseConfiguration;
import server.db.service.LoginService;

public class LoginTransactions {

    public LoginService service;

    public void add(Login login) {
        try {
            DatabaseConfiguration.abreConexao();
            PreparedStatement stmt;

            login.setId(nextId());
            String wSql = "INSERT INTO login VALUES ( ?, ?, MD5 (?));";
            stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setInt(1, login.getId());
            stmt.setString(2, login.getEmail());
            stmt.setString(3, login.getSenha());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        } finally {
            DatabaseConfiguration.closeConnection(DatabaseConfiguration.con);
        }
    }

    public void update(Login login) {
        try {
            DatabaseConfiguration.abreConexao();
            String wSql = " UPDATE login SET senha = MD5 (?), ";
            wSql += "WHERE id = ?";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setInt(1, login.getId());
            stmt.setString(2, login.getEmail());
            stmt.setString(3, login.getSenha());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        } finally {
            DatabaseConfiguration.closeConnection(DatabaseConfiguration.con);
        }
    }

    public void delete(Login login) {
        try {
            DatabaseConfiguration.abreConexao();

            String wSql = "delete FROM login WHERE id = ?";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setInt(1, login.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        } finally {
            DatabaseConfiguration.closeConnection(DatabaseConfiguration.con);
        }
    }

    public Object getLoginById(int id) {

        try {
            DatabaseConfiguration.abreConexao();

            String wSql = "SELECT * FROM login WHERE id = ?";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Login login = new Login();
                login.setId(rs.getInt("id"));
                login.setEmail(rs.getString("email"));
                login.setSenha(rs.getString("senha"));

                return login;
            }

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        } finally {
            DatabaseConfiguration.closeConnection(DatabaseConfiguration.con);
        }

        return null;
    }

    public List<Login> getLogin() {
        List<Login> users = new ArrayList<>();

        try {
            DatabaseConfiguration.abreConexao();

            String wSql = "SELECT * FROM login order by id";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Login login = new Login();
                login.setId(rs.getInt("id"));
                login.setEmail(rs.getString("email"));
                login.setSenha(rs.getString("senha"));

                users.add(login);
            }

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        } finally {
            DatabaseConfiguration.closeConnection(DatabaseConfiguration.con);
        }

        return users;
    }
    
     public boolean validarLogin(Login login) {

        try {
            DatabaseConfiguration.abreConexao();
            PreparedStatement stmt;

            String wSql = "SELECT * FROM login WHERE email = ? AND senha = md5(?)";

            stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getSenha());
            
            return stmt.executeQuery().next();

        } catch (SQLException e) {
            System.out.println("Erro ao validar e-mail e senha: " + e);
        } finally {
            DatabaseConfiguration.closeConnection(DatabaseConfiguration.con);
        }
        
        return false;
    }
    
    private int nextId()
    {
        String wSql = "SELECT MAX(id) from login;";

        try (  PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql) )
        {
            ResultSet rs = stmt.executeQuery();

            if ( rs.next() )
            {
                return rs.getInt( "max" ) + 1;
            }

        }

        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return 1;
    }
}

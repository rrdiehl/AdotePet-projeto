package server.db.Transactions;

import server.db.DatabaseConfiguration;
import server.model.Tutor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mariele Huff
 */
public class TutorTransactions
{
    /**
     * add
     *
     * @param tutor Tutor
     */
    public void add( Tutor tutor )
    {
        try
        {
            DatabaseConfiguration.abreConexao();
            PreparedStatement stmt;

            tutor.setId( nextId() );
            String wSql = "INSERT INTO tutores VALUES (?, ?, ?, ? );";
            stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setInt ( 1, tutor.getId() );
            stmt.setString ( 2, tutor.getNome() );
            stmt.setString ( 3, tutor.getEmail() );
            stmt.setTimestamp( 4, Timestamp.valueOf(LocalDateTime.now() ) );


            stmt.executeUpdate();
        }

        catch ( SQLException ex )
        {
            System.out.println( "ERRO: " +ex.getMessage() );
        }

        finally
        {
            DatabaseConfiguration.closeConnection( DatabaseConfiguration.con );
        }
    }

    /**
     * udpate
     *
     * @param tutor Tutor
     */
    public void update( Tutor tutor )
    {
        try
        {
            DatabaseConfiguration.abreConexao();
            String wSql = " UPDATE tutores SET nome = ?, email = ? ";

            wSql += "WHERE id = ?";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setString ( 1, tutor.getNome() );
            stmt.setString ( 2, tutor.getEmail() );
            stmt.setInt( 3, tutor.getId() );

            stmt.executeUpdate();
        }

        catch ( SQLException ex )
        {
            System.out.println( "ERRO: " + ex.getMessage() );
        }

        finally
        {
            DatabaseConfiguration.closeConnection( DatabaseConfiguration.con );
        }
    }

    /**
     * delete
     *
     * @param tutor Tutor
     */
    public void delete( Tutor tutor )
    {
        try
        {
            DatabaseConfiguration.abreConexao();

            String wSql = "delete FROM tutores WHERE id = ?";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement( wSql );

            stmt.setInt( 1, tutor.getId() );

            stmt.executeUpdate();
        }

        catch ( SQLException ex )
        {
            System.out.println("ERRO: " +ex.getMessage() );
        }

        finally
        {
            DatabaseConfiguration.closeConnection( DatabaseConfiguration.con );
        }
    }

    /**
     * getTutorById
     *
     * @param id int
     * @return Tutor
     */
    public Tutor getTutorById( int id )
    {
        try
        {
            DatabaseConfiguration.abreConexao();

            String wSql = "SELECT * FROM tutores WHERE id = ?";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setInt( 1, id );

            ResultSet rs = stmt.executeQuery();

            if ( rs.next() )
            {
                Tutor tutor = new Tutor();
                tutor.setId( rs.getInt( "id" ) );
                tutor.setNome( rs.getString( "nome" ) );
                tutor.setEmail( rs.getString( "email") );
                tutor.setDataDeRegistro( rs.getTimestamp( "dataDeRegistro" ).toLocalDateTime() );

                return tutor;
            }

        }

        catch ( Exception e )
        {
            System.out.println( "ERRO: " +e.getMessage() );
        }

        finally
        {
            DatabaseConfiguration.closeConnection( DatabaseConfiguration.con );
        }

        return null;
    }

    /**
     * getTutores
     *
     * @return List<Tutor>
     */
    public List<Tutor> getTutores()
    {
        List<Tutor> tutores = new ArrayList<>();

        try
        {
            DatabaseConfiguration.abreConexao();

            String wSql = "SELECT * FROM tutores order by id";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            ResultSet rs = stmt.executeQuery();

            while ( rs.next() )
            {
                Tutor tutor = new Tutor();
                tutor.setId( rs.getInt( "id" ) );
                tutor.setNome( rs.getString( "nome" ) );
                tutor.setEmail( rs.getString( "email" ) );
                tutor.setDataDeRegistro( rs.getTimestamp( "dataDeRegistro" ).toLocalDateTime() );

                tutores.add( tutor );
            }

        }

        catch ( Exception e )
        {
            System.out.println( "ERRO: " +e.getMessage() );
        }

        finally
        {
            DatabaseConfiguration.closeConnection( DatabaseConfiguration.con );
        }

        return tutores;
    }
    
    /**
     * getTutoresDisponiveis
     * 
     * @return  List<Tutor>
     */
    public List<Tutor> getTutoresDisponiveis()
    {
        List<Tutor> tutores = new ArrayList<>();

        try
        {
            DatabaseConfiguration.abreConexao();

            String wSql = "SELECT * "
                        + "FROM tutores t "
                        + "where t.id not in ( select a.tutorId from  adocoes a where a.statusAdocao = 0 or a.statusAdocao = 1 ) "
                        + "order by t.id ";
            
            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            ResultSet rs = stmt.executeQuery();

            while ( rs.next() )
            {
               Tutor tutor = new Tutor();
                tutor.setId( rs.getInt( "id" ) );
                tutor.setNome( rs.getString( "nome" ) );
                tutor.setEmail( rs.getString( "email" ) );
                tutor.setDataDeRegistro( rs.getTimestamp( "dataDeRegistro" ).toLocalDateTime() );

                tutores.add( tutor );
            }

        }

        catch ( Exception e )
        {
            System.out.println( "ERRO: " +e.getMessage() );
        }

        finally
        {
            DatabaseConfiguration.closeConnection( DatabaseConfiguration.con );
        }
        
        return tutores;
    }
    
       private int nextId()
    {
        String wSql = "SELECT MAX(id) from tutores;";

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

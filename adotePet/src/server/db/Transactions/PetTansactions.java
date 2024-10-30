package server.db.Transactions;

import server.db.DatabaseConfiguration;
import server.model.Pet;

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
public class PetTansactions
{
    /**
     * add
     *
     * @param pet Pet
     */
    public void add( Pet pet )
    {
        try
        {
            DatabaseConfiguration.abreConexao();
            PreparedStatement stmt;

            pet.setId( nextId() );
            
            String wSql = "INSERT INTO pets VALUES (?, ?, ?, ?, ?, ?, ? );";
            stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setInt ( 1, pet.getId() );
            stmt.setString ( 2, pet.getNome() );
            stmt.setInt ( 3, pet.getIdade() );
            stmt.setTimestamp( 4, Timestamp.valueOf( LocalDateTime.now() ) );
            stmt.setString ( 5, pet.getImagem() );
            stmt.setInt ( 6, pet.getTipoPet().ordinal() );
            stmt.setInt ( 7, pet.getTipoPorte().ordinal() );

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
     * update
     *
     * @param pet Pet
     */
    public void update( Pet pet )
    {
        try
        {
            DatabaseConfiguration.abreConexao();
            
            String wSql = " UPDATE pets SET nome = ?, " +
                            "idade = ?, " +
                            "imagem = ?, " +
                            "tipoPet = ?, " +
                            "tipoPorte = ? ";

            wSql += "WHERE id = ?";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setString ( 1, pet.getNome() );
            stmt.setInt ( 2, pet.getIdade() );
            stmt.setString ( 3, pet.getImagem() );
            stmt.setInt ( 4, pet.getTipoPet().ordinal() );
            stmt.setInt ( 5, pet.getTipoPorte().ordinal() );
            stmt.setInt ( 6, pet.getId() );

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
     * @param pet Pet
     */
    public void delete( Pet pet )
    {
        try
        {
            DatabaseConfiguration.abreConexao();

            String wSql = "delete FROM pets WHERE id = ?";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement( wSql );

            stmt.setInt( 1, pet.getId() );

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
     * getPetById
     *
     * @param id int
     * @return Pet
     */
    public Pet getPetById( int id )
    {
        try
        {
            DatabaseConfiguration.abreConexao();

            String wSql = "SELECT * FROM pets WHERE id = ?";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setInt( 1, id );

            ResultSet rs = stmt.executeQuery();

            if ( rs.next() )
            {
                Pet pet = new Pet();
                pet.setId( rs.getInt( "id" ) );
                pet.setNome( rs.getString( "nome" ) );
                pet.setIdade( rs.getInt( "idade" ) );
                pet.setDataDeRegistro( rs.getTimestamp( "dataDeRegistro" ).toLocalDateTime() );
                pet.setImagem( rs.getString( "imagem") );
                pet.setTipoPorte( Pet.TipoPorte.values()[ rs.getInt( "tipoPorte" ) ] );
                pet.setTipoPet( Pet.TipoPet.values()[ rs.getInt( "tipoPet" ) ] );

                return pet;
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
     * getPets
     *
     * @return List<Pet>
     */
    public List<Pet> getPets()
    {
        List<Pet> pets = new ArrayList<>();

        try
        {
            DatabaseConfiguration.abreConexao();

            String wSql = "SELECT * FROM pets order by id";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            ResultSet rs = stmt.executeQuery();

            while ( rs.next() )
            {
                Pet pet = new Pet();
                pet.setId( rs.getInt( "id" ) );
                pet.setNome( rs.getString( "nome" ) );
                pet.setIdade( rs.getInt( "idade" ) );
                pet.setDataDeRegistro( rs.getTimestamp( "dataDeRegistro" ).toLocalDateTime() );
                pet.setImagem( rs.getString( "imagem") );
                pet.setTipoPorte( Pet.TipoPorte.values()[ rs.getInt( "tipoporte" ) ] );
                pet.setTipoPet( Pet.TipoPet.values()[ rs.getInt( "tipoPet" ) ] );

               pets.add( pet );
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

        return pets;
    }
    
    /**
     * getPetsDisponiveis
     *
     * @return List<Pet>
     */
    public List<Pet> getPetsDisponiveis()
    {
        List<Pet> pets = new ArrayList<>();

        try
        {
            DatabaseConfiguration.abreConexao();

            String wSql = "SELECT * "
                        + "FROM pets p "
                        + "where p.id not in ( select a.petid from  adocoes a where a.statusAdocao = 0 or a.statusAdocao = 1 ) "
                        + "order by p.id ";
            
            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            ResultSet rs = stmt.executeQuery();

            while ( rs.next() )
            {
                Pet pet = new Pet();
                pet.setId( rs.getInt( "id" ) );
                pet.setNome( rs.getString( "nome" ) );
                pet.setIdade( rs.getInt( "idade" ) );
                pet.setDataDeRegistro( rs.getTimestamp( "dataDeRegistro" ).toLocalDateTime() );
                pet.setImagem( rs.getString( "imagem") );
                pet.setTipoPorte( Pet.TipoPorte.values()[ rs.getInt( "tipoporte" ) ] );
                pet.setTipoPet( Pet.TipoPet.values()[ rs.getInt( "tipoPet" ) ] );

               pets.add( pet );
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

        return pets;
    }
    
    /**
     * getPetsByFiltro
     *
     * @return List<Pet>
     */
    public List<Pet> getPetsByFiltro( String whereCondition )
    {
        List<Pet> pets = new ArrayList<>();

        try
        {
            DatabaseConfiguration.abreConexao();

            String wSql = "SELECT * "
                        + "FROM pets p "
                        + "WHERE "
                        + whereCondition;
            
            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            ResultSet rs = stmt.executeQuery();

            while ( rs.next() )
            {
                Pet pet = new Pet();
                pet.setId( rs.getInt( "id" ) );
                pet.setNome( rs.getString( "nome" ) );
                pet.setIdade( rs.getInt( "idade" ) );
                pet.setDataDeRegistro( rs.getTimestamp( "dataDeRegistro" ).toLocalDateTime() );
                pet.setImagem( rs.getString( "imagem") );
                pet.setTipoPorte( Pet.TipoPorte.values()[ rs.getInt( "tipoporte" ) ] );
                pet.setTipoPet( Pet.TipoPet.values()[ rs.getInt( "tipoPet" ) ] );

               pets.add( pet );
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

        return pets;
    }
    
    
    private int nextId()
    {
        String wSql = "SELECT MAX(id) from pets;";

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

package server.db.Transactions;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import server.db.DatabaseConfiguration;
import server.db.service.PetService;
import server.db.service.TutorService;
import server.model.Adocao;
import server.model.Pet;
import server.model.Tutor;

/**
 *
 * @author luisk
 */
public class AdocaoTransactions {
    
    /**
     * 
     * @param adocao
     */
    public void add( Adocao adocao )
    {
        try
        {
            DatabaseConfiguration.abreConexao();
            PreparedStatement stmt;

            adocao.setId(nextId());
            
            String wSql = "INSERT INTO adocoes VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            
            stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setInt   ( 1, adocao.getId() );
            stmt.setInt   ( 2, adocao.getTutorId() );
            stmt.setInt   ( 3, adocao.getPetId() );
            stmt.setInt   ( 4, adocao.getStatusAdocao().ordinal() );
            stmt.setDate  ( 5, Date.valueOf( adocao.getDataSolicitacao() ) );
            stmt.setDate  ( 6,   adocao.getDataAdocao() != null ? Date.valueOf( adocao.getDataAdocao() ) : null);
            stmt.setString( 7, adocao.getInfo() );
            stmt.setString( 8, adocao.getJustificativa() );
            

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
     * 
     * @param adocao
     */
    public void update( Adocao adocao )
    {
        try
        {
            DatabaseConfiguration.abreConexao();
            PreparedStatement stmt;

            String wSql = " UPDATE adocoes SET tutorId = ?, " +
                            "petId = ?, " +
                            "dataSolicitacao = ?, " +
                            "dataAdocao = ?, " +
                            "info = ?, " +
                            "justificativa = ?, " +
                            "statusAdocao = ? ";

            wSql += "WHERE id = ?";
            stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            stmt.setInt   ( 1, adocao.getTutorId() );
            stmt.setInt   ( 2, adocao.getPetId() );
            stmt.setDate  (3, Date.valueOf( adocao.getDataSolicitacao() ) );
            stmt.setDate  ( 4,   adocao.getDataAdocao() != null ? Date.valueOf( adocao.getDataAdocao() ) : null);
            stmt.setString( 5, adocao.getInfo() );
            stmt.setString( 6, adocao.getJustificativa() );
            stmt.setInt   ( 7, adocao.getStatusAdocao().ordinal());
            stmt.setInt   ( 8, adocao.getId() );

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
     * 
     * @param adocao
     */
    public void delete( Adocao adocao )
    {
        try
        {
            DatabaseConfiguration.abreConexao();
            PreparedStatement stmt;

            String wSql = "delete FROM adocoes WHERE id = ?";
            stmt = DatabaseConfiguration.con.prepareStatement( wSql );

            stmt.setInt( 1, adocao.getId() );

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
     * 
     * @param id
     * @return
     */
    public Adocao getAdocaoById( int id )
    {
        try 
        {
            DatabaseConfiguration.abreConexao();
            PreparedStatement stmt;
            ResultSet rs;

            String sql = "SELECT * FROM adocoes WHERE id = ?";
            stmt = DatabaseConfiguration.con.prepareStatement( sql );

            stmt.setInt( 1, id );

            rs = stmt.executeQuery();

            while ( rs.next() )
            {
                Adocao adocao = new Adocao();
                adocao.setId( rs.getInt("id") );
                adocao.setPetId( rs.getInt("petId") );
                adocao.setTutorId( rs.getInt("tutorId") );
                adocao.setDataAdocao( rs.getDate("dataAdocao")!= null ? rs.getDate("dataAdocao").toLocalDate(): null );
                adocao.setDataSolicitacao( rs.getDate("dataSolicitacao") != null ? rs.getDate("dataSolicitacao").toLocalDate() : null );
                adocao.setInfo( rs.getString("info") );
                adocao.setJustificativa(rs.getString("justificativa"));
                adocao.setStatusAdocao( Adocao.StatusAdocao.values()[ rs.getInt("statusAdocao" ) ] );
                
                return adocao;
            }
        } 
        catch ( SQLException ex ) 
        {
            System.out.println("ERRO: " +ex.getMessage() );
        }
        finally
        {
            DatabaseConfiguration.closeConnection( DatabaseConfiguration.con );
        }

        return null;
    }
    
    public List<Adocao> getBuscarAdocoes()
    {
        List<Adocao> adocoes = new ArrayList<>();

        try
        {
            DatabaseConfiguration.abreConexao();

            String wSql = "SELECT * FROM adocoes order by id";

            PreparedStatement stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            ResultSet rs = stmt.executeQuery();

            while ( rs.next() )
            {
                Adocao adocao = new Adocao();
                adocao.setId( rs.getInt( "id" ) );
                adocao.setPetId( rs.getInt("petId" ) );
                adocao.setTutorId(rs.getInt( "tutorId" ) );
                adocao.setStatusAdocao( Adocao.StatusAdocao.values()[ rs.getInt( "statusAdocao") ] );
                adocao.setDataAdocao( rs.getDate("dataAdocao")!= null ? rs.getDate("dataAdocao").toLocalDate(): null );
                adocao.setDataSolicitacao( rs.getDate("dataSolicitacao") != null ? rs.getDate("dataSolicitacao").toLocalDate() : null );
                adocao.setInfo( rs.getString( "info" ) );
                adocao.setJustificativa( rs.getString("justificativa") );

               adocoes.add( adocao );
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

        return adocoes;
    }
    
    /**
     * 
     * @param statusAdocao
     * @return
     */
    public List<Tutor> getTutores( Adocao.StatusAdocao... statusAdocao )
    {
        
        List tutorList = new ArrayList();
        
        try
        {
            TutorService service = new TutorService();

            DatabaseConfiguration.abreConexao();
            PreparedStatement stmt;
            ResultSet rs;

            String wSql = "SELECT DISTINCT tutorId FROM adocoes WHERE dataAdocao is null";

            if ( statusAdocao != null )
            {
                for ( Adocao.StatusAdocao status : statusAdocao )
                {
                    wSql += " or statusadocao = ?";
                }
            }
            
            wSql += " order by tutorId";
             
            stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            int i = 1;
            
            if ( statusAdocao != null )
            {
                for ( Adocao.StatusAdocao status : statusAdocao )
                {
                   stmt.setInt(i, status.ordinal() );
                   i++;
                }
            }
            
            rs = stmt.executeQuery();

            while ( rs.next() )
            {
                Tutor tutor = service.getTutorById( rs.getInt("tutorId") );
                
                tutorList.add( tutor );
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

        return tutorList;
    }
    
    /**
     * 
     * @param statusAdocao
     * @return
     */
    public List<Pet> getPets( Adocao.StatusAdocao... statusAdocao )
    {
        List petList = new ArrayList();
        
        try
        {
            PetService service = new PetService();

            DatabaseConfiguration.abreConexao();
            PreparedStatement stmt;
            ResultSet rs;

            String wSql = "SELECT DISTINCT petId FROM adocoes WHERE dataAdocao is null";

            if ( statusAdocao != null )
            {
                for ( Adocao.StatusAdocao status : statusAdocao )
                {
                    wSql += " or statusAdocao = ?";
                }
            }
            
            
            stmt = DatabaseConfiguration.con.prepareStatement(wSql);

            int i = 1;
            
            if ( statusAdocao != null )
            {
                for ( Adocao.StatusAdocao status : statusAdocao )
                {
                    stmt.setInt(i, status.ordinal() );
                    i++;
                }
            }
            
           
            rs = stmt.executeQuery();

            while ( rs.next() )
            { 
                Pet pet = service.getPetById( rs.getInt("petId") );
                
                petList.add( pet );
            }
        }
        catch ( Exception e )
        {
            System.out.println( "ERRO: " + e.getMessage() );
        }
        finally
        {
            DatabaseConfiguration.closeConnection( DatabaseConfiguration.con );
        }
        
        return petList;
    }
    
    private int nextId()
    {
        String wSql = "SELECT MAX(id) from adocoes;";

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

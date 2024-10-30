package server.controller;

import java.util.ArrayList;
import java.util.List;

import server.db.service.AdocaoService;
import server.model.Adocao;
import server.model.Pet;
import server.model.Tutor;

public class AdocaoController {

    public AdocaoService service = new AdocaoService();

    /**
     * 
     * @param adocao
     * @return
     */
    public String cadastrar( Adocao adocao )
    {
        try 
        {
            service.add( adocao );
        } 
        catch ( Exception e )
        {
            return "Erro ao registar adoção \n"
                    +"[ERRO] " + e.getMessage();
        }

        return "Adoção registrada com sucesso.";

    }

    /**
     * 
     * @param adocao
     * @return
     */
    public String atualizar( Adocao adocao )
    {
        try 
        {
            service.update( adocao );
        } 
        catch ( Exception e ) 
        {
            return "Erro ao atualizar registro de adoção \n"
                    + "[ERRO] " + e.getMessage();
        }

        return "Registro atualizado com sucesso";
    }

    /**
     * 
     * @param adocao
     * @return
     */
    public String excluir( Adocao adocao )
    {
        try 
        {
            service.delete( adocao );    
        } 
        catch ( Exception e ) 
        {
            return "Erro ao excluir registro de adoção \n"
                    + "[ERRO] " + e.getMessage();
        }

        return "Registro de adoção excluido com sucesso";
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
            return service.getAdocaoById( id );
        } 
        catch ( Exception e ) 
        {
            System.out.println( "Erro ao buscar pela adoção com o ID informado [ERRO] " + e.getMessage() );
            return null;
        }
    }

    public List<Adocao> getAdocoes()
    {
        try 
        {
            return service.getBuscarAdocoes();
        } 
        catch ( Exception e ) 
        {
            System.out.println( "Erro ao buscar pelas adoções " + e.getMessage() );
            return new ArrayList<>();
        }
    }
        
    /**
     * 
     * @param statusAdocao
     * @return
     */
    public List<Tutor> getTutoresByStatus( Adocao.StatusAdocao... statusAdocao )
    {
        try 
        {
            return service.getTutores( statusAdocao );
        } 
        catch ( Exception e ) 
        {
            System.out.println( "Erro ao buscar por tutores, [ERRO] " + e.getMessage() );
            return  new ArrayList<>();
        }
    }

    /**
     * 
     * @param statusAdocao
     * @return
     */
    public List<Pet> getPets( Adocao.StatusAdocao... statusAdocao )
    {
        try 
        {
            return service.getPets( statusAdocao );    
        } 
        catch ( Exception e )
        {
            System.out.println( "Erro ao buscar por pets, [ERRO] " + e.getMessage() );
            return new ArrayList<>();
        }
    }
    

}

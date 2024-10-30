package server.controller;

import server.db.service.PetService;
import server.model.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mariele Huff
 */
public class PetController
{
    private final PetService service = new PetService();

    /**
     * PetController
     *
     */
    public PetController() {};

    /**
     * cadastrar
     *
     * @param pet Pet
     * @return String
     */
    public String cadastrar( Pet pet )
    {
        try
        {
            service.add( pet );
        }

        catch ( Exception e )
        {
            return( "Erro ao cadastrar pet: " + e.getMessage() );
        }

        return "Pet cadatrado com sucesso!" ;
    }

    /**
     * buscarTodosPets
     *
     * @return  List<Pet>
     */
    public List<Pet> buscarTodosPets()
    {
        List<Pet> pets = new ArrayList<>();

        try
        {
            return service.getPets();
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao buscar os pets" );
            return null;
        }
    }
    
    /**
     * buscarTodosPetsDisponiveis
     *
     * @return  List<Pet>
     */
    public List<Pet> buscarTodosPetsDisponiveis()
    {
        List<Pet> pets = new ArrayList<>();

        try
        {
            return service.getPetsDisponiveis();
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao buscar os pets" );
            return null;
        }
    }

    /**
     * buscarPetPorId
     *
     * @param id int
     * @return Pet
     */
    public Pet buscarPetPorId( int id )
    {
        try
        {
            return service.getPetById( id );
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao buscar pet com o id " + id );
            return null;
        }
    }
    
    /**
     * buscarPetPorId
     *
     * @param String filtro
     * @return Pet
     */
    public List<Pet> buscarPetPorFiltro( String filtro )
    {
        try
        {
            return service.getPetsByFiltro( filtro );
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao buscar pet com o filtro informado " + filtro );
            return null;
        }
    }

    /**
     * deletarRegistro
     *
     * @param pet Pet
     * @return String
     */
    public String deletarRegistro( Pet pet )
    {
        try
        {
            service.delete( pet );
        }

        catch ( Exception e )
        {
            return "Erro ao deletar o pet: " + e.getMessage();
        }

        return "Pet deletado com sucesso!";
    }

    /**
     * atualizarPet
     *
     * @param pet Pet
     * @return String
     */
    public String atualizarPet( Pet pet )
    {
        try
        {
            service.update( pet );
        }

        catch ( Exception e )
        {
            return "Erro ao atualizar o pet: " + e.getMessage();
        }

        return "Pet atualizado com sucesso!";
    }
}

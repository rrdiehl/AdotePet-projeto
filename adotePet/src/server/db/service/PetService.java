package server.db.service;

import server.db.Transactions.PetTansactions;
import server.db.repository.PetRepository;
import server.model.Pet;

import java.util.List;
import server.model.Adocao;

/**
 *
 * @author Mariele Huff
 */
public class PetService
    implements
        PetRepository
{

    private PetTansactions transaction = new PetTansactions();

    /**
     * add
     *
     * @param pet Pet
     */
    @Override
    public void add( Pet pet ) throws Exception
    {
        boolean petAlreadyExist = transaction.getPetById( pet.getId() ) != null;

        if ( petAlreadyExist )
        {
            throw new Exception( "O id do pet já é utilizado em outro registro!" );
        }

        else
        {
            transaction.add(pet);
        }
    }

    /**
     * update
     *
     * @param pet Pet
     */
    @Override
    public void update( Pet pet ) throws Exception
    {
        boolean petNotExist = transaction.getPetById( pet.getId() ) == null;

        if ( petNotExist )
        {
            throw new Exception( "Registro do pet não existe!" );
        }

        else
        {
            transaction.update(pet);
        }
    }

    /**
     * delete
     *
     * @param pet Pet
     */
    @Override
    public void delete( Pet pet ) throws Exception
    {
        AdocaoService adocaoService = new AdocaoService();
        
        List<Pet> pets = adocaoService.getPets( Adocao.StatusAdocao.AGUARDANDO_AVALIACAO );
        
        boolean hasPet = pets.stream().allMatch( p -> p.getId() == pet.getId() );
        
        boolean petNotExist = transaction.getPetById( pet.getId() ) == null;

        if ( petNotExist )
        {
            throw new Exception( "Registro do pet não existe!" );
        }
        
        else if( hasPet )
        {
            throw new Exception( "Não é possível deletar o pet, pois ele esta em processo de adoção." );
        }

        else
        {
            transaction.delete( pet );
        }
    }

    /**
     * getPetById
     *
     * @param id int
     * @return Pet
     */
    @Override
    public Pet getPetById(int id)
    {
        return transaction.getPetById( id );
    }

    /**
     * getPets
     *
     * @return List<Pet>
     */
    @Override
    public List<Pet> getPets()
    {
      return transaction.getPets();
    }

    /**
     * getPetsDisponiveis
     * 
     * @return List<Pet>
     */
    @Override
    public List<Pet> getPetsDisponiveis() 
    {
       return transaction.getPetsDisponiveis();
    }

    @Override
    public List<Pet> getPetsByFiltro( String filtro ) 
    {
        return transaction.getPetsByFiltro( filtro );
    }
}

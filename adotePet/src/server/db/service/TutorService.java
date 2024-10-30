package server.db.service;

import server.db.Transactions.TutorTransactions;
import server.db.repository.TutorRepository;
import server.model.Tutor;

import java.util.List;
import server.model.Adocao;

/**
 *
 * @author Mariele Huff
 */
public class TutorService
    implements
        TutorRepository
{

    private TutorTransactions transaction = new TutorTransactions();

    /**
     * add
     *
     * @param tutor Tutor
     */
    @Override
    public void add( Tutor tutor ) throws Exception
    {
        boolean tutorAlreadyExist = transaction.getTutorById( tutor.getId() ) != null;

        if ( tutorAlreadyExist )
        {
            throw new Exception( "O id do tutor já é utilizado em outro registro!" );
        }

        else
        {
            transaction.add( tutor );
        }
    }

    /**
     * update
     *
     * @param tutor Tutor
     */
    @Override
    public void udpate( Tutor tutor ) throws Exception
    {
        boolean tutorNotExist = transaction.getTutorById( tutor.getId() ) == null;

        if ( tutorNotExist )
        {
            throw new Exception( "Não foi possível atualizar o tutor pois ele não existe!");
        }

        else
        {
            transaction.update( tutor );
        }
    }

    /**
     * delete
     *
     * @param tutor Tutor
     */
    @Override
    public void delete( Tutor tutor ) throws Exception
    {
        AdocaoService adocaoService = new AdocaoService();
        
        List<Tutor> tutores = adocaoService.getTutores(Adocao.StatusAdocao.AGUARDANDO_AVALIACAO );
        
        boolean hasPet = tutores.stream().allMatch( t -> t.getId() == tutor.getId() );
        
        boolean tutorNotExist = transaction.getTutorById( tutor.getId() ) == null;

        if ( tutorNotExist )
        {
            throw new Exception( "Não foi possível deletar o tutor pois ele não existe!");
        }
        
        else if ( hasPet )
        {
            throw new Exception( "Não é possível deletar o tutor, pois ele tem um ou mais processos de adoção em andamento." );
        }

        else
        {
            transaction.delete( tutor );
        }
    }

    /**
     * getTutorById
     *
     * @param id int
     * @return Tutor
     */
    @Override
    public Tutor getTutorById( int id )
    {
        return transaction.getTutorById( id );
    }

    /**
     * getTutores
     *
     * @return List<Tutor>
     */
    @Override
    public List<Tutor> getTutores()
    {
        return transaction.getTutores();
    }

    /**
     * getTutoresDisponiveis
     * 
     * @return List<Tutor>
     */
    @Override
    public List<Tutor> getTutoresDisponiveis() 
    {
        return transaction.getTutoresDisponiveis();
    }
}

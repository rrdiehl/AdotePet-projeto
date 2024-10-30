package server.db.service;

import java.util.List;
import server.db.Transactions.AdocaoTransactions;
import server.db.repository.AdocaoRepository;
import server.model.Adocao;
import server.model.Pet;
import server.model.Tutor;

public class AdocaoService 
    implements 
        AdocaoRepository
{
    
    private AdocaoTransactions transaction = new AdocaoTransactions();

    @Override
    public void add( Adocao adocao ) throws Exception 
    {
        transaction.add( adocao );
    }

    @Override
    public void update( Adocao adocao ) throws Exception
    {

        boolean isOk = transaction.getAdocaoById( adocao.getId() ) != null;

        if( isOk )
        {
            transaction.update( adocao );
        }
        else
        {
            throw new Exception( "Adoção não registrada com o id informado ID [ "+ adocao.getId()+" ] " );
        }

    }

    @Override
    public void delete( Adocao adocao ) throws Exception
    {
        boolean isOk = transaction.getAdocaoById( adocao.getId() ) != null;

        if( isOk )
        {
            transaction.delete( adocao );
        }
        else
        {
            throw new Exception( "Adoção não registrada com o id informado ID [ "+ adocao.getId()+" ] " );
        }
    }
    
    @Override
    public Adocao getAdocaoById( int id ) 
    {
        return transaction.getAdocaoById( id );
    }
    
    @Override
    public List<Adocao> getBuscarAdocoes() 
    {
       return transaction.getBuscarAdocoes();
    }

    @Override
    public List<Tutor> getTutores( Adocao.StatusAdocao... statusAdocao ) 
    {
        return transaction.getTutores( statusAdocao );
    }

    @Override
    public List<Pet> getPets( Adocao.StatusAdocao... statusAdocao ) 
    {
        return transaction.getPets( statusAdocao );
    }
}

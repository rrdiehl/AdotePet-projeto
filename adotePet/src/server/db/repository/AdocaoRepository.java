package server.db.repository;

import server.model.Adocao;
import server.model.Pet;
import server.model.Tutor;

import java.util.List;

/**
 *
 * @author Mariele Huff
 */
public interface AdocaoRepository
{
    void add( Adocao adocao ) throws Exception;
    void update( Adocao adocao ) throws Exception;
    void delete( Adocao adocao ) throws Exception;
    
    Adocao getAdocaoById( int id ); 
    List<Adocao> getBuscarAdocoes();
    List<Tutor> getTutores( Adocao.StatusAdocao... statusAdocao );
    List<Pet> getPets( Adocao.StatusAdocao... statusAdocao );
}
package server.db.repository;

import server.model.Pet;

import java.util.List;

/**
 *
 * @author Mariele Huff
 */
public interface PetRepository
{
    void add( Pet pet ) throws Exception;
    void update( Pet pet ) throws Exception;
    void delete( Pet pet ) throws Exception;

    Pet getPetById( int id );
    List<Pet> getPets();
    List<Pet> getPetsDisponiveis();
    List<Pet> getPetsByFiltro( String filtro );
}

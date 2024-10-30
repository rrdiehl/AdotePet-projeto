package server.db.repository;

import server.model.Tutor;

import java.util.List;

/**
 *
 * @author Mariele Huff
 */
public interface TutorRepository
{
    void add( Tutor tutor ) throws Exception;
    void udpate( Tutor tutor ) throws Exception;
    void delete( Tutor tutor ) throws Exception;

    Tutor getTutorById( int id );
    List<Tutor> getTutores();
    List<Tutor> getTutoresDisponiveis();
}

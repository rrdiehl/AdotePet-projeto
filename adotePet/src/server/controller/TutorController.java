package server.controller;

import server.db.service.TutorService;
import server.model.Tutor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mariele Huff
 */
public class TutorController
{
    private final TutorService service = new TutorService();

    /**
     * TutorController
     *
     */
    public TutorController() {};

    /**
     * cadastrar
     *
     * @param tutor Tutor
     * @return String
     */
    public String cadastrar( Tutor tutor )
    {
        try
        {
            service.add( tutor );
        }

        catch ( Exception e )
        {
            return( "Erro ao cadastrar tutor: " + e.getMessage() );
        }

        return "tutor cadatrado com sucesso!" ;
    }

    /**
     * buscarTodostutors
     *
     * @return  List<tutor>
     */
    public List<Tutor> buscarTodosTutores()
    {
        List<Tutor> tutors = new ArrayList<>();

        try
        {
            tutors = service.getTutores();
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao buscar os tutors" );
        }
        
        return tutors;
    }
    
    /**
     * buscarTodosTutoresDisponiveis
     *
     * @return  List<tutor>
     */
    public List<Tutor> buscarTodosTutoresDisponiveis()
    {
        List<Tutor> tutors = new ArrayList<>();

        try
        {
            tutors = service.getTutoresDisponiveis();
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao buscar os tutors" );
        }
        
        return tutors;
    }

    /**
     * buscarTutorPorId
     *
     * @param id int
     * @return Tutor
     */
    public Tutor buscartTtorPorId( int id )
    {
        try
        {
            return service.getTutorById( id );
        }

        catch ( Exception e )
        {
            System.out.println( "Erro ao buscar tutor com o id " + id );
            return null;
        }
    }

    /**
     * deletarRegistro
     *
     * @param tutor Tutor
     * @return String
     */
    public String deletarRegistro( Tutor tutor )
    {
        try
        {
            service.delete( tutor );
        }

        catch ( Exception e )
        {
            return "Erro ao deletar o tutor: " + e.getMessage();
        }

        return "tutor deletado com sucesso!";
    }

    /**
     * atualizarTutor
     *
     * @param tutor tutor
     * @return String
     */
    public String atualizarTutor( Tutor tutor )
    {
        try
        {
            service.udpate( tutor );
        }

        catch ( Exception e )
        {
            return "Erro ao atualizar o tutor: " + e.getMessage();
        }

        return "tutor atualizado com sucesso!";
    }
}

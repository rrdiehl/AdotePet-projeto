package server.db.repository;

import java.util.List;
import server.model.Login;

/**
 *
 * @author rrdiehl
 */
public interface LoginRepository
{
    void add( Login login ) throws Exception;
    void udpate( Login login ) throws Exception;
    void delete( Login login ) throws Exception;

    Login getLoginById( int id );
    List<Login> getLogin();
}

package server.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mariele Huff
 */
public class Tutor
{
    private int id;

    private String nome;
    private String email;

    private LocalDateTime dataDeRegistro;

    private List<Adocao> adocoes = new ArrayList<>();

    /**
     * Tutor
     */
    public Tutor() {}

    /**
     * Tutor
     *
     * @param id int
     * @param nome String
     * @param email String
     * @param adocoes List<Adocao>
     */
    public Tutor( int id, String nome, String email, List<Adocao> adocoes )
    {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.adocoes = adocoes;
    }

    /**
     * getId
     *
     * @return int
     */
    public int getId()
    {
        return id;
    }

    /**
     * setId
     *
     * @param id int
     */
    public void setId( int id )
    {
        this.id = id;
    }

    /**
     * getNome
     *
     * @return String
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * setNome
     *
     * @param nome
     */
    public void setNome( String nome )
    {
        this.nome = nome;
    }

    /**
     * getEmail
     *
     * @return String
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * setEmail
     *
     * @param email String
     */
    public void setEmail( String email )
    {
        this.email = email;
    }

    /**
     * getDataDeRegistro
     *
     * @return LocalDateTime
     */
    public LocalDateTime getDataDeRegistro()
    {
        return dataDeRegistro;
    }

    /**
     * setDataDeRegistro
     *
     * @param dataDeRegistro LocalDateTime
     */
    public void setDataDeRegistro( LocalDateTime dataDeRegistro )
    {
        this.dataDeRegistro = dataDeRegistro;
    }

    /**
     * getAdocoes
     *
     * @return List<Adocao>
     */
    public List<Adocao> getAdocoes()
    {
        return adocoes;
    }

    /**
     * setAdocoes
     *
     * @param adocoes List<Adocao>
     */
    public void setAdocoes( List<Adocao> adocoes )
    {
        this.adocoes = adocoes;
    }
}

package server.model;

import java.time.LocalDateTime;

/**
 *
 * @author Mariele Huff
 */
public class Pet
{
    public enum TipoPet
    {
        GATO,
        CACHORRO
    }

    public enum TipoPorte
    {
        PEQUENO,
        MEDIO,
        GRANDE
    }

    private int id;
    private int idade;

    private LocalDateTime dataDeRegistro;

    private String nome;
    private String imagem;

    private TipoPet tipoPet;
    private TipoPorte tipoPorte;


    /**
     * Pet
     */
    public Pet() {}

    /**
     * Pet
     *
     * @param id int
     * @param idade int
     * @param nome String
     * @param imagem Stirng
     * @param tipoPet TipoPet
     * @param tipoPorte TipoPorte
     */
    public Pet( int id, int idade, String nome, String imagem, TipoPet tipoPet, TipoPorte tipoPorte )
    {
        this.id = id;
        this.idade = idade;
        this.nome = nome;
        this.imagem = imagem;
        this.tipoPet = tipoPet;
        this.tipoPorte = tipoPorte;
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
     * getIdade
     *
     * @return int
     */
    public int getIdade()
    {
        return idade;
    }

    /**
     * setIdade
     *
     * @param idade int
     */
    public void setIdade( int idade )
    {
        this.idade = idade;
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
     * @param nome String
     */
    public void setNome( String nome )
    {
        this.nome = nome;
    }

    /**
     * getImagem
     *
     * @return String
     */
    public String getImagem()
    {
        return imagem;
    }

    /**
     * setImagem
     *
     * @param imagem String
     */
    public void setImagem( String imagem )
    {
        this.imagem = imagem;
    }

    /**
     * TipoPet
     *
     * @return TipoPet
     */
    public TipoPet getTipoPet()
    {
        return tipoPet;
    }

    /**
     * setTipoPet
     *
     * @param tipoPet TipoPet
     */
    public void setTipoPet( TipoPet tipoPet )
    {
        this.tipoPet = tipoPet;
    }

    /**
     * getTipoPorte
     *
     * @return TipoPorte
     */
    public TipoPorte getTipoPorte()
    {
        return tipoPorte;
    }

    /**
     * setTipoPorte
     *
     * @param tipoPorte TipoPorte
     */
    public void setTipoPorte( TipoPorte tipoPorte )
    {
        this.tipoPorte = tipoPorte;
    }
}

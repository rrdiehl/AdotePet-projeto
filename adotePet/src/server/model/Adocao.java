package server.model;

import java.time.LocalDate;

/**
 *
 * @author Mariele Huff
 *         Luis Carlos K.
 */
public class Adocao
{
    public enum StatusAdocao
    {
        AGUARDANDO_AVALIACAO,
        APROVADO,
        REPROVADO
    }
    
    private int id;

    private int tutorId;
    private int petId;
    private StatusAdocao statusAdocao;
    private LocalDate dataSolicitacao;
    private LocalDate dataAdocao;

    private String info;
    private String justificativa;

    public Adocao() {}

    /**
     * Adocao
     *
     * @param id int
     * @param tutor Tutor
     * @param pet Pet
     * @param info String
     * @param justificativa String
     */
    public Adocao( int id, int tutorId, int petId, LocalDate dataSolicitacao, LocalDate dataAdocao, String info, String justificativa )
    {
        this.id = id;
        this.tutorId = tutorId;
        this.petId = petId;
        this.dataSolicitacao = dataSolicitacao;
        this.dataAdocao = dataAdocao;
        this.info = info;
        this.justificativa = justificativa;
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
     * getTutor
     *
     * @return Tutor
     */
    public int getTutorId()
    {
        return tutorId;
    }

    /**
     * setTutor
     *
     * @param tutor Tutor
     */
    public void setTutorId( int tutorId )
    {
        this.tutorId = tutorId;
    }

    /**
     * getPet
     *
     * @return Pet
     */
    public int getPetId()
    {
        return petId;
    }

    /**
     * setPet
     *
     * @param pet Pet
     */
    public void setPetId( int petId )
    {
        this.petId = petId;
    }
    
    public LocalDate getDataSolicitacao() 
    {
        return dataSolicitacao;
    }
        
    /**
     * 
     * @param dataSolicitacao 
     */
    public void setDataSolicitacao( LocalDate dataSolicitacao ) 
    {
        this.dataSolicitacao = dataSolicitacao;
    }
    
    /**
     * 
     * @return LocalDate
     */
    public LocalDate getDataAdocao() 
    {
        return dataAdocao;
    }
    
    /**
     * 
     * @param dataAdocao 
     */
    public void setDataAdocao( LocalDate dataAdocao ) 
    {
        this.dataAdocao = dataAdocao;
    }

    /**
     * getStatusAdocao
     *
     * @return StatusAdocao
     */
    public StatusAdocao getStatusAdocao() {return statusAdocao;}

    /**
     * setStatusAdocao
     *
     * @param statusAdocao StatusAdocao
     */
    public void setStatusAdocao(StatusAdocao statusAdocao) {this.statusAdocao = statusAdocao;}

    /**
     * getInfo
     *
     * @return String
     */
    public String getInfo()
    {
        return info;
    }

    /**
     * setInfo
     *
     * @param info String
     */
    public void setInfo( String info )
    {
        this.info = info;
    }

    /**
     * getJustificativa
     *
     * @return Sting
     */
    public String getJustificativa()
    {
        return justificativa;
    }

    /**
     * setJustificativa
     *
     * @param justificativa String
     */
    public void setJustificativa( String justificativa )
    {
        this.justificativa = justificativa;
    }
}

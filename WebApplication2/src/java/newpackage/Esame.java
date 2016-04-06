/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

/**
 *
 * @author studente
 */
public class Esame {
    private String nomeStudente = "";
    private String cognomeStudente = "";
    private int matricolaStudente = 0;
    private String nomeEsame = "";
    private int voto = 0;
    private String nota = "_";

    public Esame () {
    nomeStudente = "";
    cognomeStudente = "";
    matricolaStudente = 0;
    nomeEsame = "";
    voto = 0;
    nota = "_";
    }
    
    /**
     * @return the nomeStudente
     */
    public String getNomeStudente() {
        return nomeStudente;
    }

    /**
     * @param nomeStudente the nomeStudente to set
     */
    public void setNomeStudente(String nomeStudente) {
        this.nomeStudente = nomeStudente;
    }

    /**
     * @return the cognomeStudente
     */
    public String getCognomeStudente() {
        return cognomeStudente;
    }

    /**
     * @param cognomeStudente the cognomeStudente to set
     */
    public void setCognomeStudente(String cognomeStudente) {
        this.cognomeStudente = cognomeStudente;
    }

    /**
     * @return the matricolaStudente
     */
    public int getMatricolaStudente() {
        return matricolaStudente;
    }

    /**
     * @param matricolaStudente the matricolaStudente to set
     */
    public void setMatricolaStudente(int matricolaStudente) {
        this.matricolaStudente = matricolaStudente;
    }

    /**
     * @return the nomeEsame
     */
    public String getNomeEsame() {
        return nomeEsame;
    }

    /**
     * @param nomeEsame the nomeEsame to set
     */
    public void setNomeEsame(String nomeEsame) {
        this.nomeEsame = nomeEsame;
    }

    /**
     * @return the voto
     */
    public int getVoto() {
        return voto;
    }

    /**
     * @param voto the voto to set
     */
    public void setVoto(int voto) {
        this.voto = voto;
    }

    /**
     * @return the nota
     */
    public String getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(String nota) {
        this.nota = nota;
    }
}

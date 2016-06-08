package com.stampantiSrl.classi;
/**
 *
 * @author Luigi Deidda
 * 
 */
public class Cliente extends Account {
    private String nome, cognome;
    private String codiceFiscale;
    
    /**
     *
     * @param cf
     */
    public Cliente(String cf){
        super();
        codiceFiscale = cf;
    }
    /**
     * @param nome nome da impostare
     * @param cognome cognome da impostare
     */
    public void setNomeCognome(String nome, String cognome){
        this.nome = nome;
        this.cognome = cognome;
    }
    /**
     * @param codiceFiscale codice fiscale da impostare
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    /**
     * @return codice fiscale
     */
    public String getCF(){
        return codiceFiscale;
    }
    /**
     * @return nome
     */
    public String getNome(){
        return nome;
    }
    /**
     * @return cognome
     */
    public String getCognome(){
        return cognome;
    }
    @Override
    public String toString(){
        return getClass().getName() + 
                "[nome=" + nome + "; " +
                "cognome=" + cognome + "; " +
                "codice fiscale=" + codiceFiscale + "]";
    }
    @Override
    public boolean equals(Object o){
        Cliente cliente = (Cliente) o;
        if (codiceFiscale != null && cliente.codiceFiscale != null
                && getClass().equals(cliente.getClass()))
            return codiceFiscale.equals(cliente.codiceFiscale);
        else return false;
    }
    @Override
    public int hashCode() {
        return codiceFiscale.hashCode();
    }  
}

package com.stampantiSrl.classi;
/**
 *
 * @author Luigi Deidda
 */
public class Venditore extends Account{
    private String ragioneSociale;
    private String p_iva;
    
    public Venditore(String ragioneSociale){
        super();
        this.ragioneSociale = ragioneSociale;
    }
    /**
     * @param p_iva partita iva da impostare
     */
    public void setP_iva(String p_iva){
        this.p_iva = p_iva;
    }
    /**
     * @param ragioneSociale ragione sociale da impostare
     */
     public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }
     /**
     * @return ragione sociale
     */
    public String getRagioneSociale(){
        return ragioneSociale;
    }
    /**
     * @return partita iva
     */
    public String getP_iva(){
        return p_iva;
    }
    @Override
    public String toString(){
        return getClass().getName() + 
                "[ragione sociale=" + ragioneSociale + "; " +
                "partita iva=" + p_iva + "]";
    }
    @Override
    public boolean equals(Object o){
        Venditore venditore = (Venditore) o;
        if (ragioneSociale != null && venditore.ragioneSociale != null
                && getClass().equals(venditore.getClass()))
            return ragioneSociale.equals(venditore.ragioneSociale);
        else return false;
    }
    @Override
    public int hashCode() {
        return ragioneSociale.hashCode();
    }  
}

package com.stampantiSrl.classi;
/**
 *
 * @author Luigi Deidda
 */
public abstract class Account {
    private int id;
    private String username;
    private String password;
    private String tipoUtente;
    public Account(){
    }
    /**
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id id da impostare
     */
    public void setId(int id) {
        this.id = id;
    }   
    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username username da impostare
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password  password da impostare
     */
    public void setPassword(String password) {
        this.password = password;
    }    
    /**
     * @return tipo utente
     */
    public String getTipoUtente() {
        return tipoUtente;
    }
    /**
     * @param tipoUtente  tipo utente da impostare
     */
    public void setTipoUtente(String tipoUtente) {
        this.tipoUtente = tipoUtente;
    }    
    @Override
    public boolean equals(Object o){
        Account account = (Account) o;
        if ( this.getClass().equals(account.getClass())) 
            return id == account.id;
        else return false;
    }
    @Override
    public int hashCode() {
        return id;
    }  
}


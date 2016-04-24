/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stampantiSrl.classi;

/**
 *
 * @author Luigi Deidda
 */
public abstract class Account {
    private int id;
    private static int progressive_id = 0;
    private String username;
    private String password;
    protected enum TipoAccount {VENDITORE, CLIENTE};
    public Account(){
        progressive_id++;
        id = progressive_id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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


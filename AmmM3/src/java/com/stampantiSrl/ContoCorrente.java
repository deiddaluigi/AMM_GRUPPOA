/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stampantiSrl;

/**
 *
 * @author Luigi Deidda
 */
public class ContoCorrente {
    private int numeroConto;
    private int codiceAccessoConto;
    private static int progressivo_nConto = 1000;
    private double saldo; // importi espressi in Euro
    
    /**Crea un nuovo conto e assegna un numero di conto associato al codice di Accesso 
     * inserito come parametro.
     * @param codiceAccesso
     * @param saldoIniziale*/
    public ContoCorrente(int codiceAccesso, double saldoIniziale) {
        codiceAccessoConto = codiceAccesso;
        saldo = saldoIniziale;
        progressivo_nConto++;
        numeroConto = progressivo_nConto;        
    }
    public int getNumeroConto() {
        return numeroConto;
    }
    public void versamento(double importoVersamento){
        if (importoVersamento >= 0) {
            saldo += importoVersamento;
        } else throw new RuntimeException("Errore. L'importo della ricarica deve essere maggiore o uguale a zero.");
    }
    public void prelevaDaConto(int codiceAccesso, double importoPrelevamento){
        if (this.codiceAccessoConto == codiceAccesso) {
            if (importoPrelevamento <= saldo)
                saldo -= importoPrelevamento;
            else throw new RuntimeException("Errore. L'importo da prelevare supera il saldo del conto.");
        } else throw new RuntimeException("Errore. Codice di accesso al conto errato.");
    }
    public double getSaldo(){
        return saldo;
    }
    
}

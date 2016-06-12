package com.stampantiSrl.classi;

/**
 *
 * @author Luigi Deidda
 */
public class ContoCorrente {
    private int id;
    private int numeroConto;
    private int codiceAccessoConto;
    private double saldo; // importi espressi in Euro
    
    /**Crea un nuovo conto con relativo codice di Accesso e saldo iniziale
     * inseriti come parametri.
     * @param codiceAccesso
     * @param saldoIniziale
     */
    public ContoCorrente(int codiceAccesso, double saldoIniziale) {
        codiceAccessoConto = codiceAccesso;
        saldo = saldoIniziale;       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setNumeroConto(int numeroConto) {
        this.numeroConto = numeroConto;
    }
    public int getNumeroConto() {
        return numeroConto;
    }
    public void setCodiceAccessoConto(int codiceAccessoConto) {
        this.codiceAccessoConto = codiceAccessoConto;
    }
    public void versamento(double importoVersamento){
        if (importoVersamento >= 0) {
            saldo += importoVersamento;
        } else throw new RuntimeException("Errore. L'importo del versamento deve essere maggiore o uguale a zero.");
    }
    public void prelevaDaConto(int codiceAccesso, double importoPrelevamento){
        if (this.codiceAccessoConto == codiceAccesso) {
            if (importoPrelevamento <= saldo){
                saldo -= importoPrelevamento;
                if (importoPrelevamento < 0) {
                    throw new RuntimeException("Errore. L'importo del pagamento deve avere un valore positivo.");
                }
            }
            else throw new RuntimeException("Errore. L'importo del pagamento supera il saldo del conto.");
        } else throw new RuntimeException("Errore. Codice di accesso al conto errato.");
    }
    public double getSaldo(){
        return saldo;
    }  
}

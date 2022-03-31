package it.personal.alexmarchi.tdd;

public class ContoCorrente {
    public double conto;

    public ContoCorrente(double conto) {
        this.conto = conto;
    }

    public void preleva(double prelievo) {
        if (conto - prelievo >= 0)
            conto -= prelievo;
        else
            throw new IllegalArgumentException("Non puoi prelevare quella somma");
    }

    public void deposita(double deposito) {
        conto += deposito;
    }


}

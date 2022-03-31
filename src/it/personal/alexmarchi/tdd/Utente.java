package it.personal.alexmarchi.tdd;

import java.util.Objects;

public class Utente {
    public String username;
    public String password;
    public ContoCorrente contoCorrente;

    public Utente(String username, String password) {
        this.username = username;
        this.password = password;
        contoCorrente = new ContoCorrente(0);
    }

    public Utente(String username, String password, ContoCorrente contoCorrente) {
        this(username, password);
        this.contoCorrente = contoCorrente;
    }

    public void deposita(double deposito) {
        contoCorrente.deposita(deposito);
    }

    public void preleva(double prelievo) throws IllegalArgumentException {
        contoCorrente.preleva(prelievo);
    }

    public void trasferisciDenaro(Utente destinatario, double denaroDaTrasferire) {
        try {
            contoCorrente.preleva(denaroDaTrasferire);
            destinatario.deposita(denaroDaTrasferire);
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Non puoi trasferire quella somma di denaro");
        }
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utente utente = (Utente) o;

        return Objects.equals(username, utente.username);
    }
}

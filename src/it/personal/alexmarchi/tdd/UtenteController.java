package it.personal.alexmarchi.tdd;

import java.util.ArrayList;
import java.util.List;

public class UtenteController {
    public final List<Utente> utenti;

    public UtenteController() {
        utenti = new ArrayList<>();
    }

    public void aggiungiUtente(Utente nuovoUtente) {
        if (!utentePresente(nuovoUtente))
            utenti.add(nuovoUtente);
        else
            throw new IllegalArgumentException("Utente già presente");
    }

    public Utente login(String username, String password) {
        for (Utente utente : utenti) {
            if (utente.login(username, password))
                return utente;
        }
        throw new IllegalArgumentException("Credenziali Errate");
    }

    public boolean utentePresente(Utente utente) {
        return utenti.contains(utente);
    }

    public Utente prendiUtenteDaUsername(String username) {
        for (Utente utente : utenti) {
            if (utente.username.equals(username))
                return utente;
        }
        throw new IllegalArgumentException("Utente non esiste");
    }
}

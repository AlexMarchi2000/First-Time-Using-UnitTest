package it.personal.alexmarchi.tdd.test;

import it.personal.alexmarchi.tdd.ContoCorrente;
import it.personal.alexmarchi.tdd.Utente;
import it.personal.alexmarchi.tdd.UtenteController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtenteControllerTest {
    UtenteController utenti = new UtenteController();

    @Test
    public void aggiungiUtente() {
        Utente nuovoUtente = new Utente("A", "0", new ContoCorrente(0));
        utenti.aggiungiUtente(nuovoUtente);
        assert !utenti.utenti.isEmpty();
        assert utenti.utenti.contains(nuovoUtente);
    }

    @Test
    public void aggiungiUtenteDuplicato() {
        Utente nuovoUtente = new Utente("A", "0", new ContoCorrente(0));
        Utente utenteConStessoUsername = new Utente("A", "011", new ContoCorrente(10));

        utenti.aggiungiUtente(nuovoUtente);
        assertThrows(IllegalArgumentException.class, () -> utenti.aggiungiUtente(utenteConStessoUsername));
        assert utenti.prendiUtenteDaUsername(nuovoUtente.username) == nuovoUtente;
    }

    @Test
    void prendiUtenteDaUsername() {
        Utente nuovoUtente = new Utente("A", "0", new ContoCorrente(0));
        utenti.aggiungiUtente(nuovoUtente);
        assert utenti.prendiUtenteDaUsername(nuovoUtente.username) == nuovoUtente;
        assertThrows(IllegalArgumentException.class, () -> utenti.prendiUtenteDaUsername("NomeUtenteNonPresente"));
    }

    @Test
    void utentePresente() {
        Utente nuovoUtente = new Utente("A", "0", new ContoCorrente(0));
        Utente utenteConStessoUsername = new Utente("A", "01", new ContoCorrente(1));

        utenti.aggiungiUtente(nuovoUtente);
        assert utenti.utentePresente(utenteConStessoUsername);
    }

    @Test
    void login(){
        String username = "A";
        String password = "0";
        Utente nuovoUtente = new Utente(username, password, new ContoCorrente(0));
        utenti.aggiungiUtente(nuovoUtente);

        assertThrows(IllegalArgumentException.class, () -> utenti.login("UsernameErrato", "PasswordErrata"));
        assertThrows(IllegalArgumentException.class, () -> utenti.login("UsernameErrato", password));
        assertThrows(IllegalArgumentException.class, () -> utenti.login(username, "PasswordErrata"));
        assert utenti.login(username, password) == nuovoUtente;
    }
}
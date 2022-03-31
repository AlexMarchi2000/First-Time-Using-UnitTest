package it.personal.alexmarchi.tdd.test;

import it.personal.alexmarchi.tdd.ContoCorrente;
import it.personal.alexmarchi.tdd.Utente;
import it.personal.alexmarchi.tdd.UtenteController;
import it.personal.alexmarchi.tdd.View;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {
    View view = new View();

    @Test
    void login(){
        String username = "A";
        String password = "0";
        Utente nuovoUtente = new Utente(username, password, new ContoCorrente(0));
        view.aggiungiUtente(nuovoUtente);

        assertThrows(IllegalArgumentException.class, () -> view.login("UsernameErrato", "PasswordErrata"));
        assertThrows(IllegalArgumentException.class, () -> view.login("UsernameErrato", password));
        assertThrows(IllegalArgumentException.class, () -> view.login(username, "PasswordErrata"));
        assert view.login(username, password) == nuovoUtente;
    }

    @Test
    void aggiungiUtente() {
        String username = "A";
        Utente nuovoUtente = new Utente(username, "0", new ContoCorrente(0));
        view.aggiungiUtente(nuovoUtente);
        assert view.prendiUtenteDaUsername(username) == nuovoUtente;
        assertThrows(IllegalArgumentException.class, () -> view.aggiungiUtente(nuovoUtente));
    }

    @Test
    void execute() {

    }

    @Test
    void registraUtente() {
        String username = "A";
        String password = "0";
        view.registraUtente(username, password);
        assert view.prendiUtenteDaUsername(username).equals(new Utente(username, password));
    }

    @Test
    void prendiUtenteDaUsername() {
        String username = "A";
        Utente nuovoUtente = new Utente(username, "0", new ContoCorrente(0));
        view.aggiungiUtente(nuovoUtente);
        assert view.prendiUtenteDaUsername(username) == nuovoUtente;
    }
}
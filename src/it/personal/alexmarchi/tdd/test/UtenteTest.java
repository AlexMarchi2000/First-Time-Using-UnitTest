package it.personal.alexmarchi.tdd.test;

import it.personal.alexmarchi.tdd.ContoCorrente;
import it.personal.alexmarchi.tdd.Utente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtenteTest {
    Utente utente = new Utente("AlexM28", "0000", new ContoCorrente(0.0));

    @Test
    void deposita() {
        utente.deposita(10);
        assert utente.contoCorrente.conto == 10;
    }

    @Test
    void preleva() {
        utente.deposita(10);
        utente.preleva(10);
        assert utente.contoCorrente.conto == 0;
        assertThrows(IllegalArgumentException.class, () -> utente.preleva(10));
        assert utente.contoCorrente.conto == 0;
    }

    @Test
    void trasferisci() {
        Utente utente2 = new Utente("Alex2", "0000", new ContoCorrente(0.0));
        assertThrows(IllegalArgumentException.class, () -> utente.trasferisciDenaro(utente2, 10));
        utente.deposita(10);
        utente.trasferisciDenaro(utente2, 5);
        assert utente.contoCorrente.conto == 5;
        assert utente2.contoCorrente.conto == 5;
    }

    @Test
    void testEquals() {
        Utente utente1 = new Utente("A", "0", new ContoCorrente(0));
        Utente utente2 = new Utente("A", "1", new ContoCorrente(1));
        assert utente1.equals(utente2);
    }
}
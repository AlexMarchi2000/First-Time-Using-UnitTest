package it.personal.alexmarchi.tdd.test;

import it.personal.alexmarchi.tdd.ContoCorrente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContoCorrenteTest {
    ContoCorrente contoCorrente = new ContoCorrente(0.0);

    @Test
    public void prelievo() {
        contoCorrente.deposita(10);
        contoCorrente.preleva(10);
        assert contoCorrente.conto == 0;
    }

    @Test
    public void prevelaSottoZero() {
        assertThrows(IllegalArgumentException.class, () -> contoCorrente.preleva(2));
    }

    @Test
    public void deposito() {
        contoCorrente.deposita(10);
        assert contoCorrente.conto == 10;
    }

}
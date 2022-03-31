package it.personal.alexmarchi.tdd;

import it.personal.alexmarchi.tdd.mylib.InputDati;
import it.personal.alexmarchi.tdd.mylib.MyMenu;

public class UtenteView {
    public static final String TITOLO_MENU = "Login View";
    public static final String[] VOCI_MENU = {
            "Visualizza saldo",
            "Preleva",
            "Deposita",
            "Trasferisci"
    };
    UtenteController utenteController;
    Utente utente;

    public UtenteView(Utente utente, UtenteController utenteController) {
        this.utenteController = utenteController;
        this.utente = utente;
    }

    public void execute() {
        MyMenu menu = new MyMenu(TITOLO_MENU, VOCI_MENU);
        int scelta;
        do {
            scelta = menu.scegli();
            switch (scelta) {
                case 1 -> visualizzaSaldo();
                case 2 -> preleva();
                case 3 -> deposita();
                case 4 -> trasferisci();
            }
        }while (scelta != 0);
    }

    private void visualizzaSaldo() {
        System.out.println("Saldo: " + utente.contoCorrente.conto);
    }

    private void trasferisci() {
        double importo = InputDati.leggiDoubleConMinimo("Importo da trasferire: ", 0);
        String usernameDestinatario = InputDati.leggiStringaNonVuota("Username destinatario: ");
        Utente destinatario;
        try {
            destinatario = utenteController.prendiUtenteDaUsername(usernameDestinatario);
            utente.trasferisciDenaro(destinatario, importo);
            System.out.println("Trasferimento avvenuto con successo");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deposita() {
        double deposito = InputDati.leggiDoubleConMinimo("Importo da depositare: ", 0);
        utente.deposita(deposito);
        System.out.println("Deposito avvenuto con successo");
    }

    private void preleva() {
        double prelievo = InputDati.leggiDoubleConMinimo("Importo da prelevare: ", 0);
        try {
            utente.preleva(prelievo);
            System.out.println("Prelievo avvenuto con successo");
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


}

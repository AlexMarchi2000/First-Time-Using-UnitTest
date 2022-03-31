package it.personal.alexmarchi.tdd;

import it.personal.alexmarchi.tdd.mylib.InputDati;
import it.personal.alexmarchi.tdd.mylib.MyMenu;

public class View {
    public static final String TITOLO_MENU = "Login View";
    public static final String[] VOCI_MENU = {
            "Registra Utente",
            "Accedi"
    };
    UtenteController utenteController = new UtenteController();


    public void execute() {
        MyMenu menu = new MyMenu(TITOLO_MENU, VOCI_MENU);
        int scelta;
        do {
            scelta = menu.scegli();
            switch (scelta) {
                case 1 -> registraUtente();
                case 2 -> login();
            }
        }while (scelta != 0);
    }

    private void registraUtente() {
        String username = InputDati.leggiStringaNonVuota("Username: ");
        String password = InputDati.leggiStringaNonVuota("Password: ");
        registraUtente(username, password);
    }

    private void login() {
        String username = InputDati.leggiStringaNonVuota("Username: ");
        String password = InputDati.leggiStringaNonVuota("Password: ");
        try {
            Utente utente = login(username, password);
            UtenteView utenteView = new UtenteView(utente, utenteController);
            utenteView.execute();
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public void registraUtente(String username, String password) {
        try {
            utenteController.aggiungiUtente(new Utente(username, password));
            System.out.println("Utente Registrato con successo");
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void aggiungiUtente(Utente utente) throws IllegalArgumentException {
        utenteController.aggiungiUtente(utente);
    }

    public Utente prendiUtenteDaUsername(String username) throws IllegalArgumentException {
        return utenteController.prendiUtenteDaUsername(username);
    }

    public Utente login(String username, String password) throws IllegalArgumentException {
        return utenteController.login(username, password);
    }


}

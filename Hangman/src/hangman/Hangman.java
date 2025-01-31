package hangman;

import java.util.Scanner;
import java.util.Random;

public class Hangman {

    public static void main(String[] args) {

        // Erstellung von drei Arrays mit verschiedenen Themen mit jeweils zehn Wörtern
        String[] hauptstaedte = {"Berlin", "London", "Paris", "Rom", "Amsterdam", "Tokio", "Teheran", "Wien",
            "Madrid", "Prag"};
        String[] laender = {"Deutschland", "England", "Frankreich", "Italien", "Spanien", "Niederlande", "Japan", "China",
            "Portugal", "Schweiz"};
        String[] programmierung = {"Vererbung", "Polymorphie", "Rekursion", "Kapselung", "Methode", "Konstruktor", "Variable",
            "Konstante", "Interface", "Attribut"};

        char buchstabe = ' '; // Variable für den Buchstaben
        String wort = " ";    // Variable für das gesuchte Wort 
        int i = 0; // Index für die Wörter im Array 
        boolean wortErraten = false;
        int zaehler; // Zähler für die richtigen Buchstaben
        int k = 0; // Zähler für die falschen Buchstaben
        int thema = 0; // Variable für die Themen

        // Erstellung eines neuen Scanner Objektes 
        Scanner eingabe = new Scanner(System.in);

        do {
            // Eingabeaufforderung
            System.out.print("Bitte wählen Sie ein Thema aus: \n");
            System.out.print("1: Hauptstädte \n");
            System.out.print("2: Länder \n");
            System.out.print("3: Programmierung \n");

            thema = eingabe.nextInt();

            if (thema < 1 || thema > 3) {
                System.out.print("Falsche Eingabe. \n");

            }
        } while (thema < 1 || thema > 3);

        // Erstellung eines neuen Random Objektes 
        Random zufall = new Random();

        // Es wird per Zufall die Position des zu erratenden Wortes ermittelt 
        i = zufall.nextInt(0, 10);

        switch (thema) {

            case 1: {
                // Das Wort an der Position i wird der Variable wort übergeben
                wort = hauptstaedte[i];
                break;
            }

            case 2: {

                wort = laender[i];
                break;
            }

            case 3: {

                wort = programmierung[i];
                break;
            }

        }

        // Deklaration einer Variable, die verbleibende Versuche speichert
        int anzahlVersuche = wort.length();

        // Erstellung eines Char-Arrays, der die geratenen Buchstaben speichert
        char[] gerateneBuchstaben = new char[wort.length()];

        // Befüllen des Char-Arrays mit Leerzeichen
        for (int j = 0; j < gerateneBuchstaben.length; j++) {
            gerateneBuchstaben[j] = ' ';
        }

        System.out.println();

        // Ausgabe der Leerzeichen zur Kontrolle
        for (int j = 0; j < gerateneBuchstaben.length; j++) {
            System.out.print(gerateneBuchstaben[j] + "  ");
        }

        // Erstellung eines Char-Arrays mit Unterstrichen
        char[] unterstriche = new char[wort.length()];

        // Befüllen des Char-Arrays mit Unterstrichen
        for (int j = 0; j < unterstriche.length; j++) {
            unterstriche[j] = '-';
        }

        System.out.println();

        // Ausgabe der Unterstriche zur Kontrolle
        for (int j = 0; j < unterstriche.length; j++) {
            System.out.print(unterstriche[j] + "  ");
        }

        System.out.println();

        // Erstellung eines Char-Arrays, der die falschen Buchstaben speichert
        char[] falscheBuchstaben = new char[wort.length()];

        // Befüllen des Char-Arrays mit Leerzeichen
        for (int j = 0; j < falscheBuchstaben.length; j++) {
            falscheBuchstaben[j] = ' ';
        }

        // Ausgabe der Leerzeichen zur Kontrolle
        for (int j = 0; j < falscheBuchstaben.length; j++) {
            System.out.print(falscheBuchstaben[j] + "  ");
        }

        System.out.println();

        do {
            // Eingabeaufforderung
            System.out.print("Bitte geben Sie einen Buchstaben ein: \n");

            buchstabe = eingabe.next().toUpperCase().charAt(0);

            if (!Character.isLetter(buchstabe)) {

                System.out.print("Sie haben keinen Buchstaben eingegeben. \n");

                continue;

            }

            boolean buchstabeGefunden = false;

            //Überprüfen, ob der Buchstabe im Wort enthalten ist 
            for (int j = 0; j < wort.length(); j++) {

                // Wenn der Buchstabe im Wort enthalten ist, wird er im Array gerateneBuchstaben gespeichert
                if (buchstabe == Character.toUpperCase(wort.charAt(j))) {
                    buchstabeGefunden = true;
                    gerateneBuchstaben[j] = wort.charAt(j);
                }
            }

            //Sonst wird der Buchstabe im Array falscheBuchstaben gespeichert
            if (!buchstabeGefunden) {
                falscheBuchstaben[k] = buchstabe;
                k++;
                anzahlVersuche--;
                System.out.print(buchstabe + " gibt es nicht.");
            } else {
                System.out.print(buchstabe + " gibt es.");
            }

            System.out.println();

            // Ausgabe der richtigen Buchstaben
            for (int j = 0; j < gerateneBuchstaben.length; j++) {
                System.out.print(gerateneBuchstaben[j] + "  ");
            }

            System.out.println();

            // Ausgabe des Arrays mit Unterstrichen, der die richtig geratenen Buchstaben von den falsch geratenen Buchstaben trennt 
            for (int j = 0; j < unterstriche.length; j++) {
                System.out.print(unterstriche[j] + "  ");
            }

            System.out.println();

            // Ausgabe der falschen Buchstaben
            for (int l = 0; l < k; l++) {
                System.out.print(falscheBuchstaben[l] + "  ");
            }

            System.out.println();

            // Überprüfen, ob das Wort vollständig erraten wurde
            zaehler = 0;
            for (int j = 0; j < gerateneBuchstaben.length; j++) {
                if (Character.isLetter(gerateneBuchstaben[j])) {
                    zaehler++;
                }
            }
            if (zaehler == gerateneBuchstaben.length) {
                wortErraten = true;
            }

        } while (anzahlVersuche > 0 && !wortErraten);

        if (wortErraten) {
            System.out.print("Sie haben gewonnen!");
        } else {
            System.out.print("Sie haben verloren! \n");
            System.out.print("Das gesuchte Wort war " + wort + ".");

            System.out.println(" ");
        }

    }

}

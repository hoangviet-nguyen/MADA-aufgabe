import Algorithmen.Euklid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte wählen Sie eine Option aus:");
        System.out.println("1. Schlüsselgenerierung");                  //Aufgabe 1
        System.out.println("2. Verschlüsselung");                       //Aufgabe 2
        System.out.println("3. Entschlüsselung");                       //Aufgabe 3
        int input = scanner.nextInt();

        if (input == 1) {
            Euklid euklid = new Euklid();                               //erstellt Objekt der Klasse Euklid mit allen Rechenmethoden
            Random rnd = new Random();
            BigInteger p = new BigInteger(2048, 8, rnd); // generiert 2x 2048 bit lange Primzahlen
            BigInteger q = new BigInteger(2048, 8, rnd); // mit certainty 8 ist die wahrscheinlichkeit 99.61% eine Primzahl
            BigInteger n = p.multiply(q);                               // berechnet n = p*q


            BigInteger phi = euklid.function(p, q);                     // berechnet phi(n) = (p-1)*(q-1)
            BigInteger e = euklid.possibleNumbers(phi);                 // berechnet mögliche e, die kleiner als phi(n) sind und mit phi(n) teilerfremd sind
            BigInteger d = euklid.erweitertGgt(phi, e);                  // berechnet d mit dem erweiterten euklidischen Algorithmus

            System.out.println("n: " + n);
            System.out.println("e: " + e);
            System.out.println("d: " + d);

            try {                                                       //probiert PrivateKey & PublicKey zu speichern
                File pathPrivate = new File("sk.txt");
                File pathPublic = new File("pk.txt");
                FileWriter wr = new FileWriter(pathPrivate);
                wr.write("(" + n + "," + d + ")");                   //schreibt PrivateKey in Datei im Format (n,d) in sk.txt
                wr.flush();
                wr.close();
                wr = new FileWriter(pathPublic);
                wr.write("(" + n + "," + e + ")");                   //schreibt PublicKey in Datei im Format (n,e) in pk.txt
                wr.flush();
                wr.close();
                System.out.println("Dateien wurden erfolgreich gespeichert");

            } catch (IOException ee) {
                System.out.println("fehler beim speichern der Datei");
                throw new RuntimeException(ee);

            }
        } else if (input == 2) {
            System.out.println("TODO");

            File textFile = new File("text.txt");
            Scanner reader;
            String text = "";
            try {
                reader = new Scanner(textFile);
            } catch (FileNotFoundException e) {
                System.out.println("fehler beim Lesen der Datei");
                throw new RuntimeException(e);
            }

            while (reader.hasNextLine()) {
                text = reader.nextLine();
            }
            System.out.println(text);

            //***TODO*** String text verschlüsseln


            try {                                                       //platzhalter verschlüsselter text speichern
                FileWriter wr = new FileWriter(("chiffre.txt"));
                wr.write("test chiffre");
                wr.flush();
                wr.close();
                System.out.println("Dateien wurde erfolgreich Verschlüsselt");

            } catch (IOException ee) {
                System.out.println("fehler beim speichern der Datei");
                throw new RuntimeException(ee);

            }

        } else if (input == 3) {
            System.out.println("TODO");
        } else {
            System.out.println("Falsche Eingabe");
        }

    }

}

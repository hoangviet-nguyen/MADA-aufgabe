import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import java.util.Random;
import java.util.Scanner;
import Algorithmen.Algorithms;

public class Main {

    public static Algorithms algorithm;
    public static BigInteger p;
    public static BigInteger q;
    public static BigInteger n;
    public static BigInteger phi;
    public static BigInteger e;
    public static BigInteger d;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte wählen Sie eine Option aus:");
        System.out.println("1. Schlüsselgenerierung");                  //Aufgabe 1
        System.out.println("2. Verschlüsselung");                       //Aufgabe 2
        System.out.println("3. Entschlüsselung");                       //Aufgabe 3
        int input = scanner.nextInt();

            if (input == 1) {
                algorithm = new Algorithms();                               //erstellt Objekt der Klasse Euklid mit allen Rechenmethoden
                Random rnd = new Random();
                p = new BigInteger(1500, 8, rnd); // generiert 2x 2048 bit lange Primzahlen
                q = new BigInteger(1500, 8, rnd); // mit certainty 8 ist die wahrscheinlichkeit 99.61% eine Primzahl
                n = p.multiply(q);                               // berechnet n = p*q


                phi = algorithm.function(p, q);                     // berechnet phi(n) = (p-1)*(q-1)
                e = algorithm.possibleNumbers(phi);                 // berechnet mögliche e, die kleiner als phi(n) sind und mit phi(n) teilerfremd sind
                d = algorithm.erweitertGgt(phi, e);                  // berechnet d mit dem erweiterten euklidischen Algorithmus

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
                    input = scanner.nextInt();
                } catch (IOException ee) {
                    System.out.println("fehler beim speichern der Datei");
                    throw new RuntimeException(ee);

                }
            }
            if (input == 2) {
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
                String verschluesselt = "";
                for (int i = 0; i < text.length(); i++) {
                    System.out.println(text.charAt(i));
                    verschluesselt = verschluesselt + algorithm.fastModExpo(e,n) + "\n";
                }
                System.out.println(verschluesselt);
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

            }
        }
    }
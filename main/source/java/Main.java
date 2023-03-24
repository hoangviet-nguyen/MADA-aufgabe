import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Algorithmen.Algorithms;

public class Main {

    public static Algorithms algorithm = new Algorithms();
    public static BigInteger p;
    public static BigInteger q;
    public static BigInteger n;
    public static BigInteger phi;
    public static BigInteger e;
    public static BigInteger d;
    public static Scanner scanner;
    public static int input;
    public static ArrayList<BigInteger> verschluesselt = new ArrayList<>();

    public static void main(String[] args) {
        int input;


        do {
            input = menu();

            if (input == 1) {
                //erstellt Objekt der Klasse Euklid mit allen Rechenmethoden
                Random rnd = new Random();
                p = new BigInteger(1024, 8, rnd);       // generiert 2x 2048 bit lange Primzahlen
                q = new BigInteger(1024, 8,
                    rnd);       // mit certainty 8 ist die wahrscheinlichkeit 99.61% eine Primzahl
                n = p.multiply(q);                                      // berechnet n = p*q


                phi = algorithm.function(p, q);                         // berechnet phi(n) = (p-1)*(q-1)
                e = algorithm.possibleNumbers(
                    phi);                     // berechnet mögliche e, die kleiner als phi(n) sind und mit phi(n) teilerfremd sind
                d = algorithm.erweitertGgt(phi,
                    e);                     // berechnet d mit dem erweiterten euklidischen Algorithmus

                System.out.println("n: " + n);
                System.out.println("e: " + e);
                System.out.println("d: " + d);

                try {                                                       //probiert PrivateKey & PublicKey zu speichern
                    File pathPrivate = new File("sk.txt");
                    File pathPublic = new File("pk.txt");
                    FileWriter wr = new FileWriter(pathPrivate);
                    wr.write("(" + n + "," + d +
                        ")");                   //schreibt PrivateKey in Datei im Format (n,d) in sk.txt
                    wr.flush();
                    wr.close();
                    wr = new FileWriter(pathPublic);
                    wr.write("(" + n + "," + e +
                        ")");                   //schreibt PublicKey in Datei im Format (n,e) in pk.txt
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

                Scanner reader;
                String bigString = "";
                try {
                    File textFile = new File("pk.txt");
                    reader = new Scanner(textFile);
                    scanner.useDelimiter(",");
                    int counter = 0;
                    while (reader.hasNextLine()) {

                        bigString = reader.nextLine();

                    }
                } catch (FileNotFoundException e) {
                    System.out.println("fehler beim Lesen der Datei");
                    throw new RuntimeException(e);
                }

                String[] keys = bigString.substring(1, bigString.length() - 1).split(",");
                BigInteger n = new BigInteger(keys[0]);
                BigInteger e = new BigInteger(keys[1]);
                String en = "";

                File textFile = new File("text.txt");

                String text = "";
                try {
                    reader = new Scanner(textFile);
                    while (reader.hasNextLine()) {
                        text = reader.nextLine();
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("fehler beim Lesen der Datei");
                    throw new RuntimeException(ex);
                }


                for (int i = 0; i < text.length(); i++) {
                    //System.out.println(text.charAt(i));                                // Liest die String werte aus dem Text
                    int temp = text.charAt(i);                                         // Wandelt die Werte in int's um
                    BigInteger someCharacter =
                        new BigInteger(String.valueOf(temp));   // Wandelt den int in ein BigInteger
                    verschluesselt.add(algorithm.fastModExpo(someCharacter, e,
                        n));    // verschlüsselt Text, speichert in Liste für entschlüsselung
                }
                //System.out.println(verschluesselt);
                //System.out.println(text);

                try {
                    FileWriter wr = new FileWriter(("chiffre.txt"));
                    for (BigInteger i : verschluesselt
                    ) {
                        wr.write(String.valueOf(i) + "\n");

                    }
                    wr.flush();
                    wr.close();
                    System.out.println("Dateien wurde erfolgreich Verschlüsselt");

                } catch (IOException ee) {
                    System.out.println("fehler beim speichern der Datei");
                    throw new RuntimeException(ee);

                }
            }

            if (input == 3) {
                Scanner reader;
                String bigString = "";
                try {
                    File textFile = new File("sk.txt");
                    reader = new Scanner(textFile);
                    scanner.useDelimiter(",");
                    int counter = 0;
                    while (reader.hasNextLine()) {

                        bigString = reader.nextLine();

                    }
                } catch (FileNotFoundException e) {
                    System.out.println("fehler beim Lesen der Datei");
                    throw new RuntimeException(e);
                }

                String[] keys = bigString.substring(1, bigString.length() - 1).split(",");
                BigInteger n = new BigInteger(keys[0]);
                BigInteger d = new BigInteger(keys[1]);
                ArrayList<String> encrypted = new ArrayList<>();

                try {
                    reader = new Scanner(new File("chiffre.txt"));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                while (reader.hasNextLine()) {
                    encrypted.add(reader.nextLine());
                }
                //System.out.printf(encrypted.get(1));
                //System.out.println("");
                ArrayList<Character> ausgabe = new ArrayList<>();
                for (int i = 0; i < encrypted.size(); i++) {
                    BigInteger integer = algorithm.fastModExpo(new BigInteger(encrypted.get(i)), d, n);
                    System.out.println((char) integer.intValue());
                    ausgabe.add((char) integer.intValue());


                }


                try {
                    FileWriter wr = new FileWriter(("text-d.txt"));
                    for (int i = 0; i < ausgabe.size(); i++) {
                        wr.write(ausgabe.get(i));
                    }
                    wr.flush();
                    wr.close();
                    System.out.println("Dateien wurde erfolgreich entschlüsselt");

                } catch (IOException ee) {
                    System.out.println("fehler beim speichern der Datei");
                    throw new RuntimeException(ee);

                }
            }
        }
        while (input!=4);


    }

    private static int menu() {
        scanner = new Scanner(System.in);
        System.out.println("Bitte wählen Sie eine Option aus:");
        System.out.print(" 1. Schlüsselgenerierung |");              //Aufgabe 1
        System.out.print(" 2. Verschlüsselung |");                   //Aufgabe 2
        System.out.print(" 3. Entschlüsselung |");                   //Aufgabe 3
        System.out.println(" 4. schliessen");

        return scanner.nextInt();
    }
}
package Algorithmen;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        /*
        Algorithms algorithm = new Algorithms();
        Random rnd = new Random();
        BigInteger p = new BigInteger(2048, 8, rnd); // generiert 2x 2048 bit lange Primzahlen
        BigInteger q = new BigInteger(2048, 8, rnd); // mit certainty 8 ist die wahrscheinlichkeit 99.61% eine Primzahl
        BigInteger n = p.multiply(q);                               // berechnet n = p*q


        BigInteger phi = algorithm.function(p, q);                     // berechnet phi(n) = (p-1)*(q-1)
        BigInteger e = algorithm.possibleNumbers(phi);                 // berechnet mögliche e, die kleiner als phi(n) sind und mit phi(n) teilerfremd sind
        BigInteger d = algorithm.erweitertGgt(phi, e);
        BigInteger x = new BigInteger("6");

       // System.out.println(algorithm.fastModExpo(,e,n));

         */

        //liest public key von pk.txt
        Scanner scanner = new Scanner(System.in);
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

        String[] keys = bigString.substring(1,bigString.length()-1).split(",");
        BigInteger n = new BigInteger(keys[0]);
        BigInteger e = new BigInteger(keys[1]);
        System.out.println(bigString);
        System.out.println(keys[0]);
        System.out.println(keys[1]);

    }
}

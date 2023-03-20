import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import Algorithmen.Euklid;

public class Main {
    public static void main(String[] args) {
        Euklid euklid = new Euklid();
        Random rnd = new Random();
        BigInteger p = new BigInteger(2048, 5, rnd);
        BigInteger q = new BigInteger(2048, 5, rnd);
        BigInteger n = p.multiply(q);


        BigInteger phi = euklid.function(p, q);
        BigInteger e = euklid.possibleNumbers(phi);
        BigInteger d = euklid.erweitertGgt(phi,e);
        System.out.println(e);
        System.out.println(d);
        File pathPrivate = new File("sk.txt");
        File pathPublic = new File("pk.txt");
        try { //probiert PrivateKey zu speichern
            FileWriter wr = new FileWriter(pathPrivate);
            wr.write(n.toString());
            wr.write(d.toString());


            System.out.println("test1");
            wr.flush();
            wr.close();

        }  catch (IOException ee) {
            System.out.println("fehler");
            throw new RuntimeException(ee);

        }
        try {//probiert PublicKey zu speichern
            FileWriter wr2 = new FileWriter(pathPublic);
            wr2.write(n.toString());
            wr2.write(e.toString());
            wr2.flush();
            wr2.close();
        }catch (IOException ee) {
            System.out.println("fehler");
            throw new RuntimeException(ee);

        }



    }
}
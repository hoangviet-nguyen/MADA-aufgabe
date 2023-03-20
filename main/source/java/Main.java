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
    }
}
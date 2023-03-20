import java.math.BigInteger;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Random rnd = new Random();
        BigInteger p = new BigInteger(2048, 5, rnd);
        BigInteger q = new BigInteger(2048, 5, rnd);
        BigInteger n = p.multiply(q);


        BigInteger two = new BigInteger("2");
        BigInteger one = new BigInteger("1");
        if (p.equals(q)){
            System.out.printf("p and q are equal");
            return ;
        }
        System.out.println(two.subtract(one).multiply(two.subtract(one)));

    }
}
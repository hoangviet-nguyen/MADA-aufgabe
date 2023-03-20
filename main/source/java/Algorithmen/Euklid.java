package Algorithmen;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Euklid {

    public BigInteger possibleNumbers(BigInteger range) { // rechnet den Zahlenraum Z* aus
        Random random = new Random();
        BigInteger some = new BigInteger(32, random);
        while (easyGgt(some, range).equals(new BigInteger("1"))) {
            some = new BigInteger(2040, random);
        }
        return some;
    }

    public BigInteger function(BigInteger prime1, BigInteger prime2){
         prime1 = prime1.subtract(new BigInteger("1"));
         prime2 = prime2.subtract(new BigInteger("1"));
         return prime1.multiply(prime2);
    }

    public BigInteger easyGgt(BigInteger a, BigInteger b) { // easy euklidian algorithm
        if (a.compareTo(b)<0){
            BigInteger temp = a;
            a = b;
            b = temp;
        }
        BigInteger r = a.mod(b);
        while (!r.equals(new BigInteger("0"))) {
            a = b;
            b = r;
            r =  a.mod(b);
        }
        return b;
    }

    public BigInteger erweitertGgt(BigInteger m, BigInteger e) { // continued euklidian algorithm
        BigInteger a = m; BigInteger b = e;
        BigInteger x0 = new BigInteger("1"); BigInteger y0 = new BigInteger("0");
        BigInteger x1 = new BigInteger("0"); BigInteger y1 = new BigInteger("1");
        BigInteger q = a.divide(b); BigInteger r = a.mod(b); BigInteger tempx; BigInteger tempy;
        while (b.compareTo(new BigInteger("0")) > 0) {
            a = b;
            b = r;
            tempx = x0;
            tempy = y0;
            x0 = x1;
            y0 = y1;
            x1 = tempx.subtract(q.multiply(x1));
            y1 = tempy.subtract(q.multiply(y1));
            if (b.compareTo(new BigInteger("0")) == 0){
                if (y0.compareTo(new BigInteger("0")) < 0){
                    if (x1.multiply(m).add(y1.multiply(e)).compareTo(new BigInteger("0")) == 0){
                        return y1.add(y0);
                    } else {
                        return y1;
                    }
                }
                return y0;
            }
            q = a.divide(b);
            r = a.mod(b);
        }
        return y0;
    }


}

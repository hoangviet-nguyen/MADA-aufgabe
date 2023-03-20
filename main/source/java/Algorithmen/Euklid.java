package Algorithmen;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Euklid {
// Hallo Welt

    public List<BigInteger> possibleNumbers(BigInteger prime1, BigInteger prime2) { // rechnet den Zahlenraum Z* aus

        BigInteger range = prime1.subtract(new BigInteger("1").multiply(prime2.subtract( new BigInteger("1"))));
        List<BigInteger> possible = new ArrayList<>();
        BigInteger i = new BigInteger("1");
        while (range.compareTo(i)> 0) {
            if (easyGgt(i, range).equals(new BigInteger("1"))){
                possible.add(i);
            }
            i = i.add(new BigInteger("1"));
        }
        return possible;
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

    public  static int erweitertGgt(int m, int e) { // continued euklidian algorithm
        int a = m; int b = e;
        int x0 = 1; int y0 = 0; int x1 = 0; int y1 = 1;
        int q = a / b; int r = a % b; int tempx; int tempy;
        int[] bezout = new int[2];
        while (b > 0) {
            a = b;
            b = r;
            tempx = x0;
            tempy = y0;
            x0 = x1;
            y0 = y1;
            x1 = tempx - q * x1;
            y1 = tempy - q * y1;
            if (b == 0){
                if (y0 < 0){
                    if (x1 * m + y1 * e == 0) {
                        return y1 + y0;
                    } else {
                        return y1;
                    }
                }
                return y0;
            }
            q = a/b;
            r = a % b;
        }
        return y0;
    }


}

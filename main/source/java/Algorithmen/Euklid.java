package Algorithmen;

import java.util.ArrayList;
import java.util.List;

public class Euklid {
// Hallo Welt

    public List<Integer> possibleNumbers(int prime1, int prime2) { // rechnet den Zahlenraum Z* aus
        int range = (prime1 -1) * (prime2 -1);
        List<Integer> possible = new ArrayList<>();
        int i = 0;
        while (i < range) {
            if (easyGgt(i, range) == 1){
                possible.add(i);
            }
            i++;
        }
        return possible;
    }

    public int easyGgt(int a, int b) { // easy euklidian algorithm
        if (a < b){
            int temp = a;
            a = b;
            b = temp;
        }
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
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

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

    public int easyGgt(int a, int b){
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

}

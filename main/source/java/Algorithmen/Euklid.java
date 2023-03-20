package Algorithmen;

import java.util.ArrayList;
import java.util.List;

public class Euklid {


    public List<Integer> possibleNumbers(int range) {
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
    public int zahlenRaum(int primzahl1, int primzahl2){
        return (primzahl1 - 1) * (primzahl2 -1);
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

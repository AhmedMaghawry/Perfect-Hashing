package UniversalHashing;

import java.util.Random;

public class HashFunction {
    
    int u;
    int b;
    Random x;
    
    public HashFunction(int maxNum, int b) {
        this.b = b;
        u = maxNum;
        x = new Random();
    }

    public int[][] getHashFunction() {
        int[][] tab = new int[b][u];
        for(int i = 0;i < b; i++) {
            for(int j = 0; j < u; j++) {
                int d = x.nextInt();
                if(d < 0)
                    d *= -1;
                tab[i][j] = d % 2;
            }
        }
        return tab;
    }
}

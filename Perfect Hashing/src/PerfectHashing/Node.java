package PerfectHashing;

import java.util.ArrayList;

public class Node {

    ArrayList<Integer> coll;
    int size = 0;
    int maxKeyLen;
    OrderSquare hashing;
    int[] result;
    
    public Node(int maxKeyLen) {
        coll = new ArrayList<>();
        this.maxKeyLen = maxKeyLen;
    }
    
    public void addEl(int x) {
        coll.add(x);
        size++;
    }
    
    public int[] hash() {
        int[] tt = new int[size];
        for(int i = 0; i < size; i++) {
            tt[i] = coll.get(i);
        }
        hashing = new OrderSquare(tt, maxKeyLen);
        result = hashing.hash();
        return result;
    }
    
    public int[] single() {
        result = new int[1];
        result[0] = coll.get(0);
        return result;
    }
    
    public boolean search(int key) {
        return hashing.search(key);
    }
}

package PerfectHashing;

import java.io.File;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import FileHandler.reader;
import UniversalHashing.HashFunction;

public class OrderSquare {
    
    int[] inputs;
    int maxKeyLEn;
    HashFunction universal;
    int[][] firstHash;
    int[] result;
    
    public OrderSquare() {
        JFrame frame = new JFrame("SIC");
        frame.setVisible(true);
        JTextField btn = new JTextField();
        frame.getContentPane().add(btn);
        frame.setSize(100, 100);
        JFileChooser fileCho = new JFileChooser();
        fileCho.showOpenDialog(frame);
        File getFile = fileCho.getSelectedFile();
        reader reader = new reader(getFile);
        inputs = reader.readKays();
        maxKeyLEn = (Integer.toBinaryString(reader.getMax())).length();
        int b = 2 * (int)(Math.log(inputs.length) / Math.log(2));
        result = new int[(int) Math.pow(2, b)];
        universal = new HashFunction(maxKeyLEn, b);
        firstHash = universal.getHashFunction();
    }
    
    public OrderSquare(int[] inputs, int maxKeyLEn) {
        this.inputs = inputs;
        this.maxKeyLEn = maxKeyLEn;
        int b = 2 * (int)(Math.log(inputs.length) / Math.log(2));
        result = new int[(int) Math.pow(2, b)];
        universal = new HashFunction(maxKeyLEn, b);
        firstHash = universal.getHashFunction();
    }
    
    public int[] hash() {
        Arrays.fill(result, -1);
        for(int i = 0; i < inputs.length; i++) {
            System.out.println(inputs[i]);
            int index = multi(toBinary(inputs[i]));
            if(result[index] == -1) {
                result[index] = inputs[i];
            } else {
                if(result[index] == inputs[i]) {
                    // The same number
                } else {
                    // There is Collision
                    return reHash();
                }
            }
        }
        return result;
    }

    private int[] reHash() {
        firstHash = universal.getHashFunction();
        return hash();
    }

    private int multi(String binary) {
        String binaryRes = "";
        for(int i = 0; i < firstHash.length; i++) {
            int res = 0;
            for(int j = 0; j < firstHash[i].length; j++) {
                res += firstHash[i][j] * Integer.parseInt(binary.charAt(j)+"");
            }
            binaryRes += (res % 2) + "";
        }
        int finalRes = Integer.parseInt(binaryRes, 2);
        return finalRes;
    }

    private String toBinary(int num) {
        String temp = Integer.toBinaryString(num);
        String res = "";
        for(int i = 0; i < maxKeyLEn - temp.length(); i++) {
            res += "0";
        }
        res += temp;
        return res;
    }
    
    public boolean search(int key) {
        int index = multi(toBinary(key));
        if(result[index] == key)
            return true;
        else
            return false;
    }
    
    public static void main(String[] args) {
        OrderSquare f = new OrderSquare();
        f.hash();
        System.out.println(f.search(5));
    }
}

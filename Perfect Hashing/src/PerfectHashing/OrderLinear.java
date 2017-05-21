package PerfectHashing;

import java.io.File;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import FileHandler.reader;
import UniversalHashing.HashFunction;

public class OrderLinear {
    
    int[] inputs;
    int maxKeyLEn;
    HashFunction universal;
    int[][] firstHash;
    Node[] finalIsNear;

    public OrderLinear() {
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
        int b = (int)(Math.log(inputs.length) / Math.log(2));
        finalIsNear = new Node[(int) Math.pow(2, b)];
        universal = new HashFunction(maxKeyLEn, b);
        firstHash = universal.getHashFunction();
    }
    
    public Node[] hash() {
        Arrays.fill(finalIsNear, null);
        for(int i = 0; i < inputs.length; i++) {
            int index = multi(toBinary(inputs[i]));
            if(finalIsNear[index] != null) {
                finalIsNear[index].addEl(inputs[i]);
            } else {
                Node x = new Node(maxKeyLEn);
                x.addEl(inputs[i]);
                finalIsNear[index] = x;
            }
        }
        for(Node x : finalIsNear) {
            if (x != null && x.size > 1)
                x.hash();
            else if (x != null && x.size == 1)
                x.single();
        }
        return finalIsNear;
    }
    
    public boolean search(int key) {
        int index = multi(toBinary(key));
        if(finalIsNear[index].search(key))
            return true;
        else
            return false;
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
    
    public static void main(String[] args) {
        OrderLinear f = new OrderLinear();
        f.hash();
        System.out.println(f.search(5));
    }
}

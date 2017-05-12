package FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class reader {
    
    private File file;
    private int[] nums;
    
    public reader(File file) {
        this.file = file;
    }
    
    public int[] readKays() {
        FileReader fileReader;
        int[] res = null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            String[] firstLine = line.split(" ");
            res = new int[firstLine.length];
            for(int i = 0; i < firstLine.length; i++) {
                res[i] = Integer.parseInt(firstLine[i]);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        nums = res;
        return res;
    }

    public int getMax() {
        int max = Integer.MIN_VALUE;
        for(int x : nums) {
            if(max < x)
                max = x;
        }
        return max;
    }
}

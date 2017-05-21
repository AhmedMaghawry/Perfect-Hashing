package FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class reader {
    
    private File file;
    int max = Integer.MIN_VALUE;
    
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
            String[] firstLine = line.split(",");
            res = new int[firstLine.length];
            for(int i = 0; i < firstLine.length; i++) {
                int temp = Integer.parseInt(firstLine[i]);
                res[i] = temp;
                if(temp > max)
                    max = temp;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    public int getMax() {
        return max;
    }
}

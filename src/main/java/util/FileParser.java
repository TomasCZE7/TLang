package util;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.Scanner;

public class FileParser {

    private String path;

    private File file;
    private BufferedReader reader;
    private FileWriter writer;

    public FileParser(String path) {
        this.path = path;
        file = new File(path);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void write(Object... lines) {
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Object line : lines){
            try {
                if(line.toString().isEmpty())
                    continue;
                writer.write(line.toString()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        try {
            writer = new FileWriter(file);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readIntoString() {
        String output = "";
        String line = "";
        while(true){
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            output += line + "\n";
        }
        return output;
    }

    public String getPath() {
        return path;
    }
}

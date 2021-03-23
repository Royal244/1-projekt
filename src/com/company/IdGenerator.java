package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IdGenerator {

    public int generateIdFromFile(File file) {
        String lastLine = "";
        try {
            String sCurrentLine;

            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((sCurrentLine = br.readLine()) != null) {
                lastLine = sCurrentLine;
            }
        } catch (IOException e) {
            System.out.println("Blad przy generowaniu id");
        }
        if (lastLine.equals(""))
            return 0;
        return Integer.parseInt(lastLine.split(";")[0]) + 1;
    }
}
package com.javaacademy;

import java.io.*;

public class Main {
   // private final static String[][] campo = new String[10][10];
    //private static String[][] campoGiocatore = new String[10][10];

    public static void main(String[] args) {

        Campo campoGiocatore = new Campo();
        Campo campo = leggiCampoDaFile("campo.txt");
        Gioco gioco = new Gioco(campo, campoGiocatore);
        gioco.run();
    }


    public static Campo leggiCampoDaFile(String fileName) {

        String[][] c = new String[10][10];
        try (BufferedReader buffer = new BufferedReader(new FileReader("campo.txt"))) {
            String line;
            int i = 0;
            while ((line = buffer.readLine()) !=  null) {
                if (line.length() == 10) {
                    String[] lineChars = line.split("");
                    for (int y = 0; y < 10; y++) {
                       c[i][y] = lineChars[y];
                    }
                    i++;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Campo campo = new Campo(c);
        return campo;
    }
}

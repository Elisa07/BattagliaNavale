package com.javaacademy;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        Campo campoGiocatore = new Campo();
        Campo campo = leggiCampoDaFile("campo.txt");

        if (campo != null) {
            Gioco gioco = new Gioco(campo, campoGiocatore);
            gioco.run();
        } else {
            System.out.println("Il file non è stato configurato correttamente.");
        }

    }


    public static Campo leggiCampoDaFile(String fileName) {

        int count = 0;
        String[][] c = new String[10][10];
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i = 0;
            while ((line = buffer.readLine()) !=  null) {
                count++;
                if (line.length() == 10) {
                    String[] lineChars = line.split("");
                    for (int y = 0; y < 10; y++) {
                       c[i][y] = lineChars[y];
                    }
                    i++;
                } else {
                    return null;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (count == 10) {
            return  new Campo(c);
        }
        return null;
    }
}

package com.javaacademy;

import java.util.Objects;

import static com.javaacademy.Colors.*;

public class Campo {

    public String[][] campo;

    public Campo() {
        campo = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int y = 0; y < 10; y++) {
                this.campo[i][y] = (Character.toString((char) 0x25A1));
            }
        }
    }

    public Campo(String[][] campo) {
        this.campo = campo;
    }

    public String getValoreCampo(int x, int y) {
        return campo[x - 1][y - 1];
    }

    public void setValoreCampo(int x, int y, String valore) {
        if (valore != null) {
            this.campo[x-1][y-1] = valore;
        }
    }

    public void stampaCampo() {

        //System.out.println(ANSI_BLACK + "================================================" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "   A B C D E F G H I J" + ANSI_RESET);
        for (int i = 1; i <= 10; i++) {
            for (int y = 1; y <= 10; y++) {
                if (y == 1) {
                    System.out.format(ANSI_GREEN + "%02d " + ANSI_RESET, i);
                }
                System.out.print((campo[i - 1][y -1].equals(Character.toString((char) 0x2248)) || (campo[i - 1][y -1].equals("-")) ? ANSI_CYAN : "") + (campo[i - 1][y -1].equals("X") ? ANSI_RED : "") + campo[i - 1][y -1] + " " + ANSI_RESET);
            }

            System.out.println();
        }

        System.out.println();
    }


}

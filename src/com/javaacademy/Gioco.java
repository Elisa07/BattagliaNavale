package com.javaacademy;

import java.util.Scanner;

import static com.javaacademy.Colors.*;


public class Gioco {

    private Campo campo;
    private Campo campoGiocatore;
    private final int numeroNavi = 5;
    private int naviColpite = 0;
    private int mosse = 0;

    public Gioco(Campo campo, Campo campoGiocatore) {
        this.campo = campo;
        this.campoGiocatore = campoGiocatore;
    }

    public Campo.Coordinata leggiCoordinata() {
        System.out.print("Insersci una coordinata (es. A6, D11, F2): ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim().toUpperCase().replace(" ", "");

            if (input.charAt(0) > 64 && input.charAt(0) < 75) {
                try {
                    int x = Integer.parseInt(input.substring(1));
                    if (x >= 1 && x <= 10){
                        int y = input.charAt(0) - 64;

                        Campo.Coordinata c = new Campo.Coordinata(x, y);
                        return c;
                    } else {
                        System.out.println(x + " non è un numero valido. Inserire un numero tra 1 e 10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(input.charAt(1) + " non è un numero. Inserire un numero tra 1 e 10." );
                }

            } else {
                System.out.println("La lettera " + input.charAt(0) + " non è valida. Inserire una lettera tra A e J.");
            }


        return null;
    }


    public void checkCell(Campo.Coordinata c) {
        if (c != null) {
           if(campo.getValoreCampo(c.getX(),c.getY()).equals("-") && campoGiocatore.getValoreCampo(c.getX(),c.getY()).equals((Character.toString((char) 0x25A1)))){
               System.out.println(ANSI_CYAN + "ACQUA!!!" + ANSI_RESET);
               campoGiocatore.setValoreCampo(c.getX(),c.getY(), Character.toString((char) 0x2248));
           } else if (campo.getValoreCampo(c.getX(),c.getY()).equals("+") && campoGiocatore.getValoreCampo(c.getX(),c.getY()).equals((Character.toString((char) 0x25A1)))){
               campoGiocatore.setValoreCampo(c.getX(),c.getY(), "X");
               Campo.Coordinata[] nave = lunghezzaNave(c,true);
               boolean affondata = true;
               if (nave == null) { // La nave non è orizzontale
                   nave = lunghezzaNave(c,false); // Controllo se la nave è verticale
                   //Se è verticale --> controllo le x

                   for (int i = nave[0].getX(); i <= nave[1].getX(); i++) {
                       if (!campoGiocatore.getValoreCampo(i, c.getY()).equals("X")) {
                           affondata = false;
                           break;
                       }
                   }

               } else {
                   // Se è orizzontale --> controllo le y
                   for (int i = nave[0].getY(); i <= nave[1].getY(); i++) {
                       if (!campoGiocatore.getValoreCampo(c.getX(), i).equals("X")) {
                           affondata = false;
                           break;
                       }
                   }

               }
               if  (affondata) {
                   this.naviColpite++;
                   System.out.println(ANSI_RED + "COLPITA E AFFONDATA!!" + ANSI_RESET);
                   System.out.println("HAI AFFONDATO " + naviColpite + (naviColpite == 1 ? " NAVE SU " : " NAVI SU ") + numeroNavi);
               } else {
                   System.out.println(ANSI_RED + "COLPITA!!" + ANSI_RESET);
               }
           } else {
               System.out.println("La coordinata è già stata controllata.");
           }
        }
        this.mosse++;
    }

    private Campo.Coordinata[] lunghezzaNave(Campo.Coordinata cIniziale, boolean orizzontale) {
        int x = cIniziale.getX();
        int y = cIniziale.getY();

        Campo.Coordinata inizioNave = cIniziale;
        Campo.Coordinata fineNave = cIniziale;


        if (orizzontale) {
            while (y > 0) {
                if (!campo.getValoreCampo(x, y).equals("+")) {
                    inizioNave = new Campo.Coordinata(x, y + 1);

                    break;
                } else {
                    y--;
                }
            }
            y = cIniziale.getY();
            while (y < 10) {
                if (!campo.getValoreCampo(x, y).equals("+")) {
                    fineNave = new Campo.Coordinata(x, y - 1);

                    break;
                } else {
                    y++;
                }
            }

            System.out.println("");
        } else {
            while (x > 0) {
                if (!campo.getValoreCampo(x, y).equals("+")) {
                    inizioNave = new Campo.Coordinata(x + 1, y);

                    break;
                } else {
                    x--;
                }
            }
            x = cIniziale.getX();

            while (x < 10) {

                if (!campo.getValoreCampo(x, y).equals("+")) {
                    fineNave = new Campo.Coordinata(x - 1, y);
                    break;
                }  else{
                    x++;
                }
            }
        }


        if (cIniziale.equals(inizioNave) && cIniziale.equals(fineNave)) {
            return null;
        }
        return new Campo.Coordinata[]{inizioNave, fineNave};
    }

    public void run() {

        System.out.println(ANSI_BLUE + "Benvenut" + Character.toString((char) 0x0259) + " a battaglia navale!" + ANSI_RESET);

        System.out.println("\nREGOLAMENTO:");
        System.out.println("Devi colpire e affondare " + numeroNavi + " navi.\nLe navi sono di queste dimensioni:");
        System.out.println("\t- 2 navi da 2 caselle,");
        System.out.println("\t- 2 navi da 3 caselle,");
        System.out.println("\t- 1 navi da 4 caselle.");
        System.out.println("Le navi possono essere disposte in orizzontale o in verticale (non in diagonale).\nLe navi non possono essere disposte l'una accanto all'altra.\n");

        System.out.println("Buon divertimento!\n");

        this.campoGiocatore.stampaCampo();

        while (naviColpite < numeroNavi) {
            Campo.Coordinata c = leggiCoordinata();
            if (c != null) {
                checkCell(c);
                this.campoGiocatore.stampaCampo();
            }
        }
        System.out.println(ANSI_WHITE_BOLD + "COMPLIMENTI! HAI TERMINATO IL GIOCO!" + ANSI_RESET);
        System.out.println("MOSSE UTILIZZATE: " + mosse + ".");
    }
}

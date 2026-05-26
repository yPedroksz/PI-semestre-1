package util;

import java.util.Scanner;
import personagens.Jogador;
import personagens.Classes;

public class Funcoes {
    public static Scanner entrada = new Scanner(System.in);

    public static void msg(String t) { System.out.println(t); }

    public static int ler(String t, int max) {
        int op = 0;
        while (op < 1 || op > max) { 
            System.out.println(t); 
            if (entrada.hasNextInt()) op = entrada.nextInt(); 
            else entrada.next(); // Limpa input incorreto
        }
        return op;
    }

    public static void checkMorte() { 
        if (Jogador.status[Classes.HP] <= 0) { 
            msg("\n💀 GAME OVER. Sua essência sumiu no multiverso."); 
            System.exit(0); 
        } 
    }

    public static void mercador() {
        while (true) {
            msg("\n💰 MERCADOR | Ouro: " + Jogador.ouro + "g\n1-Vida P (30g) | 2-Mana P (40g) | 3-Cristal Vigor (100g) | 4-Sair");
            int e = ler("Escolha:", 4);
            if (e == 1 && Jogador.ouro >= 30) { Jogador.ouro -= 30; Jogador.inv[0]++; msg("Vida P comprada."); }
            else if (e == 2 && Jogador.ouro >= 40) { Jogador.ouro -= 40; Jogador.inv[3]++; msg("Mana P comprada."); }
            else if (e == 3 && Jogador.ouro >= 100) { Jogador.ouro -= 100; Jogador.status[Classes.VIGOR] += 10; msg("Vigor aumentado!"); }
            else if (e == 4) break;
            else msg("Ouro insuficiente.");
        }
    }

    public static boolean abrirInv() {
        boolean vazio = true;
        for (int i = 0; i < 6; i++) {
            if (Jogador.inv[i] > 0) { 
                msg((i+1) + " - " + Jogador.ITENS[i] + " [" + Jogador.inv[i] + "]"); 
                vazio = false; 
            }
        }
        if (vazio) { msg("Mochila vazia!"); return true; }
        
        int e = ler("7 - Voltar | Escolha o item:", 7) - 1;
        if (e == 6 || Jogador.inv[e] == 0) return true; 
        
        Jogador.inv[e]--;
        if (e < 3) Jogador.status[Classes.HP] = Math.min(Jogador.status[Classes.MAX_HP], Jogador.status[Classes.HP] + (e==0 ? 50 : e==1 ? 100 : 200));
        else Jogador.status[Classes.MANA] = Math.min(Jogador.status[Classes.MAX_MANA], Jogador.status[Classes.MANA] + (e==3 ? 40 : e==4 ? 90 : 180));
        msg("🧪 Item consumido com sucesso!");
        return false; 
    }
}

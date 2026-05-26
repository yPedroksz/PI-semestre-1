package personagens;

import static util.Funcoes.msg;

public class Jogador {
    public static int[] status = new int[8];
    public static int[] inv = new int[6];
    public static int frag = 0, ouro = 0, xp = 0, nivel = 1;
    public static boolean corrompido = false;
    
    public static final String[] ITENS = {"Vida P", "Vida M", "Vida G", "Mana P", "Mana M", "Mana G"};

    // Lógica de Level Up
    public static void up() {
        if (xp >= nivel * 100) {
            xp -= nivel * 100; 
            nivel++;
            status[Classes.MAX_HP] += 25; 
            status[Classes.HP] = Math.min(status[Classes.MAX_HP], status[Classes.HP] + 25);
            status[Classes.MAX_MANA] += 15; 
            status[Classes.MANA] = Math.min(status[Classes.MAX_MANA], status[Classes.MANA] + 15);
            status[Classes.VIGOR] += 4; 
            status[Classes.VELOCIDADE] += 4; 
            status[Classes.INTEL] += 4; 
            status[Classes.FORCA] += 4;
            msg("\n✨ LEVEL UP! Você alcançou o Nível " + nivel);
        }
    }
}

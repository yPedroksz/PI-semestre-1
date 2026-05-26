package historia;

import static util.Funcoes.*;
import static personagens.Classes.*;
import personagens.Jogador;
import combate.Sistemacombate;
import inimigos.Feiticeira;

public class Floresta {
    public static void jogar() {
        mercador();
        msg("\n--- FLORESTA DO ESQUECIMENTO ---\nFeiticeira drena a área.");
        if (Jogador.status[VIGOR] >= 20) { 
            msg("Resistiu ao choque mental! -5 HP"); 
            Jogador.status[HP] -= 5; 
        } else { 
            msg("Falha de Vigor! O ataque rasga sua mente. -35 HP"); 
            Jogador.status[HP] -= 35; checkMorte(); 
        }
        
        if (ler("1-Atacar | 2-Recuar", 2) == 1) {
            Sistemacombate.lutou("Feiticeira", Feiticeira.HP, Feiticeira.ATK, Feiticeira.XP, Feiticeira.OURO, true);
        }
    }
}

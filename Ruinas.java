package historia;

import static util.Funcoes.*;
import static personagens.Classes.*;
import personagens.Jogador;
import combate.Sistemacombate;
import inimigos.Guardiao;

public class Ruinas {
    public static void jogar() {
        mercador();
        msg("\n--- RUÍNAS COLOSSAIS ---\nO Guardião Ancestral guarda a última pedra.");
        if (ler("1-Furtividade | 2-Atacar", 2) == 1 && (Jogador.status[INTEL] >= 22 || Jogador.status[FORCA] >= 22)) {
            msg("A manobra furtiva funcionou!"); 
            Jogador.frag++;
        } else {
            Sistemacombate.lutou("Guardião", Guardiao.HP, Guardiao.ATK, Guardiao.XP, Guardiao.OURO, true);
        }
    }
}

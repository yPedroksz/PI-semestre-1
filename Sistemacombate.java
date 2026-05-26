package combate;

import static util.Funcoes.*;
import static personagens.Classes.*;
import personagens.Jogador;

public class Sistemacombate {
    public static boolean lutou(String nome, int hp, int atk, int gXp, int gOuro, boolean dropFrag) {
        msg("\n⚔️ COMBATE: " + nome.toUpperCase());
        while (Jogador.status[HP] > 0 && hp > 0) {
            msg("\n" + nome + " HP: " + hp + " | Seu HP: " + Jogador.status[HP] + " | Mana: " + Jogador.status[MANA]);
            int dFis = (int)(Jogador.status[FORCA] * 1.5) + 5;
            int dMag = Jogador.status[INTEL] * 2;
            int acao = ler("1-Físico (" + dFis + " dmg) | 2-Magia (" + dMag + " dmg / 20M) | 3-Bolsa", 3);
            
            if (acao == 1) hp -= dFis;
            else if (acao == 2 && Jogador.status[MANA] >= 20) { Jogador.status[MANA] -= 20; hp -= dMag; }
            else if (acao == 3 && abrirInv()) continue; 
            else { msg("Ação falhou ou está sem recursos!"); continue; }

            if (hp <= 0) {
                msg("Vitória! +" + gXp + "XP | +" + gOuro + "g"); 
                Jogador.xp += gXp; Jogador.ouro += gOuro; Jogador.up(); 
                if (dropFrag) Jogador.frag++; 
                return true;
            }
            
            int dmg = Math.max(5, atk - (Jogador.status[VIGOR] / 2));
            msg("Você sofreu " + dmg + " de dano.");
            Jogador.status[HP] -= dmg;
            checkMorte();
        }
        return false;
    }
}

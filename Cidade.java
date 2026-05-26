package historia;

import static util.Funcoes.*;
import static personagens.Classes.*;
import personagens.Jogador;
import combate.Sistemacombate;
import inimigos.Ladrao;

public class Cidade {
    public static void jogar() {
        msg("--- CIDADE DE NEON ---\nLadrões armados têm seu PRIMEIRO FRAGMENTO.");
        if (ler("1-Atacar | 2-Furtividade", 2) == 1) {
            Sistemacombate.lutou("Ladrões", Ladrao.HP, Ladrao.ATK, Ladrao.XP, Ladrao.OURO, true);
        } else if (Jogador.status[VELOCIDADE] < 15) {
            Sistemacombate.lutou("Ladrões Alerta", Ladrao.HP_ALERTA, Ladrao.ATK_ALERTA, Ladrao.XP, Ladrao.OURO_ALERTA, true);
        }
        
        msg("\nUm cientista oferece uma máquina bio-restauradora suspeita.");
        if (ler("1-Entrar | 2-Recusar", 2) == 1) { 
            Jogador.status[HP] = Jogador.status[MAX_HP]; 
            Jogador.corrompido = true; 
            msg("Corrompido pelas sombras!"); 
        }
    }
}

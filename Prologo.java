package historia;

import static util.Funcoes.*;
import static personagens.Classes.*;
import personagens.Jogador;

public class Prologo {
    public static void jogar() {
        msg("=== FRAGMENTOS DO INFINITO ===\nSua existência foi despedaçada pela Força Divina. A busca começa.");
        int esc = ler("Escolha: 1-Mago | 2-Guerreiro | 3-Tanque | 4-Assassino", 4);
        System.arraycopy(MATRIZ[esc - 1], 0, Jogador.status, 0, 8);
        msg("✨ " + NOMES[esc - 1] + " selecionado!\n");
    }
}

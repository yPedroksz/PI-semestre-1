package historia;

import static util.Funcoes.*;
import personagens.Jogador;
import combate.Sistemacombate;

public class Finaljogo {
    public static void jogar() {
        mercador();
        msg("\n=== O JULGAMENTO FINAL ===");
        if (Jogador.corrompido) {
            msg("FINAL CORROMPIDO: Você se tornou uma casca vazia e escrava.");
        } else if (Jogador.frag <= 1) {
            msg("FINAL FRACO: Você foge para outra dimensão, perdendo sua irmã.");
        } else if (Jogador.frag == 2) {
            msg("FINAL DO SACRIFÍCIO: O seu corpo não vai aguentar, mas é a única chance.");
            if (Sistemacombate.lutou("Força Divina (Instável)", 350, 55, 0, 0, false)) 
                msg("Você venceu e a salvou, mas seu corpo virou poeira cósmica.");
        } else {
            msg("FINAL VERDADEIRO: A COMPLETUDE. Você domina a divindade!");
            if (Sistemacombate.lutou("Força Divina Suprema", 500, 70, 0, 0, false)) 
                msg("Universo restaurado. Vocês estão livres!");
        }
        msg("\n=== FIM DE JOGO ===");
        entrada.close();
    }
}

package package1;

import java.util.Scanner;

public class Main {

    // CONSTANTES DE INDEXAÇÃO - Padronização técnica para ADS
    static final int HP = 0;
    static final int MAX_HP = 1;
    static final int MANA = 2;
    static final int MAX_MANA = 3;
    static final int VIGOR = 4;
    static final int VELOCIDADE = 5;
    static final int INTELIGENCIA = 6;
    static final int FORCA = 7;

    // MATRIZ DE ATRIBUTOS BASE (Mago, Guerreiro, Tanque, Assassino)
    static final int[][] MATRIZ_CLASSES = {
        {100, 100, 150, 150, 10, 15, 25, 5},
        {140, 140, 40,  40,  15, 12,  8, 22},
        {200, 200, 30,  30,  25,  5,  8, 15},
        {90,  90,  50,  50,  12, 25, 10, 20}
    };

    static final String[] NOMES_CLASSES = {"Mago", "Guerreiro", "Tanque", "Assassino"};

    // VETOR DINÂMICO DO JOGADOR
    static int[] statusJogador = new int[8];
    static String classeNome = "";
    
    // Controle de Estado e Progressão
    static int fragmentos = 0;
    static boolean corrompido = false;
    static int ouro = 0; 
    static int xp = 0;
    static int nivel = 1;

    // Sistema de Inventário por Vetor
    static int[] inventario = new int[6]; 
    static final String[] NOMES_ITENS = {
        "Poção de Vida P", "Poção de Vida M", "Poção de Vida G",
        "Poção de Mana P", "Poção de Mana M", "Poção de Mana G"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        exibirIntroducao();
        
        int escolha = 0;
        while (escolha < 1 || escolha > 4) {
            System.out.println("Escolha sua postura de combate e canalize sua essência:");
            System.out.println("1 - Mago      | 2 - Guerreiro | 3 - Tanque    | 4 - Assassino");
            System.out.print("Sua escolha (1-4): ");
            if (scanner.hasNextInt()) {
                escolha = scanner.nextInt();
            } else {
                scanner.next(); 
            }
        }
        
        classeNome = NOMES_CLASSES[escolha - 1];
        System.arraycopy(MATRIZ_CLASSES[escolha - 1], 0, statusJogador, 0, 8);
        System.out.println("\n✨ Classe " + classeNome.toUpperCase() + " selecionada com sucesso!\n");

        // Fluxo Linear do Jogo
        faseCidade(scanner);
        visitarMercador(scanner, "Floresta do Esquecimento");
        faseFloresta(scanner);
        visitarMercador(scanner, "Ruínas Colossais");
        faseRuinas(scanner);
        visitarMercador(scanner, "O Confronto Final");
        julgamentoFinal(scanner);
        
        scanner.close();
    }

    public static void exibirIntroducao() {
        System.out.println("=================================================================================");
        System.out.println("                         FRAGMENTOS DO INFINITO                                  ");
        System.out.println("=================================================================================");
        System.out.println("Você e sua irmã atravessavam dimensões juntos como sempre fizeram. Mundos");
        System.out.println("diferentes, perigos diferentes, resolvendo tudo lado a lado. Até que uma presença");
        System.out.println("esmagadora fez o próprio tecido da realidade tremer: A Força Divina.");
        System.out.println("Ela decretou que a união de vocês era um erro. Antes que qualquer defesa pudesse");
        System.out.println("ser erguida, uma energia absurda os separa. O impacto despedaça sua existência.");
        System.out.println("\nVocê acorda fragmentado. Sua busca pelos fragmentos e por sua irmã começa agora.");
        System.out.println("=================================================================================\n");
    }

    public static void visitarMercador(Scanner scanner, String proximaFase) {
        System.out.println("\n=================================================================================");
        System.out.println("O caminho se abre brevemente. Antes de chegar à " + proximaFase.toUpperCase() + ",");
        System.out.println("você encontra o Mercador Dimensional. É sua última chance de preparação.");
        System.out.println("=================================================================================");
        
        while (true) {
            System.out.println("\n--- STATUS ATUAL ---");
            System.out.println("HP: " + statusJogador[HP] + "/" + statusJogador[MAX_HP] + " | Mana: " + statusJogador[MANA] + "/" + statusJogador[MAX_MANA] + " | Ouro: " + ouro + "g");
            System.out.println("1 - Poção de Vida P (+50 HP) -> 30g");
            System.out.println("2 - Poção de Mana P (+40 Mana) -> 40g");
            System.out.println("3 - Cristal de Vigor (+10 Vigor) -> 100g");
            System.out.println("4 - [Sair e Prosseguir]");
            System.out.print("Sua escolha: ");
            
            int item = scanner.nextInt();
            if (item == 1 && ouro >= 30) { ouro -= 30; inventario[0]++; System.out.println("🎒 Poção comprada!"); } 
            else if (item == 2 && ouro >= 40) { ouro -= 40; inventario[3]++; System.out.println("🎒 Poção comprada!"); } 
            else if (item == 3 && ouro >= 100) { ouro -= 100; statusJogador[VIGOR] += 10; System.out.println("💪 Vigor aumentado!"); } 
            else if (item == 4) break;
            else System.out.println("Ouro insuficiente ou opção inválida.");
        }
    }

    public static void checarEvolucao() {
        int limite = nivel * 100;
        if (xp >= limite) {
            xp -= limite;
            nivel++;
            statusJogador[MAX_HP] += 25; statusJogador[HP] = Math.min(statusJogador[MAX_HP], statusJogador[HP] + 25);
            statusJogador[MAX_MANA] += 15; statusJogador[MANA] = Math.min(statusJogador[MAX_MANA], statusJogador[MANA] + 15);
            statusJogador[VIGOR] += 4; statusJogador[VELOCIDADE] += 4;
            statusJogador[INTELIGENCIA] += 4; statusJogador[FORCA] += 4;
            System.out.println("\n✨ Nível " + nivel + " alcançado! Seus atributos matriciais aumentaram.");
        }
    }

    public static boolean abrirInventario(Scanner scanner) {
        System.out.println("\n🎒 --- INVENTÁRIO ---");
        boolean vazio = true;
        for (int i = 0; i < 6; i++) {
            if (inventario[i] > 0) {
                vazio = false;
                System.out.println((i + 1) + " - " + NOMES_ITENS[i] + " [Qtd: " + inventario[i] + "]");
            }
        }
        if (vazio) { System.out.println("Mochila vazia!"); return false; }

        System.out.println("7 - Voltar");
        int esc = scanner.nextInt();
        if (esc == 7) return false;

        int idx = esc - 1;
        if (idx >= 0 && idx < 6 && inventario[idx] > 0) {
            System.out.println("Usar " + NOMES_ITENS[idx] + "? (1-Sim / 2-Não)");
            if (scanner.nextInt() == 1) {
                inventario[idx]--;
                if (idx < 3) statusJogador[HP] = Math.min(statusJogador[MAX_HP], statusJogador[HP] + 50);
                else statusJogador[MANA] = Math.min(statusJogador[MAX_MANA], statusJogador[MANA] + 40);
                return true;
            }
        }
        return false;
    }

    public static boolean gerenciarCombate(Scanner scanner, String inimigo, int eHp, int eAtaque, int ganhoXp, int ganhoOuro) {
        System.out.println("\n⚔️ COMBATE: " + inimigo.toUpperCase());
        while (statusJogador[HP] > 0 && eHp > 0) {
            System.out.println("\n" + inimigo + " HP: " + eHp + " | Seu HP: " + statusJogador[HP]);
            int dFisico = (int)(statusJogador[FORCA] * 1.5) + 5;
            int dMagico = statusJogador[INTELIGENCIA] * 2;
            
            System.out.println("1 - Físico (" + dFisico + " dmg) | 2 - Magia (" + dMagico + " dmg / 20 Mana) | 3 - Bolsa");
            int acao = scanner.nextInt();
            boolean turnoGasto = true;

            if (acao == 1) eHp -= dFisico;
            else if (acao == 2 && statusJogador[MANA] >= 20) { statusJogador[MANA] -= 20; eHp -= dMagico; }
            else if (acao == 3) turnoGasto = abrirInventario(scanner);
            else { System.out.println("Falhou na ação!"); turnoGasto = false; }

            if (!turnoGasto) continue;

            if (eHp <= 0) {
                System.out.println("Inimigo derrotado!");
                xp += ganhoXp; ouro += ganhoOuro;
                checarEvolucao();
                return true;
            }

            int danoSofrido = eAtaque - (statusJogador[VIGOR] / 2);
            if (danoSofrido < 5) danoSofrido = 5;
            System.out.println("Inimigo ataca: -" + danoSofrido + " HP");
            statusJogador[HP] -= danoSofrido;
        }
        if (statusJogador[HP] <= 0) { System.out.println("GAME OVER."); System.exit(0); }
        return false;
    }

    public static void faseCidade(Scanner scanner) {
        System.out.println("\n--- CAPÍTULO 1: CIDADE DE NEON ---");
        System.out.println("Ladrões seguram seu PRIMEIRO FRAGMENTO num beco.");
        System.out.println("1 - Atacar | 2 - Tentar ser furtivo");
        if (scanner.nextInt() == 1) {
            if (gerenciarCombate(scanner, "Ladrões", 85, 22, 100, 200)) fragmentos++;
        } else {
            if (statusJogador[VELOCIDADE] >= 15) System.out.println("Fugiu, mas perdeu o fragmento.");
            else if (gerenciarCombate(scanner, "Ladrões em Alerta", 95, 25, 100, 50)) fragmentos++;
        }

        System.out.println("\nUm cientista oferece uma máquina de bio-restauração. Ela pulsa estranhamente.");
        System.out.println("1 - Entrar na máquina | 2 - Recusar");
        if (scanner.nextInt() == 1) { statusJogador[HP] = statusJogador[MAX_HP]; corrompido = true; System.out.println("Curado, mas sente vozes na mente..."); }
    }

    public static void faseFloresta(Scanner scanner) {
        System.out.println("\n--- CAPÍTULO 2: FLORESTA DO ESQUECIMENTO ---");
        System.out.println("Uma Feiticeira drena a floresta. SEGUNDO FRAGMENTO no centro.");
        System.out.println("[TESTE DE VIGOR]");
        if (statusJogador[VIGOR] >= 20) { System.out.println("Resistiu ao choque mental! -5 HP"); statusJogador[HP] -= 5; }
        else { System.out.println("Choque mental severo! -35 HP"); statusJogador[HP] -= 35; }
        
        System.out.println("1 - Atacar a Bruxa | 2 - Recuar");
        if (scanner.nextInt() == 1) {
            if (gerenciarCombate(scanner, "Feiticeira", 160, 35, 200, 100)) fragmentos++;
        }
    }

    public static void faseRuinas(Scanner scanner) {
        System.out.println("\n--- CAPÍTULO 3: RUÍNAS COLOSSAIS ---");
        System.out.println("O Guardião Ancestral guarda o ÚLTIMO FRAGMENTO.");
        System.out.println("1 - Escalar e ser furtivo | 2 - Ataque direto");
        if (scanner.nextInt() == 1) {
            if (statusJogador[INTELIGENCIA] >= 22 || statusJogador[FORCA] >= 22) { System.out.println("Extraiu sem lutar!"); fragmentos++; }
            else if (gerenciarCombate(scanner, "Guardião", 260, 45, 300, 150)) fragmentos++;
        } else {
            gerenciarCombate(scanner, "Guardião", 260, 45, 300, 150); fragmentos++;
        }
    }

    public static void julgamentoFinal(Scanner scanner) {
        System.out.println("\n=================================================================================");
        System.out.println("                               O JULGAMENTO FINAL                                ");
        System.out.println("=================================================================================");
        System.out.println("O vórtex se abre. A Força Divina desce para o confronto final.\n");

        if (corrompido) {
            System.out.println("💥 >>> FINAL: O CORROMPIDO <<< 💥");
            System.out.println("A máquina apagou quem você era. Você se torna um escravo da divindade.");
        } 
        else if (fragmentos <= 1) {
            System.out.println("🍂 >>> FINAL FRACO <<< 🍂");
            System.out.println("Fraco demais. Você foge para outra dimensão e abandona sua irmã.");
        } 
        else if (fragmentos == 2) {
            System.out.println("🔥 >>> FINAL: O SACRIFÍCIO <<< 🔥");
            System.out.println("Com 2 fragmentos, você tem uma chance. É matar ou morrer!");
            // COMBATE FINAL (Dificuldade Moderada)
            if (gerenciarCombate(scanner, "Força Divina (Poder Moderado)", 350, 55, 0, 0)) {
                System.out.println("\nVocê venceu, mas seu corpo colapsa. Sua irmã foge enquanto você se dissipa.");
            }
        } 
        else if (fragmentos == 3 && !corrompido) {
            System.out.println("👑 >>> FINAL VERDADEIRO: A COMPLETUDE <<< 👑");
            System.out.println("Completo e puro. A Força Divina treme diante do seu poder real!");
            // COMBATE FINAL (Dificuldade Suprema)
            if (gerenciarCombate(scanner, "A Força Divina Suprema", 500, 70, 0, 0)) {
                System.out.println("\nEntidade obliterada! Você e sua irmã estão juntos e livres para sempre.");
            }
        }
        System.out.println("\n================================ FIM DE JOGO =================================");
    }
}

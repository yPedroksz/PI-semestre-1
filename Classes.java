package personagens;

public class Classes {
    // Constantes de Indexação
    public static final int HP = 0, MAX_HP = 1, MANA = 2, MAX_MANA = 3, VIGOR = 4, VELOCIDADE = 5, INTEL = 6, FORCA = 7;
    
    // Matriz de Atributos Base
    public static final int[][] MATRIZ = {
        {100, 100, 150, 150, 10, 15, 25, 5},   // Mago
        {140, 140, 40,  40,  15, 12,  8, 22},  // Guerreiro
        {200, 200, 30,  30,  25,  5,  8, 15},  // Tanque
        {90,  90,  50,  50,  12, 25, 10, 20}   // Assassino
    };
    
    public static final String[] NOMES = {"Mago", "Guerreiro", "Tanque", "Assassino"};
}

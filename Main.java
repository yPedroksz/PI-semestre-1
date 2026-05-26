import historia.*;

public class Main {
    public static void main(String[] args) {
        // A arquitetura orientada a pacotes deixa a sua classe principal puramente orquestradora
        Prologo.jogar();
        Cidade.jogar();
        Floresta.jogar();
        Ruinas.jogar();
        Finaljogo.jogar();
    }
}

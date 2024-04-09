import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        JogadorFutebol jogador1 = new JogadorFutebol("Cristiano Ronaldo", "Atacante", 36, 1.87, 83.5);
        jogador1.mostrarDados();
        jogador1.tempoParaAposentar();
    }
}

class JogadorFutebol {
    private String nome;
    private String posicao;
    private int idade;
    private double altura;
    private double peso;
    private static final Map<String, Integer> IDADE_APOSENTADORIA;

    static {
        IDADE_APOSENTADORIA = new HashMap<>();
        IDADE_APOSENTADORIA.put("defesa", 40);
        IDADE_APOSENTADORIA.put("meio-campo", 38);
        IDADE_APOSENTADORIA.put("atacante", 36);
    }

    public JogadorFutebol(String nome, String posicao, int idade, double altura, double peso) {
        this.nome = nome;
        this.posicao = posicao;
        this.idade = idade;
        this.altura = altura;
        this.peso = peso;
    }

    public void mostrarDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Posição: " + posicao);
        System.out.println("Idade: " + idade);
        System.out.println("Altura: " + altura);
        System.out.println("Peso: " + peso);
    }

    public void tempoParaAposentar() {
        int idadeAposentadoria = IDADE_APOSENTADORIA.getOrDefault(posicao.toLowerCase(), -1);
        if (idadeAposentadoria == -1) {
            System.out.println("Não foi possível determinar a idade de aposentadoria para a posição " + posicao);
            return;
        }
        int anosRestantes = idadeAposentadoria - idade;
        if (anosRestantes <= 0) {
            System.out.println("O jogador " + nome + " já está aposentado.");
        } else {
            System.out.println("Faltam " + anosRestantes + " anos para " + nome + " se aposentar.");
        }
    }
}
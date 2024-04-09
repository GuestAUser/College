import java.util.*;

//Comentários estão só pra deixar a leitura mais simples possível em caso de uso para estudos.
//Code Made by: Lk10 Github(GuestAUser);


//Chamar main
public class Main {
    public static void main(String[] args) {
        JogadorFutebol jogador1 = new JogadorFutebol("Cristiano Neylaldo", "Atacante", 45, 2.20, 120);
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
	
	//Definindo a estrutura do hashmap;
    static {
        IDADE_APOSENTADORIA = new HashMap<>();
        IDADE_APOSENTADORIA.put("defesa", 40); //Objeto->Atributo;
        IDADE_APOSENTADORIA.put("meio-campo", 38); //Objeto->Atributo;
        IDADE_APOSENTADORIA.put("atacante", 36); //Objeto->Atributo;
    }
	
	//Definindo estrutura pública de nomes para definir o objeto(Jogador);
    public JogadorFutebol(String nome, String posicao, int idade, double altura, double peso) {
        this.nome = nome;
        this.posicao = posicao;
        this.idade = idade;
        this.altura = altura;
        this.peso = peso;
    }
	
	//Como os dados vão ser mostrados;
    public void mostrarDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Posição: " + posicao);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Altura: " + altura + " m");
        System.out.println("Peso: " + peso + " kg");
    }
	
	//Definir-> {Limite de tempo referente a [aposentadoria]}.
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
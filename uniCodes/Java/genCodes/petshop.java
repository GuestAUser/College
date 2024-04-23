public class Animal {
    private String especie;
    private String nome;
    private int idade;
    private String saude;
    private boolean vacinado;

    public Animal(String especie, String nome, int idade, String saude, boolean vacinado) {
        this.especie = especie;
        this.nome = nome;
        this.idade = idade;
        this.saude = saude;
        this.vacinado = vacinado;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSaude() {
        return saude;
    }

    public void setSaude(String saude) {
        this.saude = saude;
    }

    public boolean isVacinado() {
        return vacinado;
    }

    public void setVacinado(boolean vacinado) {
        this.vacinado = vacinado;
    }

    public void exibirInformacoes() {
        System.out.println("Especie: " + especie);
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Saude: " + saude);
        System.out.println("Vacinado: " + (vacinado ? "Sim" : "Nao"));
    }
}

public class Cachorro extends Animal {
    private String raca;

    public Cachorro(String especie, String nome, int idade, String saude, boolean vacinado, String raca) {
        super(especie, nome, idade, saude, vacinado);
        this.raca = raca;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Raca: " + raca);
    }
}

public class petshop {
    public static void main(String[] args) {
        Animal animal = new Animal("Papagaio", "Loro", 3, "Saudavel", true);
        animal.exibirInformacoes();
        System.out.println("\n\n");

        Cachorro cachorro = new Cachorro("Cachorro", "Rex", 5, "Saudavel", true, "Labrador");
        cachorro.exibirInformacoes();
    }
}

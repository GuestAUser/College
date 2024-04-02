import java.util.Scanner;

public class CalculoRoubo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int[] dinheiroPessoas = new int[10];
        int totalRoubado = 0;
        boolean checkVerdadeiro = false;
        
        System.out.println("Digite o dinheiro das 10 pessoas presas:");
        for (int i = 0; i < 10; i++) {
            dinheiroPessoas[i] = scanner.nextInt();
        }
        System.out.println("Digite o dinheiro que o bandido que se entregou roubou:");
        int dinheiroMenosRoubou = scanner.nextInt();
        
        while (checkVerdadeiro == false) {
            System.out.println("Informacao falsa!");
            dinheiroMenosRoubou = scanner.nextInt();
            if (dinheiroMenosRoubou % 10 == 0) {
                checkVerdadeiro = true;
            }
        }
        
        for (int dinheiro : dinheiroPessoas) {
            if (dinheiro % 10 == 0 && dinheiro > dinheiroMenosRoubou) {
                totalRoubado += dinheiro;
            }
        }
        
        System.out.println("O total de dinheiro roubado foi: " + totalRoubado);
    }
}

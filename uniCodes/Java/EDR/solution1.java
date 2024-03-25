package EDR;

import java.util.*;

public class solution1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type a number (1-10): ");
        int n = scanner.nextInt();
        if (n < 1 || n <= 10) {
            System.out.println("Número Valido.");
        } else {
            System.out.println("Número Invalido.");
            main(args);
        }
        scanner.close();
    }
}
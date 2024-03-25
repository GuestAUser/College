package EDR;

import java.util.*;

public class solution10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type a number: ");
        int n = scanner.nextInt();
        System.out.println("Type a number: ");
        int m = scanner.nextInt();
        System.out.println("=".repeat(25));
        for (int i = n+1; i <= m-1; i++) {
            System.out.println(i);
        }
        System.out.println("=".repeat(25));
        scanner.close();
    }
}
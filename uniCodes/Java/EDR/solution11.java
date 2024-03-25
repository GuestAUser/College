package EDR;

import java.util.*;

public class solution11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type a number: "); int n = scanner.nextInt(); 
        System.out.println("Type a number: "); int m = scanner.nextInt(); 
        System.out.println("=".repeat(25));

        StringBuilder sb = new StringBuilder();
        for (int i=n+1; i<=m-1;i++) {sb.append(i).append("\n");}
        System.out.print(sb.toString());
        
        int sum = (m-1)*m/2-n*(n+1)/2; System.out.println("Sum of all numbers: " + sum);
        System.out.println("=".repeat(25)); scanner.close();
    }
}
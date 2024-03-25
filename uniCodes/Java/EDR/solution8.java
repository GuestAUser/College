package EDR;

import java.util.*;

public class solution8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[5];
        int sum = 0;

        for (int i = 0; i < 5; i++) {
            System.out.println("Type a number ");
            numbers[i] = scanner.nextInt();
            sum += numbers[i];
        }
        System.out.println("Avarage number(sum): " + sum);
        scanner.close();
    }
}
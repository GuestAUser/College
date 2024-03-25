package EDR;

import java.util.*;

public class solution7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[5];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 5; i++) {
            System.out.println("Type a number ");
            numbers[i] = scanner.nextInt();
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        System.out.println("The highest number is: " + max);
        scanner.close();
    }
}
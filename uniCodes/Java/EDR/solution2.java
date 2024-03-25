package EDR;

import java.util.*;

public class solution2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type username: ");
        String n = scanner.nextLine();
        System.out.println("Type password: ");
        String p = scanner.nextLine();
        if (n.equals(p) == true) {
            System.out.println("Username needs to be different from password.");
            main(args);
        } else {
            System.out.println("Valid Registration.");
        }


        scanner.close();
    }
}
package EDR;

import java.util.Scanner;

public class solution3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter name:"); String name = scanner.nextLine(); if (name.length() <= 3) throw new IllegalArgumentException("Invalid name");
                System.out.println("Enter age:"); int age = scanner.nextInt(); if (age < 0 || age > 150) throw new IllegalArgumentException("Invalid age");
                System.out.println("Enter salary:"); double salary = scanner.nextDouble(); if (salary <= 0) throw new IllegalArgumentException("Invalid salary");
                System.out.println("Enter sex [m/f]:"); char sex = scanner.next().charAt(0); if (sex != 'f' && sex != 'm') throw new IllegalArgumentException("Invalid sex");
                System.out.println("Enter marital status[s, c, v, d]:"); char maritalStatus = scanner.next().charAt(0); if (maritalStatus != 's' && maritalStatus != 'c' && maritalStatus != 'v' && maritalStatus != 'd') throw new IllegalArgumentException("Invalid marital status");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + ". Please try again.");
                scanner.nextLine();
            }
        }
        System.out.println("Form has been successfully filled. Thank you.");
        scanner.close();
    }
}
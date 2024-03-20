package Solved;

import java.util.*;

public class stringManipTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type Athletes Age [dd/mm/aaaa]: ");
        String athleteAge = scanner.nextLine();
        String[] date = athleteAge.split("/");
        
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        
        Date currentDate = new Date();
        int currentDay = currentDate.getDay();
        int currentMonth = currentDate.getMonth();
        int currentYear = currentDate.getYear() + 1900;
        
        int age = currentYear - year;
        if (currentMonth < month || (currentMonth == month && currentDay < day)) {
            age--;
        }
        System.out.println("The athlete is " + age + " years old.");
        scanner.close();
    }
}

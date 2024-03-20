package Solved;

import java.util.*;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, HashMap<String, Double>> seatCosts = new HashMap<>();
        HashMap<String, Double> windowCosts = new HashMap<>();
        windowCosts.put("Normal", 300.00);
        windowCosts.put("Extra-Space", 350.00);
        seatCosts.put("Window", windowCosts);

        HashMap<String, Double> aisleCosts = new HashMap<>();
        aisleCosts.put("Normal", 280.00);
        aisleCosts.put("Extra-Space", 330.00);
        seatCosts.put("Aisle", aisleCosts);

        HashMap<String, Double> middleCosts = new HashMap<>();
        middleCosts.put("Normal", 250.00);
        middleCosts.put("Extra-Space", 300.00);
        seatCosts.put("Middle", middleCosts);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type of seat's available [Window, Aisle or Middle]: ");
        String x = scanner.nextLine();
        System.out.println("Space Selection [Normal, Extra-Space]: ");
        String y = scanner.nextLine();

        double cost = seatCosts.get(x).get(y);
        System.out.println("You selected a " + x + " seat with " + y + " space. The cost is R$ " + cost);

        scanner.close();
    }
}
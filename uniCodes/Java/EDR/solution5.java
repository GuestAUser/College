package EDR;

import java.util.*;

public class solution5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cityAPopulation = 80000;
        int cityBPopulation = 200000;
        System.out.println("Enter city A's growth rate: ");
        double cityAGrowthRate = scanner.nextDouble();
        System.out.println("Enter city B's growth rate: ");
        double cityBGrowthRate = scanner.nextDouble();
        int years = 0;
        while (cityAPopulation <= cityBPopulation) {
            cityAPopulation = (int) (cityAPopulation * (1 + cityAGrowthRate / 100));
            cityBPopulation = (int) (cityBPopulation * (1 + cityBGrowthRate / 100));
            years++;
        }
        System.out.println("City A will surpass City B in " + years + " years.");
        scanner.close();
    }
}
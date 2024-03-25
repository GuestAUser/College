package EDR;
public class solution4 {
    public static void main(String[] args) {
        int cityAPopulation = 80000;
        int cityBPopulation = 200000;
        double cityAGrowthRate = 3;
        double cityBGrowthRate = 1.5;
        int years = 0;
        while (cityAPopulation <= cityBPopulation) {
            cityAPopulation = (int) (cityAPopulation * (1 + cityAGrowthRate / 100));
            cityBPopulation = (int) (cityBPopulation * (1 + cityBGrowthRate / 100));
            years++;
        }
        System.out.println("City A will surpass City B in " + years + " years.");
    }
}
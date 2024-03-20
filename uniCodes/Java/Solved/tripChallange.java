package Solved;

import java.util.*;

import Solved.tripChallange.Preference; // imported module for item selections on map;

public class tripChallange {
    enum Preference {
        FAST, CHEAP, LESS
    }
    public static void main(String[] args) {
        Map<String, Map<Preference, Trip>> trips = new HashMap<>();
        addTrip(trips, new Trip("Paris", Preference.FAST, 7, 900.00, 1));
        addTrip(trips, new Trip("Paris", Preference.CHEAP, 12, 750.00, 2));
        addTrip(trips, new Trip("Paris", Preference.LESS, 10, 850, 1));
        addTrip(trips, new Trip("New York", Preference.FAST, 15, 800.00, 3));
        addTrip(trips, new Trip("New York", Preference.CHEAP, 20, 650.00, 4));
        addTrip(trips, new Trip("New York", Preference.LESS, 18, 750.00, 3));
        addTrip(trips, new Trip("Tokyo", Preference.FAST, 20, 1200.00, 0));
        addTrip(trips, new Trip("Tokyo", Preference.CHEAP, 25, 1000, 1));
        addTrip(trips, new Trip("Tokyo", Preference.LESS, 22, 1100.00, 1));

        Scanner scanner = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("--- psc.Solutions Started ---");
        System.out.println("Trip Destination [Tokyo | New York | Paris]: ");
        
        String destination = scanner.nextLine();
        System.out.println("Passenger Preference [Fast | Cheap | Less]: ");
        
        Preference preference = Preference.valueOf(scanner.nextLine().toUpperCase());
        Trip trip = trips.get(destination).get(preference);
        printTripDetails(trip);

        scanner.close();
    }

    private static void addTrip(Map<String, Map<Preference, Trip>> trips, Trip trip) {
        trips.computeIfAbsent(trip.destination, k -> new HashMap<>()).put(trip.preference, trip);
    }

    private static void printTripDetails(Trip trip) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("=".repeat(25));
        System.out.println("Destination: " + trip.destination);
        System.out.println("Preference: " + trip.preference);
        System.out.println("Duration: " + trip.duration + "hrs");
        System.out.println("Cost: R$" + trip.cost);
        
        if (trip.scales == 0) {
            System.out.println("Scales: Direct Flight");
        } else {
            System.out.println("Scales: " + trip.scales);
        }
        System.out.println("=".repeat(25));
    }
}

class Trip {
    String destination;
    Preference preference;
    int duration;
    double cost;
    int scales;
    
    Trip(String destination, Preference preference, int duration, double cost, int scales) {
        this.destination = destination;
        this.preference = preference;
        this.duration = duration;
        this.cost = cost;
        this.scales = scales;
    }
}

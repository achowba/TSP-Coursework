/*

The algorithm for this code was gotten from:
Lee Jacobson's article on  "Applying a genetic algorithm to the traveling salesman problem" published on (20/08/2012).
Available at: http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5 (Retrieved: 29/11/2018).

*/

// Trip.java
// Stores a candidate trip

package tsp_coursework1;

/**
 *
 * @author PRINCE
 */

import java.util.ArrayList;
import java.util.Collections;

public class Trip {

    // Holds our trip of cities
    private ArrayList trip = new ArrayList<City>();
    // Cache
    private double fitness = 0;
    private int distance = 0;

    // Constructs a blank trip
    public Trip(){
        for (int i = 0; i < TripManager.numberOfCities(); i++) {
            trip.add(null);
        }
    }

    public Trip(ArrayList trip){
        this.trip = trip;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all our destination cities and add them to our trip
        for (int cityIndex = 0; cityIndex < TripManager.numberOfCities(); cityIndex++) {
            setCity(cityIndex, TripManager.getCity(cityIndex));
        }
        // Randomly reorder the trip
        Collections.shuffle(trip);
    }

    // Gets a city from the trip
    public City getCity(int tripPosition) {
        return (City)trip.get(tripPosition);
    }

    // Sets a city in a certain position within a trip
    public void setCity(int tripPosition, City city) {
        trip.set(tripPosition, city);
        // If the trips been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }

    // Gets the trips fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }

    // Gets the total distance of the trip
    public int getDistance(){
        if (distance == 0) {
            int tripDistance = 0;
            // Loop through our trip's cities
            for (int cityIndex=0; cityIndex < tripSize(); cityIndex++) {
                // Get city we're travelling from
                City fromCity = getCity(cityIndex);
                // City we're travelling to
                City destinationCity;
                // Check we're not on our trip's last city, if we are set our
                // trip's final destination city to our starting city
                if(cityIndex + 1 < tripSize()){
                    destinationCity = getCity(cityIndex+1);
                } else {
                    destinationCity = getCity(0);
                }
                // Get the distance between the two cities
                tripDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tripDistance;
        }
        return distance;
    }

    // Get number of cities on our trip
    public int tripSize() {
        return trip.size();
    }

    // Check if the trip contains a city
    public boolean containsCity(City city){
        return trip.contains(city);
    }

    @Override
    public String toString() {
        String geneString = "| ";
        for (int i = 0; i < tripSize(); i++) {
            geneString += getCity(i) + " | ";
        }
        return geneString;
    }
}

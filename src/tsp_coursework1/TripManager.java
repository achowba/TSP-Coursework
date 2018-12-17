/*

The algorithm for this code was gotten from:
Lee Jacobson's article on  "Applying a genetic algorithm to the traveling salesman problem" published on (20/08/2012).
Available at: http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5 (Retrieved: 29/11/2018).

*/

// TripManager.java
// This code holds the cities of a trip

package tsp_coursework1;

/**
 *
 * @author PRINCE
 */

import java.util.ArrayList;

public class TripManager {

    // Holds our cities
    private static ArrayList destinationCities = new ArrayList<City>(); // create a new ArrayList to hold the given cities

    // Adds a destination city
    public static void addCity(City city) {
        destinationCities.add(city); // add the next city to be visited
    }

    // Get a city
    public static City getCity(int index){
        return (City) destinationCities.get(index); // return the a city based on its index in the ArrayList
    }

    // Get the number of destination cities
    public static int numberOfCities(){
        return destinationCities.size(); // get the number of cities left to be visited
    }
}

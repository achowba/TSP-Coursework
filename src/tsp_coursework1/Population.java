/*

The algorithm for this code was gotten from:
Lee Jacobson's article on  "Applying a genetic algorithm to the traveling salesman problem" published on (20/08/2012).
Available at: http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5 (Retrieved: 29/11/2018).

*/

// Population.java
// Manages a population of candidate trips

package tsp_coursework1;

/**
 *
 * @author PRINCE
 */

public class Population {

    // Holds population of trips
    Trip [] trips;

    // Construct a population
    public Population(int populationSize, boolean initialise) {
        trips = new Trip[populationSize];

        // If we need to initialise a population of trips do so
        if (initialise) {

            // Loop and create individuals
            for (int i = 0; i < populationSize(); i++) {
                Trip newTrip = new Trip();
                newTrip.generateIndividual();
                saveTrip(i, newTrip);
            }
        }
    }

    // Saves a trip
    public void saveTrip(int index, Trip trip) {
        trips[index] = trip;
    }

    // Gets a trip from population
    public Trip getTrip(int index) {
        return trips[index];
    }

    // Gets the best trip in the population
    public Trip getFittest() {
        Trip fittest = trips[0];

        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTrip(i).getFitness()) {
                fittest = getTrip(i);
            }
        }
        return fittest;
    }

    // Gets population size
    public int populationSize() {
        return trips.length;
    }
}

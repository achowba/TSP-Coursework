/*

The algorithm for this code was gotten from:
Lee Jacobson's article on  "Applying a genetic algorithm to the traveling salesman problem" published on (20/08/2012).
Available at: http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5 (Retrieved: 29/11/2018).

*/

// GeneticAlgorithm.java
// Manages algorithms for evolving population

package tsp_coursework1;

/**
 *
 * @author PRINCE
 */

public class GeneticAlgorithm {

    /* GeneticAlgorithm parameters */
    private static final double mutationRate = 0.015;
    private static final int tripSize = 5;
    private static final boolean elitism = true;

    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTrip(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            Trip parent1 = tripSelection(pop);
            Trip parent2 = tripSelection(pop);
            // Crossover parents
            Trip child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.saveTrip(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTrip(i));
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Trip crossover(Trip parent1, Trip parent2) {
        // Create new child trip
        Trip child = new Trip();

        // Get start and end sub trip positions for parent1's trip
        int startPos = (int) (Math.random() * parent1.tripSize());
        int endPos = (int) (Math.random() * parent1.tripSize());

        // Loop and add the sub trip from parent1 to our child
        for (int i = 0; i < child.tripSize(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } else if (startPos > endPos) { // If our start position is larger
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // Loop through parent2's city trip
        for (int i = 0; i < parent2.tripSize(); i++) {
            // If child doesn't have the city add it
            if (!child.containsCity(parent2.getCity(i))) {
                // Loop to find a spare position in the child's trip
                for (int ii = 0; ii < child.tripSize(); ii++) {
                    // Spare position found, add city
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Mutate a trip using swap mutation
    private static void mutate (Trip trip) {
        // Loop through trip cities
        for(int tripPos1 = 0; tripPos1 < trip.tripSize(); tripPos1++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the trip
                int tripPos2 = (int) (trip.tripSize() * Math.random());

                // Get the cities at target position in trip
                City city1 = trip.getCity(tripPos1);
                City city2 = trip.getCity(tripPos2);

                // Swap them around
                trip.setCity(tripPos2, city1);
                trip.setCity(tripPos1, city2);
            }
        }
    }

    // Selects candidate trip for crossover
    private static Trip tripSelection(Population pop) {
        // Create a trip population
        Population trip = new Population(tripSize, false);
        // For each place in the trip get a random candidate trip and
        // add it
        for (int i = 0; i < tripSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            trip.saveTrip(i, pop.getTrip(randomId));
        }
        // Get the fittest trip
        Trip fittest = trip.getFittest();
        return fittest;
    }
}

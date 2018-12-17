/*

The algorithm for this code was gotten from:
Lee Jacobson's article on  "Applying a genetic algorithm to the traveling salesman problem" published on (20/08/2012).
Available at: http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5 (Retrieved: 29/11/2018).

*/

// City.java
// This code models a city to be visited by the salesman

package tsp_coursework1;

/**
 *
 * @author PRINCE
 */

public class City {
    int x; // x coordinates of the city
    int y; // x coordinates of the city

    // Constructs a randomly placed city
    public City(){
        this.x = (int)(Math.random() * 200);
        this.y = (int)(Math.random() * 200);
    }

    // Construct a city at chosen x, y location
    public City(int x, int y){
        this.x = x;
        this.y = y;
    }

    // Gets city's x coordinate
    public int getX(){
        return this.x;
    }

    // Gets city's y coordinate
    public int getY(){
        return this.y;
    }

    // Gets the distance to given city
    public double distanceTo(City city){
        int xDistance = Math.abs(getX() - city.getX()); // get xDistance between cities
        int yDistance = Math.abs(getY() - city.getY()); // get yDistance between cities
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance * yDistance) ); // calculate the Euclidean distance between two cities

        return distance;
    }

    @Override
    public String toString(){
        return getX() + ", " + getY();
    }
}

/*

The algorithm for this code was gotten from:
Lee Jacobson's article on  "Applying a genetic algorithm to the traveling salesman problem" published on (20/08/2012).
Available at: http://www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5 (Retrieved: 29/11/2018).

*/

// TSP_GA.java
// Create a trip and evolve a solution

package tsp_coursework1;

/**
 *
 * @author PRINCE
 */

import javax.swing.*;
import java.io.*;

public class TSP_GA {

    public static void main(String[] args) throws Exception {

        JFileChooser fileChooser = new JFileChooser(); // initialize the file chooser in order to select text file
        fileChooser.getFileSystemView().getDefaultDirectory().toString(); //
        int result = fileChooser.showOpenDialog(null); // show the open file dialog

        if (result == JFileChooser.APPROVE_OPTION) { // if the file is selected, read it and stream the data
            File selectedFile = fileChooser.getSelectedFile();

            // if statement to ensure only txt files are selected
            if (getFileExtension(selectedFile).equals("txt")) {
                FileInputStream fis = new FileInputStream(selectedFile); // create a FileInputStream class to get the selectedFile
                DataInputStream in = new DataInputStream(fis); // pass the FileInputStream to the DataInputStream to be read as a primitive type
                BufferedReader br = new BufferedReader(new InputStreamReader(in)); // create a BufferedReader from the InputStreamReader

                String strLine;
                while ((strLine = br.readLine()) != null) { // while there is still data in the text file, keep on reading data from it
                    String [] tokens = strLine.split("\\s+"); // remove white space from the text file

                    /* this if else was done as a hack due to the presence of a empty array element when reading some of the text files that contained spaces before the city number */
                    // add the new city with the x and y coordinates from the array and then add each of the cities to the trip
                    if (tokens.length == 3) {
                        City city = new City(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                        TripManager.addCity(city);
                    } else if (tokens.length > 3) {
                        City city = new City(Integer.parseInt(tokens[1 + 1]), Integer.parseInt(tokens[2 + 1]));
                        TripManager.addCity(city);
                    }
                }

                in.close(); // close the stream

                long startTime = System.nanoTime();
                // Create and add our cities

                System.out.println("FINISHED CALCULATING SHORTEST PATH!");

                // Initialize population
                Population pop = new Population(50, true);
                System.out.println("Initial distance: " + pop.getFittest().getDistance());

                // Evolve population for 100 generations
                pop = GeneticAlgorithm.evolvePopulation(pop);
                for (int i = 0; i < 100; i++) {
                    pop = GeneticAlgorithm.evolvePopulation(pop);
                }

                // Print final results
                System.out.println("Final distance: " + pop.getFittest().getDistance());
                System.out.println("Solution:");
                System.out.println(pop.getFittest());
                System.out.println( "Total Time taken: " + ((System.nanoTime() - startTime) / 1000000) + "ms");
            } else {
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Please only .txt files are allowed!", "Invalid File Type", JOptionPane.ERROR_MESSAGE);
                System.out.println("Wrong File Type Selected!");
            }
        }
    }

    // method to get the extension of the file to be read
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".")+1);
        } else {
            return "";
        }
    }
}

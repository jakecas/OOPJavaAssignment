package oop.assignment;

import oop.assignment.exceptions.MalformedCommandException;
import oop.assignment.restaurant.exceptions.RestaurantException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * The main class for the restaurant app, it iterates over the lines of the file passed as a command line argument,
 * or the default test file if none were given, executing each line individually
 * through the single RestaurantAppConnector instance.
 *
 * At the end, it prints the total spent, and the details of the restaurant with the highest revenue.
 */
public class RestaurantAppLauncher {
    private static final String DEFAULT_TEST_FILE = "defaulttestfile.txt";

    public static void main(String[] args) {
        RestaurantAppConnector restaurantAppConnector = RestaurantAppConnector.getInstance();

        String filename = (args.length == 1) ? args[0] : DEFAULT_TEST_FILE;
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
                try {
                    restaurantAppConnector.executeLine(lineSplitter(line));
                } catch(MalformedCommandException | RestaurantException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
            System.exit(-1);
        }

        restaurantAppConnector.printTotal();
        restaurantAppConnector.printHighestRevenuRestaurant();
    }

    /**
     * Splits the string passed on whitespace.
     *
     * @param line a line from a text file
     * @return a list of the words found in the line
     */
    public static List<String> lineSplitter(String line){
        return Arrays.asList(line.split(" "));
    }

}

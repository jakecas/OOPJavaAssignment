package oop.assignment;

import oop.assignment.exceptions.MalformedCommandException;
import oop.assignment.restaurant.exceptions.RestaurantException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RestaurantAppLauncher {
    private static final String DEFAULT_TEST_FILE = "defaulttestfile.txt";

    public static void main(String[] args) {
        RestaurantAppConnector restaurantAppConnector = RestaurantAppConnector.getInstance();

        try(BufferedReader br = new BufferedReader(new FileReader(DEFAULT_TEST_FILE))){
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
                try {
                    restaurantAppConnector.executeLine(lineSplitter(line));
                } catch(MalformedCommandException e){
                    System.out.println(e.getMessage());
                    System.exit(-1);
                } catch (RestaurantException e){
                    System.out.println(e.getMessage());
                    System.exit(-2);
                }
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
            System.exit(-1);
        }

        restaurantAppConnector.printTotal();
        restaurantAppConnector.printHighestRevenuRestaurant();
    }

    public static List<String> lineSplitter(String line){
        return Arrays.asList(line.split(" "));
    }

}

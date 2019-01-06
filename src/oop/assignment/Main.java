package oop.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String DEFAULT_TEST_FILE = "defaulttextfile.txt";

    public static void main(String[] args) {
        RestaurantAppController restaurantAppController = RestaurantAppController.getInstance();

        try(BufferedReader br = new BufferedReader(new FileReader(DEFAULT_TEST_FILE))){
            String line;
            while((line = br.readLine()) != null){
                restaurantAppController.executeLine(lineSplitter(line));
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
            System.exit(-1);
        }
    }

    public static List<String> lineSplitter(String line){
        return Arrays.asList(line.split(" "));
    }

}

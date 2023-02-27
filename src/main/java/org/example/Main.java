package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Create a new Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a search topic
        System.out.print("Enter a search topic: ");

        // Read the user's input and store it in the "searchTopic" variable
        String searchTopic = scanner.nextLine();

        // Create a new instance of the WikipediaSearch class
        WikipediaSearch wikipediaSearch = new WikipediaSearch();

        // Call the search() method of the WikipediaSearch class with the "searchTopic" variable as the argument
        wikipediaSearch.search(searchTopic);
    }
}

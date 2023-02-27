package org.example;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //create scanner to input
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a search topic: ");
        String searchTopic = scanner.nextLine();
        //create object to call search()
        WikipediaSearch wikipediaSearch = new WikipediaSearch();
        //call search()
        wikipediaSearch.search(searchTopic);
    }
}

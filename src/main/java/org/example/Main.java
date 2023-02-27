package org.example;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a search topic: ");
        String searchTopic = scanner.nextLine();

        WikipediaSearch wikipediaSearch = new WikipediaSearch();
        wikipediaSearch.search(searchTopic);
    }
}

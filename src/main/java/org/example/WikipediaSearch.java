package org.example;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WikipediaSearch {

    // Define the URL of the Wikipedia API search endpoint
    private static final String WIKIPEDIA_API_URL =
            "https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch=";

    // Initialize the OkHttp client and Gson parser
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    // Method for searching Wikipedia with a given search term
    public void search(String searchTopic) {
        // Build the API URL by appending the search term to the base URL
        String apiUrl = WIKIPEDIA_API_URL + searchTopic;

        // Create an HTTP GET request with the API URL
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try {
            // Send the HTTP request and get the response
            Response response = client.newCall(request).execute();
            // Read the JSON response as a string
            String jsonResponse = response.body().string();

            // Parse the JSON response into a JsonObject
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
            // Get the search results from the JsonObject
            JsonArray searchResults = jsonObject.getAsJsonObject("query").getAsJsonArray("search");

            // Iterate over the search results and print the title, URL, and snippets of the first result
            for (int i = 0; i < Math.min(1, searchResults.size()); i++) {
                // Get the title and URL of the search result
                JsonObject result = searchResults.get(i).getAsJsonObject();
                String title = result.get("title").getAsString();
                String url = "https://en.wikipedia.org/wiki/" + title.replace(" ", "_");

                // Get the snippet of the search result and remove HTML tags
                String snippet = result.get("snippet").getAsString().replaceAll("<.*?>", "");

                // Print the title, URL, and snippets of the search result
                System.out.println("Title:    " + title);
                System.out.println("URL:      " + url);
                System.out.println("Snippet 1:    " + snippet.substring(0, Math.min(snippet.length(), 30)) + "...");
                System.out.println("Snippet 2:    " + snippet.substring(31, Math.min(snippet.length(), 60)) + "...");
                System.out.println("Snippet 3:    " + snippet.substring(61, Math.min(snippet.length(), 100)) + "...");
                System.out.println();
            }
        } catch (IOException e) {
            // Handle any IO errors
            e.printStackTrace();
        }
    }
}

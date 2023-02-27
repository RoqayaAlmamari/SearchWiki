package org.example;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WikipediaSearch {

    private static final String WIKIPEDIA_API_URL =
            "https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch="; //use the search url of wikipedia to get the name of topic and other information

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();


    //add method for search topic and test it in Main Class
    public void search(String searchTopic) {
        String apiUrl = WIKIPEDIA_API_URL + searchTopic; //use the api url to search topic

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();


        try {
            Response response = client.newCall(request).execute();
            String jsonResponse = response.body().string();

            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
            JsonArray searchResults = jsonObject.getAsJsonObject("query").getAsJsonArray("search");

            for (int i = 0; i < Math.min(1, searchResults.size()); i++) {
                JsonObject result = searchResults.get(i).getAsJsonObject();
                String title = result.get("title").getAsString();
                //String snippet = result.get("snippet").getAsString();
                String snippet = result.get("snippet").getAsString().replaceAll("<.*?>", ""); // remove HTML tags
                //snippet = snippet.substring(0, Math.min(snippet.length(), 500)) + "...";

                String url = "https://en.wikipedia.org/wiki/" + title.replace(" ", "_");


                System.out.println("Title: " + title);
                System.out.println("URL: " + url);

                //to get the snippets from different line in wikipedia site
                System.out.println("Snippet 1: " + snippet.substring(0, Math.min(snippet.length(), 30)) + "...");
                System.out.println("Snippet 2: " + snippet.substring(31, Math.min(snippet.length(), 60)) + "...");
                System.out.println("Snippet 3: " + snippet.substring(61, Math.min(snippet.length(), 100)) + "...");
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

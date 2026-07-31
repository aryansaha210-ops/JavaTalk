package com.aryan.javatalk.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AIService {

    // Paste your OpenRouter API key here
    private static final String API_KEY = System.getenv("OPENROUTER_API_KEY");

    // Free model
    private static final String MODEL = "nvidia/nemotron-3-ultra-550b-a55b:free";

    public static String askAI(String prompt) {

        try {

            HttpClient client = HttpClient.newHttpClient();

            String json = """
                    {
                      "model": "%s",
                      "messages": [
                        {
                          "role": "user",
                          "content": "%s"
                        }
                      ]
                    }
                    """.formatted(MODEL, prompt.replace("\"", "\\\""));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://openrouter.ai/api/v1/chat/completions"))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .header("HTTP-Referer", "https://github.com/aryan/javatalk")
                    .header("X-Title", "JavaTalk")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());

            if (root.has("error")) {
                return "API Error: " + root.get("error").get("message").asText();
            }

            return root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
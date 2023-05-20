package com.niilo.game.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niilo.game.userinterface.HighScore;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class RequestSender {

    public static void sendHighScore(Integer score) {
        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            HighScore highScore = new HighScore(score);
            String requestBody = objectMapper.writeValueAsString(highScore);
            System.out.println(highScore);
            HttpPost httpPost = new HttpPost("http://localhost:8080/api/highscore/add");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(requestBody));
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println(response.getStatusLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

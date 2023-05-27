package com.niilo.game.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niilo.game.backend.HighScore;
import com.niilo.game.backend.Player;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.ArrayList;
import java.util.List;

public class RequestSender {

    public static void sendHighScore(Integer score, String name) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Player player = new Player(1L, name);
            List<HighScore> highScores = new ArrayList<>();
            HighScore highScore = new HighScore(score, player);
            highScores.add(highScore);
            String requestBody = objectMapper.writeValueAsString(highScores);
            System.out.println(requestBody);
            HttpPut httpPut = new HttpPut("http://localhost:8080/api/highscore/addall");
            httpPut.setHeader("Content-Type", "application/json");
            httpPut.setEntity(new StringEntity(requestBody));
            HttpResponse response = httpClient.execute(httpPut);
            System.out.println(response.getStatusLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.niilo.game.http;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.HttpRequestHeader;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.niilo.game.backend.HighScore;
import com.niilo.game.backend.Player;

import java.util.ArrayList;
import java.util.List;

public class RequestSender {

    public static void sendHighScore(Integer score, String name) {
        try {
            String requestBody = getRequestBody(score, name);
            sendRequest(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getRequestBody(Integer score, String name) {
        Player player = new Player(1L, name);
        List<HighScore> highScores = new ArrayList<>();
        HighScore highScore = new HighScore(score, player);
        highScores.add(highScore);
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String requestBody = json.toJson(highScores);
        System.out.println(requestBody);
        return requestBody;
    }

    private static void sendRequest(String requestBody) {
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        requestBuilder.newRequest();
        requestBuilder.header(HttpRequestHeader.ContentType, "application/json");
        requestBuilder.content(requestBody);
        requestBuilder.method(Net.HttpMethods.PUT);
        requestBuilder.url("http://localhost:8080/api/highscore/addall");
        Net.HttpRequest httpRequest = requestBuilder.build();
        Net.HttpResponseListener httpResponseListener = new MyHttpListener();
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);
    }

    public static class MyHttpListener implements Net.HttpResponseListener {
        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            System.out.println(httpResponse.getStatus().getStatusCode());
            System.out.println(httpResponse.getResultAsString());
        }

        @Override
        public void failed(Throwable t) {

        }

        @Override
        public void cancelled() {

        }
    }

}

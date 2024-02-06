package org.goit.constants;

import com.google.gson.Gson;

import java.net.http.HttpClient;

public class Constants {
    private Constants() {}
    public static final String URL_USERS = "https://jsonplaceholder.typicode.com/users";
    public static final String URL_POSTS = "https://jsonplaceholder.typicode.com/posts";
    public static final Gson GSON = new Gson();
    public static final HttpClient CLIENT = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
}

package org.goit.task1;

import com.google.gson.reflect.TypeToken;
import org.goit.task1.user.MyCustomUser;
import org.goit.task1.user.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.goit.constants.Constants.*;

public class TaskOne {
    public static void main(String[] args) throws IOException, InterruptedException {

        String jsonData = GSON.toJson(MyCustomUser.USER);

        System.out.println(createUser(jsonData));

        System.out.println(overwriteUser(jsonData, 9));

        removeUser(9);

        System.out.println(getAllUsers());

        System.out.println(getUserByID(10));

        System.out.println(getUserByUserName("Moriah.Stanton"));

    }


    public static User createUser(String jsonData) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(URL_USERS))
                .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response = " + response);
        return GSON.fromJson(response.body(), User.class);
    }

    public static User overwriteUser(String jsonData, int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(URL_USERS +"/"+id))
                .header("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonData))
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response = " + response);
        return GSON.fromJson(response.body(), User.class);
    }

    public static void removeUser(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(URL_USERS +"/"+id))
                .header("Content-type", "application/json")
                .DELETE()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response = " + response);
    }

    public static List<User> getAllUsers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS))
                .header("Content-type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response = " + response);
        Type type = TypeToken.getParameterized(List.class, User.class).getType();
        List<User> result = GSON.fromJson(response.body(), type);
        return result;
    }

    public static User getUserByID(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS + "/" + id))
                .header("Content-type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response = " + response);
        return GSON.fromJson(response.body(), User.class);
    }

    public static User getUserByUserName(String username) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS + "?username=" + username))
                .header("Content-type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response = " + response);
        Type type = TypeToken.getParameterized(List.class, User.class).getType();
        List<User> users = GSON.fromJson(response.body(), type);
        return users.get(0);
    }
}

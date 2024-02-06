package org.goit.task3;

import com.google.gson.reflect.TypeToken;
import org.goit.task3.todo.Todo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.goit.constants.Constants.*;

public class TaskThree {
    public static void main(String[] args) throws IOException, InterruptedException {

        List<Todo> userTodos = getUserTodos(1);

        userTodos.forEach(System.out::println);

    }

    public static List<Todo> getUserTodos(int userID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS + "/" + userID + "/todos"))
                .header("Content-type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response = " + response);
        Type type = TypeToken.getParameterized(List.class, Todo.class).getType();
        List<Todo> todos = GSON.fromJson(response.body(), type);
        return todos.stream().filter(x -> !x.isCompleted()).toList();
    }
}

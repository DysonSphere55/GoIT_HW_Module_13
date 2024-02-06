package org.goit.task2;

import com.google.gson.reflect.TypeToken;
import org.goit.task2.comment.Comment;
import org.goit.task2.post.Post;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static org.goit.constants.Constants.*;

public class TaskTwo {
    public static void main(String[] args) throws IOException, InterruptedException {

        Post userLastPost = getUserLastPost(10);

        List<Comment> postComments = getPostComments(userLastPost.getId());

        saveFile(10, userLastPost.getId(), postComments);
    }

    public static Post getUserLastPost(int userID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USERS +"/"+userID+"/posts"))
                .header("Content-type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response = " + response);
        Type type = TypeToken.getParameterized(List.class, Post.class).getType();
        List<Post> posts = GSON.fromJson(response.body(), type);
        return posts.stream().sorted(Comparator.comparing(Post::getId).reversed()).findFirst().orElseThrow();
    }

    public static List<Comment> getPostComments(int postID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_POSTS+"/"+postID+"/comments"))
                .header("Content-type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response = " + response);
        Type type = TypeToken.getParameterized(List.class, Comment.class).getType();
        List<Comment> comments = GSON.fromJson(response.body(), type);
        return comments;
    }

    public static void saveFile(int userID, int postID, List<Comment> comments) throws IOException {
        Path directory = Files.createDirectory(Path.of("./TaskTwoOutput"));
        try (FileWriter fw = new FileWriter(directory+"/user-"+userID+"-post-"+postID+"-comments.json")) {
            for (Comment comment : comments) {
                fw.write(GSON.toJson(comment));
            }
        }
    }
}

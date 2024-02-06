package org.goit.task2.comment;

import lombok.Data;

@Data
public class Comment {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
    public Comment(int postId, int id, String name, String email, String body) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }
}

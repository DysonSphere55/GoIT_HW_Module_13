package org.goit.task3.todo;

import lombok.Data;

@Data
public class Todo {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public Todo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}



/*
{
    "userId": 1,
    "id": 1,
    "title": "delectus aut autem",
    "completed": false
  },
 */
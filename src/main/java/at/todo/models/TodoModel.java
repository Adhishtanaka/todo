package at.todo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
public class TodoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String todoTitle;
    @Column(nullable = false)
    private boolean status;
    @Column(nullable = false)
    private LocalDateTime time;

    public TodoModel() {}
    public TodoModel(String userId, String todoTitle, boolean status, LocalDateTime time) {
        this.userId = userId;
        this.todoTitle = todoTitle;
        this.status = status;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


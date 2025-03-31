package at.todo.services;

import at.todo.models.TodoModel;

import java.util.List;

public interface TodoRepository
{
    public List<TodoModel> getAllTodo ();
    public void addTodo();
    public void UpdateTodo();
    public void deleteTodo();
}

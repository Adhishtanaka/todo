package at.todo.services;


import at.todo.models.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoModel> getTodosByUserId(String userId) {
        return todoRepository.findByUserId(userId);
    }

    public Optional<TodoModel> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public TodoModel createTodo(TodoModel todo) {
        if (todo.getTime() == null) {
            todo.setTime(LocalDateTime.now());
        }
        return todoRepository.save(todo);
    }

    public TodoModel updateTodo(Long id, TodoModel updatedTodo) {
        Optional<TodoModel> existingTodo = todoRepository.findById(id);
        if (existingTodo.isPresent()) {
            TodoModel todo = existingTodo.get();
            todo.setTodoTitle(updatedTodo.getTodoTitle());
            todo.setStatus(updatedTodo.isStatus());
            return todoRepository.save(todo);
        }
        return null;
    }

    public boolean deleteTodo(Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

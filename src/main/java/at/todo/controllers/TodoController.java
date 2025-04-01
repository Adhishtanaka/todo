package at.todo.controllers;

import at.todo.models.TodoModel;
import at.todo.services.TodoService;
import at.todo.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public ResponseEntity<List<TodoModel>> getTodosByUserId() {
        String userId = SecurityUtils.getCurrentUserId();
        List<TodoModel> todos = todoService.getTodosByUserId(userId);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TodoModel>> getTodoById(@PathVariable Long id) {
        String userId = SecurityUtils.getCurrentUserId();
        Optional<TodoModel> todoOptional = todoService.getTodoById(id);
        if (todoOptional.isPresent()) {
            TodoModel todo = todoOptional.get();
            if (!todo.getUserId().equals(userId)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        return new ResponseEntity<>(todoOptional, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TodoModel> createTodo(@RequestBody TodoModel todoModel) {
        TodoModel Todo = todoService.createTodo(todoModel);
        return new ResponseEntity<>(Todo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoModel> updateTodo(@PathVariable Long id, @RequestBody TodoModel todomodel) {
        String userId = SecurityUtils.getCurrentUserId();
        Optional<TodoModel> todoOptional = todoService.getTodoById(id);
        if (todoOptional.isPresent()) {
            TodoModel existingTodo = todoOptional.get();
            if (!existingTodo.getUserId().equals(userId)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        TodoModel updatedTodo = todoService.updateTodo(id, todomodel);
        if (updatedTodo != null) {
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        }}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        String userId = SecurityUtils.getCurrentUserId();
        Optional<TodoModel> todoOptional = todoService.getTodoById(id);
        if (todoOptional.isPresent()) {
            TodoModel existingTodo = todoOptional.get();
            if (!existingTodo.getUserId().equals(userId)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        boolean deleted = todoService.deleteTodo(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

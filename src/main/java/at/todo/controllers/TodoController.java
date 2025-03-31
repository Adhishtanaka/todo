package at.todo.controllers;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/todos")
public class TodoController {


//    @GetMapping()
//    public List<TodoModel> getTodos() {
//
//    }

    @PostMapping()
    public void addTodos(){

    }

    @PutMapping("/{id}/update")
    public void updateTodo() {

    }

    @DeleteMapping("/{id}/delete")
    public void DeleteTodo() {

    }

    @PutMapping("/{id}/isCompleted")
    public void updateTodoStatus() {

    }

}

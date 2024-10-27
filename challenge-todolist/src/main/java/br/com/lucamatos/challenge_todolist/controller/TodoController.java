package br.com.lucamatos.challenge_todolist.controller;

import br.com.lucamatos.challenge_todolist.entity.Todo;
import br.com.lucamatos.challenge_todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    List<Todo> create(@RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @GetMapping
    List<Todo> list() {
        return todoService.list();
    }

    @GetMapping("{id}")
    public Optional<Todo> findById(@PathVariable("id") Long id) {
        Optional<Todo> todo = todoService.findTodoById(id);

        if (todo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found.");
        }

        return todo;
    }


    @PutMapping
    List<Todo> update(@RequestBody Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping("{id}")
    List<Todo> delete(@PathVariable("id") Long id) {

        return todoService.delete(id);
    }

}

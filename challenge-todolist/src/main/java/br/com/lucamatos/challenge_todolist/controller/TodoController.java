package br.com.lucamatos.challenge_todolist.controller;

import br.com.lucamatos.challenge_todolist.entity.Todo;
import br.com.lucamatos.challenge_todolist.exception.TodoNotFoundException;
import br.com.lucamatos.challenge_todolist.service.TodoService;
import jakarta.validation.Valid;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    List<Todo> create(@Valid @RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @GetMapping
    List<Todo> list() {
        return todoService.list();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("{id}")
    public Optional<Todo> findById(@PathVariable("id") Long id) {
        Optional<Todo> todo = todoService.findTodoById(id);

        if (todo.isEmpty()) {
            throw new TodoNotFoundException();
        }

        return todo;
    }

    @PutMapping
    List<Todo> update(@Valid @RequestBody Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping("{id}")
    List<Todo> delete(@PathVariable("id") Long id) {
        Optional<Todo> todo = todoService.findTodoById(id);
        if (todo.isEmpty()) {
            throw new TodoNotFoundException();
        }
        return todoService.delete(id);
    }

}

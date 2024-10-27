package br.com.lucamatos.challenge_todolist.repository;

import br.com.lucamatos.challenge_todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}

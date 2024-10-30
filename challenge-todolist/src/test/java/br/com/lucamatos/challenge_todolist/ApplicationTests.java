package br.com.lucamatos.challenge_todolist;

import br.com.lucamatos.challenge_todolist.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static br.com.lucamatos.challenge_todolist.TestConstants.TODOS;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
	void testCreateTodoSuccess() {
        var todo = new Todo("todo test", "todo test desc", true,1);

        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].name").isEqualTo(todo.getName())
                .jsonPath("$[0].description").isEqualTo(todo.getDescription())
                .jsonPath("$[0].status").isEqualTo(todo.isStatus())
                .jsonPath("$[0].priority").isEqualTo(todo.getPriority());
	}

    @Test
    void testCreateTodoFailure() {
        var todo = new Todo("", "", false,-1);

        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isBadRequest();

    }

    @Test
    void testListTodosSuccess() {
        for (Todo todo : TODOS) {
            webTestClient
                    .post()
                    .uri("/todos")
                    .bodyValue(todo)
                    .exchange()
                    .expectStatus().isCreated();
        }

        webTestClient
                .get()
                .uri("/todos")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(5);
                /*.jsonPath("$[0].name").isEqualTo(TODOS.get(0).getName())
                .jsonPath("$[1].name").isEqualTo(TODOS.get(1).getName())
                .jsonPath("$[2].name").isEqualTo(TODOS.get(2).getName())
                .jsonPath("$[3].name").isEqualTo(TODOS.get(3).getName())
                .jsonPath("$[4].name").isEqualTo(TODOS.get(4).getName());*/
    }

    @Test
    void ListTodosFailure() {

    }


}

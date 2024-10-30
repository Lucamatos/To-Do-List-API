package br.com.lucamatos.challenge_todolist;

import br.com.lucamatos.challenge_todolist.entity.Todo;

import java.util.ArrayList;
import java.util.List;

public class TestConstants {
    public static final List<Todo> TODOS = new ArrayList<>() {
        {
            add(new Todo( "@giulianabezerra", "Curtir", true, 1));
            add(new Todo( "@giulianabezerra", "Comentar", true, 1));
            add(new Todo( "@giulianabezerra", "Compartilhar", true, 1));
            add(new Todo( "@giulianabezerra", "Se Inscrever", true, 1));
            add(new Todo( "@giulianabezerra", "Ativar as Notificações", true, 1));
        }
    };
    public static final Todo TODO = TODOS.get(0);

}

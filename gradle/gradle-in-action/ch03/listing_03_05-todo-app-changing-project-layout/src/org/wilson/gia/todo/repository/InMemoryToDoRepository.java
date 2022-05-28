package org.wilson.gia.todo.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.wilson.gia.todo.model.ToDoItem;

public class InMemoryToDoRepository implements ToDoRepository {
    private AtomicLong currentId = new AtomicLong();
    private ConcurrentMap<Long, ToDoItem> toDos = new ConcurrentHashMap<>();
    
    @Override
    public List<ToDoItem> findAll() {
        final var toDoItems = new ArrayList<ToDoItem>(this.toDos.values());
        Collections.sort(toDoItems);
        return toDoItems;
    }

    @Override
    public ToDoItem findById(Long id) {
        return this.toDos.get(id);
    }

    @Override
    public Long insert(ToDoItem toDoItem) {
        final var id = this.currentId.getAndIncrement();
        toDoItem.setId(id);
        this.toDos.putIfAbsent(id, toDoItem);
        return id;
    }

    @Override
    public void update(ToDoItem toDoItem) {
        this.toDos.replace(toDoItem.getId(), toDoItem);
    }

    @Override
    public void delete(ToDoItem toDoItem) {
        this.toDos.remove(toDoItem.getId());
    }
}

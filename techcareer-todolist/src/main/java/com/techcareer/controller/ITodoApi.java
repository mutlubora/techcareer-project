package com.techcareer.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITodoApi<D> {

    // ALL DELETE
    public ResponseEntity<String> deleteAllTodos();

    ////////////////////////////////////////////////////////////////////////////
    // C R U D
    // CREATE
    public ResponseEntity<?>  createTodo(@Valid D d);

    // LIST
    public ResponseEntity<List<D>>  listTodos(Boolean isDone);

    // FIND BY ID
    public ResponseEntity<?>  findTodoById(Long id);

    // UPDATE
    public ResponseEntity<?>  updateTodo(Long id,@Valid D d);

    // DELETE
    public ResponseEntity<?>  deleteTodoById(Long id);
}

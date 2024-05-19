package com.techcareer.controller;

import com.techcareer.business.dto.TodoDTO;
import com.techcareer.business.service.ITodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// API
@RestController
@RequestMapping("/api/v1/todos")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class TodoApiImpl implements ITodoApi<TodoDTO>{

    private final ITodoService iTodoService;

    // ALL DELETE
    @Override
    @GetMapping(value="/delete/all")
    public ResponseEntity<String> deleteAllTodos() {
        return ResponseEntity.ok(iTodoService.deleteAllTodos());
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    // CREATE
    @Override
    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody @Valid TodoDTO todoDTO) {
        todoDTO.setDone(false);
        return  ResponseEntity.ok(iTodoService.createTodo(todoDTO));
    }

    // LIST
    @Override
    @GetMapping
    public ResponseEntity<List<TodoDTO>> listTodos(
            @RequestParam(value = "isDone", required = false) Boolean isDone) {
        return ResponseEntity.ok(iTodoService.listTodos(isDone));
    }

    // FIND BY ID
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> findTodoById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(iTodoService.findTodoById(id));
    }



    // UPDATE
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable(name = "id") Long id,
                                        @RequestBody @Valid TodoDTO todoDTO) {
        return ResponseEntity.ok(iTodoService.updateTodo(id, todoDTO));
    }


    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable(name = "id") Long id) {
        iTodoService.deleteTodoById(id);
        return ResponseEntity.noContent().build();
    }
}

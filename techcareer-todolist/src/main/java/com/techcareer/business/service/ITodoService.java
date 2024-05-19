package com.techcareer.business.service;


import com.techcareer.business.dto.TodoDTO;
import com.techcareer.data.TodoEntity;

import java.util.List;

public interface ITodoService {
    // Model Mapper
    public TodoDTO entityToDto(TodoEntity todoEntity);

    public TodoEntity dtoToEntity(TodoDTO todoDTO);

    // ALL DELETE
    public String deleteAllTodos();

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    // C R U D
    // CREATE
    public TodoDTO createTodo(TodoDTO todoDTO);

    // LIST
    public List<TodoDTO> listTodos(Boolean isDone);

    // FIND BY ID
    public TodoDTO findTodoById(Long id);


    // UPDATE
    public TodoDTO updateTodo(Long id,TodoDTO todoDTO);

    // DELETE
    public void deleteTodoById(Long id);
}

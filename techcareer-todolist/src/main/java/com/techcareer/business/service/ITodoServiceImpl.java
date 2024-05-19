package com.techcareer.business.service;

import com.techcareer.business.dto.TodoDTO;
import com.techcareer.data.ITodoRepository;
import com.techcareer.data.TodoEntity;
import com.techcareer.exception.TodoNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// SERVICES
@Service
public class ITodoServiceImpl implements ITodoService {
    private final ITodoRepository iTodoRepository;
    private final ModelMapper modelMapper;
    @Override
    public TodoDTO entityToDto(TodoEntity todoEntity) {
        return modelMapper.map(todoEntity, TodoDTO.class);
    }

    @Override
    public TodoEntity dtoToEntity(TodoDTO todoDTO) {
        return modelMapper.map(todoDTO, TodoEntity.class);
    }

    @Override
    public String deleteAllTodos() {
        iTodoRepository.deleteAll();
        return "All todos deleted successfully.";
    }


    //////////////////////////////////////////////////////////////////////////////
    // CREATE
    @Override
    @Transactional
    public TodoDTO createTodo(TodoDTO dto) {
        if (dto != null) {
            TodoEntity todoEntity = dtoToEntity(dto);
            iTodoRepository.save(todoEntity);
            dto.setId(todoEntity.getId());
            dto.setSystemCreatedDate(todoEntity.getSystemCreatedDate());
            return dto;
        } else {
            throw new NullPointerException("TodoDTO can not null.");
        }

    }

    // LIST
    @Override
    public List<TodoDTO> listTodos(Boolean isDone) {
        Iterable<TodoEntity> todoIterable;
        if (isDone == null) {
            todoIterable = iTodoRepository.findAll();
        } else {
            todoIterable = iTodoRepository.findTodoEntityByDone(isDone);
        }
        List<TodoDTO> dtoList = new ArrayList<>();

       for (TodoEntity e : todoIterable) {
           TodoDTO todoDTO = entityToDto(e);
           dtoList.add(todoDTO);
       }

        return dtoList;
    }

    // FIND
    @Override
    public TodoDTO findTodoById(Long id) {
        TodoEntity findById = iTodoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("No todo found with the given id: " + id));
        return entityToDto(findById);
    }

    // UPDATE
    @Override
    @Transactional
    public TodoDTO updateTodo(Long id, TodoDTO dto) {
        TodoDTO todoDTO = findTodoById(id);
        TodoEntity todoEntity = dtoToEntity(todoDTO);
        todoEntity.setContent(dto.getContent());
        todoEntity.setDone(dto.isDone());
        iTodoRepository.save(todoEntity);
        return entityToDto(todoEntity);
    }

    // DELETE
    @Override
    @Transactional
    public void deleteTodoById(Long id) {
        iTodoRepository.deleteById(id);
    }
}

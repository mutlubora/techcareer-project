package com.techcareer.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITodoRepository extends JpaRepository<TodoEntity, Long> {

    Iterable<TodoEntity> findTodoEntityByDone(boolean done);
}

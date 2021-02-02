package com.unicorntech.todolist.repository;

import com.unicorntech.todolist.model.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
}

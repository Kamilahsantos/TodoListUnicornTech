package com.unicorntech.todolist.controller;


import com.unicorntech.todolist.exception.ResourceNotFoundException;
import com.unicorntech.todolist.model.ToDoList;
import com.unicorntech.todolist.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ToDoListController {


    @Autowired
    private ToDoListRepository toDoListRepository;


    @GetMapping("/task")
    public List<ToDoList> getAllTasks() {
        return toDoListRepository.findAll();
    }

    @GetMapping("/task/{id}")
    public ResponseEntity< ToDoList > getTaskById(@PathVariable(value = "id") Long taskId)
            throws ResourceNotFoundException {
        ToDoList toDoList = toDoListRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found :: " + taskId));
        return ResponseEntity.ok().body(toDoList);
    }

    @PostMapping("/task")
    public ToDoList createTask(@Valid @RequestBody ToDoList toDoList) {
        return toDoListRepository.save(toDoList);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity < ToDoList > updateTask(@PathVariable(value = "id") Long taskId,
                                              @Valid @RequestBody ToDoList taskDetails) throws ResourceNotFoundException {
        ToDoList toDoList = toDoListRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

        toDoList.setTitle(taskDetails.getTitle());
        toDoList.setStatus(taskDetails.getStatus());
            final ToDoList updateTask = toDoListRepository.save(toDoList);
        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/task/{id}")
    public Map< String, Boolean > deleteTask(@PathVariable(value = "id") Long taskId)
            throws ResourceNotFoundException {
        ToDoList toDoList = toDoListRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

        toDoListRepository.delete(toDoList);
        Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

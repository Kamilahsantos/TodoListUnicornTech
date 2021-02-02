package com.unicorntech.todolist.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todo_list")
public class ToDoList {


    private Long id;
    private String title;
    private String status;



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title", length = 255, nullable = true)
    @Size(max = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "status", length = 255, nullable = true)
    @Size(max = 255)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ToDoList(Long id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public ToDoList() {
    }


    @Override
    public String toString() {
        return "ToDoList{" +
                "id=" + id +
                ", title=" + title +
                ", status=" + status +
                '}';
    }
}

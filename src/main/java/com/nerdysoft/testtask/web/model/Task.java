package com.nerdysoft.testtask.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String capture;


    @OneToMany(mappedBy = "task")
    private Set<UserTask> userTasks = new HashSet<UserTask>();

    public Task( @NotBlank @Size(max = 40) String capture) {
        this.capture = capture;
    }
}
package com.nerdysoft.testtask.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_tasks")
public class UserTask {
    @EmbeddedId
    private UserTaskId id;

    @ManyToOne
    @MapsId("user_id")
    private User user;

    @ManyToOne
    @MapsId("task_id")
    private Task task;

    @Column(name = "shared_by")
    private String sharedBy;

}

package com.nerdysoft.testtask.web.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
@Embeddable
public class UserTaskId implements Serializable {

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "task_id")
    private Long task_id;

    public UserTaskId (){};

    public UserTaskId(Long user_id, Long task_id) {
        this.user_id = user_id;
        this.task_id = task_id;
    }
}
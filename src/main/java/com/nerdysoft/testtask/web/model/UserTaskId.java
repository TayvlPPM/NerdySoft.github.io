package com.nerdysoft.testtask.web.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


import javax.persistence.Column;
import javax.persistence.Embeddable;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        UserTaskId that = (UserTaskId) o;
        return Objects.equals(user_id, that.user_id) &&
                Objects.equals(task_id, that.task_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, task_id);
    }
}
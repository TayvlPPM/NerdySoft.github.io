package com.nerdysoft.testtask.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TaskSummary {

    @NotBlank
    private Long id;

    private String caption;

    private String sharedBy;

    public TaskSummary(@NotBlank Long id,
                    String caption,
                       String sharedBy) {
        this.id = id;
        this.caption = caption;
        this.sharedBy = sharedBy;
    }
}
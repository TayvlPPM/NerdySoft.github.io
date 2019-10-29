package com.nerdysoft.testtask.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TaskSummary {
    private Long id;
    private String caption;
    private String sharedBy;
}
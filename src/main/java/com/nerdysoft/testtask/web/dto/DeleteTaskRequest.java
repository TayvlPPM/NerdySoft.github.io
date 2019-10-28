package com.nerdysoft.testtask.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DeleteTaskRequest {

    @NotBlank
    private Long id;

}

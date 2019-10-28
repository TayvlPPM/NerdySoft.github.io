package com.nerdysoft.testtask.web.dto;

import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class UpdTaskRequest {
    @NotNull
    private Long id;

    @NotBlank
    private String new_caption;

}
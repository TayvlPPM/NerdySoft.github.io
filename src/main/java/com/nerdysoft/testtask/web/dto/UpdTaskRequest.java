package com.nerdysoft.testtask.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdTaskRequest {
    @NotBlank
    private Long id;

    @NotBlank
    private String new_caption;

}
package com.nerdysoft.testtask.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ShareTaskRequest {

    @NotBlank
    @Email
    private String shareToEmail;

}

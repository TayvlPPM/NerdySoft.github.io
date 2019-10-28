package com.nerdysoft.testtask.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

    private String sharedBy;

    @NotBlank
    private String capture;


    public Task(@NotEmpty @Size(max = 40) String sharedBy, @NotBlank @Size(max = 40) String capture) {
        this.sharedBy = sharedBy;
        this.capture = capture;
    }
}
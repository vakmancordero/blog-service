package com.curso.blog.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class BlogUpdateRequest {

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String name;

    private String description;

}

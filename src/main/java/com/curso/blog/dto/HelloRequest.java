package com.curso.blog.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class HelloRequest {

    @NotEmpty
    private String name;

    @Min(16)
    private Integer age;

}

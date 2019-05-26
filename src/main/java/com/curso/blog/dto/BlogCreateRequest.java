package com.curso.blog.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class BlogCreateRequest {

    /*
    @Min(value = 5)
    @Max(value = 2500)
    @Size(min = 5, max = 2500)
    @NotNull
    private Long maxPostsSize;
    */

    //@Pattern(regexp = "(//d)")
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String name;

    private String description;

    @Email(message = "El dato dado no corresponde a un correo electrónico")
    @NotEmpty(message = "El blog tiene que tener un dueño")
    private String createdBy;

}

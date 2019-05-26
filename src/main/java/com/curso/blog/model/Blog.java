package com.curso.blog.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

//@Getter
//@Setter
//@ToString//(of = {"id", "name"}, exclude = {"name"})
//@EqualsAndHashCode
//equals
//hashCode
//toString
@Data
@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, length = 100, nullable = false)
    private String name;

    @Column(name = "description"/*, length = 255*/)
    private String description;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
    private List<Post> posts;

}

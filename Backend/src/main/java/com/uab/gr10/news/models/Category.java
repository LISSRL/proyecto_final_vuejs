package com.uab.gr10.news.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import lombok.*;
import org.aspectj.weaver.ast.Not;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String description ;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Notice> notices;



}

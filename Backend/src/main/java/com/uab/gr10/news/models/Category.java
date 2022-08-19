package com.uab.gr10.news.models;

import lombok.*;
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


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}

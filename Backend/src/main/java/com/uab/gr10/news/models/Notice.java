package com.uab.gr10.news.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @NotBlank
    private String title;

    @NotBlank
    private String subtitle ;

    @NotBlank
    private String content ;

    @NotBlank
    private String author ;


    @NotBlank
    private String image ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}

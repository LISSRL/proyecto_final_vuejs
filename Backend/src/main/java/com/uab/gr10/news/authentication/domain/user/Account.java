package com.uab.gr10.news.authentication.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Account {

    private String username;
    private String token;
    private String message;
}

package com.uab.gr10.news.authentication.infraestructure.exceptions;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends RuntimeException
{
    private String errorMessage;
    private int statusCode;
    private List<Errors> details;
}

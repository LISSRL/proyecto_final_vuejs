package com.uab.gr10.news.authentication.infraestructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCredentialsDTO
{
    @ApiModelProperty(
        example = "grupo10-user"
    )
    @JsonProperty("usernameOrEmail")
    private final String usernameOrEmail;

    @ApiModelProperty(
        example = "password"
    )
    @JsonProperty("password")
    private final String password;
}

package com.uab.gr10.news.authentication.infraestructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.uab.gr10.news.authentication.domain.user.User;
import com.uab.gr10.news.authentication.infraestructure.utils.ValidatorUtils;
import com.uab.gr10.news.authentication.infraestructure.utils.constants.ErrorsUtil;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO
{
    @ApiModelProperty(
        example = "-5319461058060181693"
    )
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("id")
    private long id;

    @ApiModelProperty(
        example = "juan.perez"
    )
    @JsonProperty("username")
    private String username;

    @ApiModelProperty(
        example = "Juan.Perez@gmail.com"
    )
    @JsonProperty("email")
    private String email;

    @ApiModelProperty(
        example = "JuanPerez123!"
    )
    @JsonProperty("password")
    @Pattern(
        regexp = ValidatorUtils.REGEX_PASSWORD,
        message = ErrorsUtil.ERROR_PASSWORD_DONT_CONTAIN_EXPECTED_VALUES
    )
    private String password;

    public UserDTO(User user)
    {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}


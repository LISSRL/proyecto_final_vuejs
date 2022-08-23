package com.uab.gr10.news.authentication.infraestructure.dto;

import com.uab.gr10.news.authentication.infraestructure.utils.ValidatorUtils;
import com.uab.gr10.news.authentication.infraestructure.utils.constants.ErrorsUtil;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDTO
{
    @ApiModelProperty(
        example = "OldPassword1+"
    )
    @Pattern(
        regexp = ValidatorUtils.REGEX_PASSWORD,
        message = ErrorsUtil.ERROR_PASSWORD_DONT_CONTAIN_EXPECTED_VALUES
    )
    private String oldPassword;

    @ApiModelProperty(
        example = "NewPassword1+"
    )
    @Pattern(
        regexp = ValidatorUtils.REGEX_PASSWORD,
        message = ErrorsUtil.ERROR_PASSWORD_DONT_CONTAIN_EXPECTED_VALUES
    )
    private String newPassword;
}

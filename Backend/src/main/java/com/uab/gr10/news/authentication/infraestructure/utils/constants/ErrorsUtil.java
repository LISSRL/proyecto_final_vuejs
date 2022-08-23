package com.uab.gr10.news.authentication.infraestructure.utils.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorsUtil
{
    public static final String ERROR_PASSWORD_CHANGE_INVALID_PARAMETERS = "Password change with "
        + "invalid parameters";
    public static final String ERROR_MESSAGE_ENTITY_NOT_FOUND = "Entity not found";
    public static final String ERROR_USER_NOT_FOUND = "User with %s: %s not found";

    public static final String ERROR_BAD_OLD_PASSWORD = "Invalid old password";
    public static final String ERROR_NEW_PASSWORD_IS_THE_SAME = "New password is the same than the "
        + "old password";
    public static final String ERROR_MESSAGE_ENTITY_DUPLICATED = "Entity already registered";
    public static final String ERROR_MESSAGE_ENTITY_INVALID = "Entity is invalid";
    public static final String ERROR_DESCRIPTION_USER_DUPLICATED = "User with %s: "
        + "%s is already registered";
    public static final String ERROR_PASSWORD_DONT_CONTAIN_EXPECTED_VALUES = "Password doesn't "
        + "contain expected values";
    public static final String ERROR_MESSAGE_AUTHENTICATION_TOKEN = "Unauthorized token";
    public static final String ERROR_DESCRIPTION_USER_NOT_FOUND = "User with %s: %s "
        + "was not found";

    public static String getDescription(String message, String field, String value)
    {
        return String.format(message, field, value);
    }
}
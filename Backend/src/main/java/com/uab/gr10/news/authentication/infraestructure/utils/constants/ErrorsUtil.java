package com.uab.gr10.news.authentication.infraestructure.utils.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorsUtil
{
    public static final String ERROR_MESSAGE_ENTITY_DUPLICATED = "Entity already registered";
    public static final String ERROR_MESSAGE_ENTITY_INVALID = "Entity is invalid";
    public static final String ERROR_DESCRIPTION_USER_DUPLICATED = "User with %s: "
        + "%s is already registered";
    public static final String ERROR_PASSWORD_DONT_CONTAIN_EXPECTED_VALUES = "Password doesn't "
        + "contain expected values";
    public static final String ERROR_MESSAGE_AUTHENTICATION_TOKEN = "Unauthorized token";

    public static String getDescription(String message, String field, String value)
    {
        return String.format(message, field, value);
    }
}
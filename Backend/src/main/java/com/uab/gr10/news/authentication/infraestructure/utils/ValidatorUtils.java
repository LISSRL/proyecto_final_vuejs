package com.uab.gr10.news.authentication.infraestructure.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtils
{
    /*
     * The verification pattern was obtained from the following link
     * "https://emailregex.com/"
     */
    public static final String REGEX_EMAIL_VALID =
        "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+"
            + "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
            + "|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01"
            + "-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")"
            + "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+"
            + "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
            + "|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
            + "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
            + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b"
            + "\\x0c\\x0e-\\x7f])+)\\])";

    public static final String REGEX_PASSWORD =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern PATTERN_CRITERIA_EMAIL = Pattern.compile(REGEX_EMAIL_VALID);
    private static final Pattern PATTERN_CRITERIA_PASSWORD = Pattern.compile(REGEX_PASSWORD);

    public static boolean isEmailValid(String email)
    {
        Matcher matcher = PATTERN_CRITERIA_EMAIL.matcher(email);
        return matcher.matches();
    }

    public static boolean isPasswordValid(String password)
    {
        Matcher matcher = PATTERN_CRITERIA_PASSWORD.matcher(password);
        return matcher.matches();
    }
}

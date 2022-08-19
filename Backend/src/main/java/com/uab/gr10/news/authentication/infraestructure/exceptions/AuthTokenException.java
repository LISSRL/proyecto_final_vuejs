package com.uab.gr10.news.authentication.infraestructure.exceptions;

import com.uab.gr10.news.authentication.infraestructure.utils.constants.ErrorsUtil;
import java.util.Arrays;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthTokenException extends BaseException
{
    private static final int httpStatus = HttpStatus.SC_FORBIDDEN;
    private static final Logger logger = LogManager.getLogger(AuthTokenException.class);

    public AuthTokenException(String field, String errorDescription)
    {
        super(
            ErrorsUtil.ERROR_MESSAGE_AUTHENTICATION_TOKEN,
            httpStatus,
            Arrays.asList(new Errors(field, errorDescription)));
        logger.error(ErrorsUtil.ERROR_MESSAGE_AUTHENTICATION_TOKEN);
    }
}

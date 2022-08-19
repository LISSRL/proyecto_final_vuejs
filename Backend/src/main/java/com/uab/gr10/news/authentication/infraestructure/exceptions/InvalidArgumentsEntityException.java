package com.uab.gr10.news.authentication.infraestructure.exceptions;

import com.uab.gr10.news.authentication.infraestructure.utils.constants.ErrorsUtil;
import java.util.List;
import lombok.Getter;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
public class InvalidArgumentsEntityException extends BaseException
{
    private static final int httpStatus = HttpStatus.SC_UNPROCESSABLE_ENTITY;
    private static final Logger logger =
        LogManager.getLogger(InvalidArgumentsEntityException.class);

    public InvalidArgumentsEntityException(List<Errors> errorsList)
    {
        super(ErrorsUtil.ERROR_MESSAGE_ENTITY_INVALID, httpStatus, errorsList);
        logger.error(ErrorsUtil.ERROR_MESSAGE_ENTITY_INVALID);
    }
}

package com.uab.gr10.news.authentication.infraestructure.exceptions;

import com.uab.gr10.news.authentication.infraestructure.utils.constants.ErrorsUtil;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
public class DuplicatedEntityException extends BaseException
{

    private static final int httpStatus = HttpStatus.SC_CONFLICT;

    private static final Logger logger = LogManager.getLogger(DuplicatedEntityException.class);

    public DuplicatedEntityException(List<Errors> errorsList)
    {
        super(ErrorsUtil.ERROR_MESSAGE_ENTITY_DUPLICATED, httpStatus, errorsList);
        logger.error(ErrorsUtil.ERROR_MESSAGE_ENTITY_DUPLICATED);
    }

    public DuplicatedEntityException(String fieldId, String errorDescription)
    {
        super(
            ErrorsUtil.ERROR_MESSAGE_ENTITY_DUPLICATED,
            httpStatus,
            Arrays.asList(new Errors(fieldId, errorDescription)));
        logger.error(ErrorsUtil.ERROR_MESSAGE_ENTITY_DUPLICATED);
    }
}

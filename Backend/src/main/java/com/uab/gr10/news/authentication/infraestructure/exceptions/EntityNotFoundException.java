package com.uab.gr10.news.authentication.infraestructure.exceptions;

import com.uab.gr10.news.authentication.infraestructure.utils.constants.ErrorsUtil;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.apache.http.HttpStatus;

@Getter
public class EntityNotFoundException extends BaseException
{

    private static final int httpStatus = HttpStatus.SC_NOT_FOUND;


    public EntityNotFoundException(List<Errors> errorsList)
    {
        super(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND, httpStatus, errorsList);
    }

    public EntityNotFoundException(String fieldId, String errorDescription)
    {
        super(ErrorsUtil.ERROR_MESSAGE_ENTITY_NOT_FOUND,
            httpStatus,
            Arrays.asList(new Errors(fieldId, errorDescription)));
    }
}

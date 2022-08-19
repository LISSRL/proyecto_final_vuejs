package com.uab.gr10.news.authentication.infraestructure.controller.user;

import com.uab.gr10.news.authentication.infraestructure.dto.UserDTO;
import com.uab.gr10.news.authentication.infraestructure.usecase.user.manipulation.UserHandleUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController
{
    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final UserHandleUseCase userHandleUseCase;

    @Autowired
    public UserController(final UserHandleUseCase userHandleUseCase)
    {
        this.userHandleUseCase = userHandleUseCase;
    }

    @ApiOperation(
        value = "Sign up a new user",
        response = UserDTO.class
    )
    @ApiResponses( {
        @ApiResponse(
            code = 201, message = "Created, when resource was created successfully and returns the"
            + " object of the created resource in the body"
        ),
        @ApiResponse(
            code = 400, message = "Descriptive bad request message according the rules violated"
        ),
        @ApiResponse(
            code = 409, message = "Duplicate name error, when there is already a registered"
        ),
        @ApiResponse(
            code = 422, message = "Invalid input error, when the payload contains a required"
            + " attribute empty"
        )
    })
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> create(@Valid @RequestBody final UserDTO userDTO)
    {
        logger.info("User signup ", userDTO);
        return new ResponseEntity<>(userHandleUseCase.create(userDTO), HttpStatus.CREATED);
    }
}

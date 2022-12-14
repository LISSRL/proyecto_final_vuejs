package com.uab.gr10.news.authentication.infraestructure.controller.user;

import com.uab.gr10.news.authentication.infraestructure.dto.ChangePasswordDTO;
import com.uab.gr10.news.authentication.infraestructure.dto.UserDTO;
import com.uab.gr10.news.authentication.infraestructure.usecase.user.fetch.UserFetchUseCase;
import com.uab.gr10.news.authentication.infraestructure.usecase.user.manipulation.UserHandleUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController
{
    private static final String ID = "id";
    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final UserHandleUseCase userHandleUseCase;
    private final UserFetchUseCase userFetchUseCase;

    @Autowired
    public UserController(final UserHandleUseCase userHandleUseCase, final UserFetchUseCase userFetchUseCase)
    {
        this.userHandleUseCase = userHandleUseCase;
        this.userFetchUseCase = userFetchUseCase;
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

    @GetMapping
    public ResponseEntity<List<UserDTO>> FetchAllUsers()
    {
        return new ResponseEntity<>(userFetchUseCase.fetchAll(), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Fetch user by id",
        response = UserDTO.class
    )
    @ApiResponses( {
        @ApiResponse(
            code = 200, message = "Fetch user successfully"
        ),
        @ApiResponse(
            code = 400, message = "Descriptive bad request message according the rules violated"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> fetchUserById(@PathVariable(ID) long id)
    {
        logger.info("Fetch user by id ", id);
        return ResponseEntity.ok(userFetchUseCase.fetchById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(
        @PathVariable(ID) final long id,
        @Valid @RequestBody UserDTO userDTO)
    {
        logger.info("Update user by id ", id);
        return ResponseEntity.ok(userHandleUseCase.update(id, userDTO));
    }

    @ApiOperation(
        value = "Delete user by id using soft delete"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 204, message = "Delete user successfully"
        ),
        @ApiResponse(
            code = 404, message = "User not found"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable(ID) long id, @RequestBody String comments
    )
    {
        userHandleUseCase.delete(id, comments);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
        value = "update user password"
    )
    @ApiResponses( {
        @ApiResponse(
            code = 204, message = "Update user password successfully"
        ),
        @ApiResponse(
            code = 404, message = "User not found"
        )
    })
    @PatchMapping("/{id}/update-password")
    public ResponseEntity<Void> updatePassword(
        @PathVariable(ID) long id, @Valid @RequestBody ChangePasswordDTO changePasswordDTO)
    {
        userHandleUseCase.updatePassword(id, changePasswordDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

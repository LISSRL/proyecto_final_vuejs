package com.uab.gr10.news.authentication.infraestructure.usecase.user.manipulation;

import com.uab.gr10.news.authentication.domain.user.User;
import com.uab.gr10.news.authentication.domain.user.repository.UserRepository;
import com.uab.gr10.news.authentication.infraestructure.dto.ChangePasswordDTO;
import com.uab.gr10.news.authentication.infraestructure.dto.UserDTO;
import com.uab.gr10.news.authentication.infraestructure.exceptions.EntityNotFoundException;
import com.uab.gr10.news.authentication.infraestructure.exceptions.PasswordException;
import com.uab.gr10.news.authentication.infraestructure.factory.UserFactory;
import com.uab.gr10.news.authentication.infraestructure.utils.constants.ErrorsUtil;
import com.uab.gr10.news.authentication.infraestructure.utils.constants.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserHandleUseCase
{
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public UserHandleUseCase(
        final UserRepository userRepository,
        final UserFactory userFactory, final PasswordEncoder bcryptEncoder
    )
    {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.bcryptEncoder = bcryptEncoder;
    }

    public UserDTO create(final UserDTO userDTO)
    {
        User user = userFactory.create(userDTO);
        return new UserDTO(userRepository.save(user));
    }
    public UserDTO update(final long userId, UserDTO userDTO)
    {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(ErrorsUtil.ERROR_DESCRIPTION_USER_NOT_FOUND, Field.ID,
                    String.valueOf(userId))
            ));
        String encodedPassword = user.getPassword();
        if (userDTO.getPassword() != null
            && !bcryptEncoder.matches(userDTO.getPassword(), encodedPassword))
        {
            encodedPassword = bcryptEncoder.encode(userDTO.getPassword());
        }
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        User updatedUser = userRepository.save(user);
        return new UserDTO(updatedUser);
    }

    public void delete(long id, String comments)
    {
        User user =
            userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(ErrorsUtil.ERROR_USER_NOT_FOUND, Field.ID,
                    String.valueOf(id))));
        user.setActive(false);
        userRepository.save(user);
    }

    public void updatePassword(long id, ChangePasswordDTO changePasswordDTO)
    {
        User user = userRepository.findById(id).orElseThrow((() -> new EntityNotFoundException(
            Field.ID,
            ErrorsUtil.getDescription(ErrorsUtil.ERROR_USER_NOT_FOUND, Field.ID,
                String.valueOf(id)))));
        verifyPassword(user.getPassword(), changePasswordDTO);
        user.setPassword(bcryptEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    private void verifyPassword(String currentPassword, ChangePasswordDTO changePasswordDTO)
    {
        if (!bcryptEncoder.matches(changePasswordDTO.getOldPassword(), currentPassword))
        {
            throw new PasswordException("oldPassword", ErrorsUtil.ERROR_BAD_OLD_PASSWORD);
        }

        if (changePasswordDTO.getOldPassword().equalsIgnoreCase(changePasswordDTO.getNewPassword()))
        {
            throw new PasswordException(
                "newPassword",
                ErrorsUtil.ERROR_NEW_PASSWORD_IS_THE_SAME);
        }
    }
}

package com.uab.gr10.news.authentication.infraestructure.factory;

import com.uab.gr10.news.authentication.domain.user.User;
import com.uab.gr10.news.authentication.domain.user.repository.UserRepository;
import com.uab.gr10.news.authentication.infraestructure.dto.UserDTO;
import com.uab.gr10.news.authentication.infraestructure.exceptions.DuplicatedEntityException;
import com.uab.gr10.news.authentication.infraestructure.utils.constants.ErrorsUtil;
import com.uab.gr10.news.authentication.infraestructure.utils.constants.Field;
import com.uab.gr10.news.authentication.infraestructure.utils.factory.Factory;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserFactory implements Factory<User, UserDTO>
{
    private final UserRepository userRepository;

    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public UserFactory(final UserRepository userRepository, final PasswordEncoder bcryptEncoder)
    {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }


    @Override
    public User create(final UserDTO userDTO)
    {
        final long userId = UUID.randomUUID().getLeastSignificantBits();

        validateIfEmailIsUnique(userDTO.getEmail());
        validateIfUsernameIsUnique(userDTO.getUsername());

        return new User(
            userId,
            userDTO.getUsername(),
            userDTO.getEmail(),
            bcryptEncoder.encode(userDTO.getPassword())
        );
    }

    private void validateIfEmailIsUnique(String email)
    {
        if (userRepository.existsByEmailIgnoreCase(email))
        {
            throw new DuplicatedEntityException(
                Field.EMAIL,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_USER_DUPLICATED,
                    Field.EMAIL, email));
        }
    }

    private void validateIfUsernameIsUnique(String username)
    {
        if (userRepository.existsByUsername(username))
        {
            throw new DuplicatedEntityException(
                Field.USERNAME,
                ErrorsUtil.getDescription(
                    ErrorsUtil.ERROR_DESCRIPTION_USER_DUPLICATED,
                    Field.USERNAME, username));
        }
    }
}


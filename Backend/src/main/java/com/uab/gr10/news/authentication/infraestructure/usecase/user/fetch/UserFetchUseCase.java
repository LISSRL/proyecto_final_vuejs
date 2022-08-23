package com.uab.gr10.news.authentication.infraestructure.usecase.user.fetch;

import com.uab.gr10.news.authentication.domain.user.User;
import com.uab.gr10.news.authentication.domain.user.repository.UserRepository;
import com.uab.gr10.news.authentication.infraestructure.dto.UserDTO;
import com.uab.gr10.news.authentication.infraestructure.exceptions.AuthTokenException;
import com.uab.gr10.news.authentication.infraestructure.exceptions.EntityNotFoundException;
import com.uab.gr10.news.authentication.infraestructure.utils.constants.ErrorsUtil;
import com.uab.gr10.news.authentication.infraestructure.utils.constants.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserFetchUseCase implements UserDetailsService
{
    private final UserRepository userRepository;

    @Autowired
    public UserFetchUseCase(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException
    {
        Optional<User> userByUsername = userRepository.findByUsername(usernameOrEmail);
        Optional<User> userByEmail = Optional.empty();
        if (userByUsername.isEmpty())
        {
            userByEmail = userRepository.findByEmail(usernameOrEmail);
            if (!userByEmail.isPresent())
            {
                throw new AuthTokenException(
                    "User not found with the name or email :  ",
                    usernameOrEmail);
            }
        }
        return userByUsername.isPresent() ? userByUsername.get() : userByEmail.get();
    }

    public List<UserDTO> fetchAll()
    {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO fetchById(long id)
    {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                Field.ID,
                ErrorsUtil.getDescription(ErrorsUtil.ERROR_DESCRIPTION_USER_NOT_FOUND, Field.ID,
                    String.valueOf(id))));
        return new UserDTO(user);
    }

}

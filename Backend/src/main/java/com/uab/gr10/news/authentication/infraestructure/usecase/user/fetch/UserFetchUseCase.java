package com.uab.gr10.news.authentication.infraestructure.usecase.user.fetch;

import com.uab.gr10.news.authentication.domain.user.User;
import com.uab.gr10.news.authentication.domain.user.repository.UserRepository;
import com.uab.gr10.news.authentication.infraestructure.exceptions.AuthTokenException;
import java.util.Optional;
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

}

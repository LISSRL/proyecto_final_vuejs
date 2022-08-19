package com.uab.gr10.news.authentication.infraestructure.usecase.user.manipulation;

import com.uab.gr10.news.authentication.domain.user.User;
import com.uab.gr10.news.authentication.domain.user.repository.UserRepository;
import com.uab.gr10.news.authentication.infraestructure.dto.UserDTO;
import com.uab.gr10.news.authentication.infraestructure.factory.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHandleUseCase
{
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    @Autowired
    public UserHandleUseCase(
        final UserRepository userRepository,
        final UserFactory userFactory
    )
    {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public UserDTO create(final UserDTO userDTO)
    {
        User user = userFactory.create(userDTO);
        return new UserDTO(userRepository.save(user));
    }
}

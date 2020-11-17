package com.damar.spring.service;

import com.damar.spring.exception.UserNotFoundException;
import com.damar.spring.model.User;
import com.damar.spring.repository.UserRepository;
import com.damar.spring.repository.entity.UserEntity;
import com.damar.spring.service.security.JwtService;
import lombok.AllArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceDefault implements UserService {

    private UserRepository userRepository;

    private JwtService jwtService;

    private DozerBeanMapper mapper;

    @Override
    public User signUp(User user) {
        UserEntity toSave = userRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map(toSave, User.class);
    }

    @Override
    public String login(String username) throws UserNotFoundException {
        UserEntity user = Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(UserNotFoundException::new);
        return jwtService.createJWT(user.getUsername());
    }
}

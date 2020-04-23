package com.damar.aopdata.service;


import com.damar.aopdata.exception.UserNotFoundException;
import com.damar.aopdata.repository.entity.UserEntity;
import com.damar.aopdata.model.User;
import com.damar.aopdata.repository.UserRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private DozerBeanMapper mapper;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(DozerBeanMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public User getById(Long id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (Objects.isNull(userEntity))
            throw new UserNotFoundException();
        return mapper.map(userEntity, User.class);
    }

}

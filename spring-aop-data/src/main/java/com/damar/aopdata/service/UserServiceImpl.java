package com.damar.aopdata.service;


import com.damar.aopdata.exception.UserNotFoundException;
import com.damar.aopdata.repository.entity.UserEntity;
import com.damar.aopdata.model.User;
import com.damar.aopdata.repository.UserRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public List<User> getAll(Boolean isActive) {
        List<UserEntity> userEntityList;

        if (BooleanUtils.isTrue(isActive))
            userEntityList = userRepository.findByActiveTrue();
        else if (BooleanUtils.isFalse(isActive))
            userEntityList = userRepository.findByActiveFalse();
        else
            userEntityList = userRepository.findAll();

        List<User> userList = userEntityList.stream().map(entity -> mapper.map(entity, User.class))
                .collect(Collectors.toList());
        return userList;
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        if (BooleanUtils.isFalse(checkIfUserExists(user.getUserId())))
            throw new UserNotFoundException();
        UserEntity toUpdate = userRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map(toUpdate, User.class);
    }

    @Override
    public User saveUser(User user) {
        UserEntity newEntity = userRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map(newEntity, User.class);
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        UserEntity toDelete = userRepository.findById(id).orElse(null);
        if (Objects.isNull(toDelete))
            throw new UserNotFoundException();
        userRepository.delete(toDelete);
    }

    @Override
    public List<User> getAllByMinMaxAge(Integer minAge, Integer maxAge, Boolean equal) {
        List<UserEntity> userEntityList;

        if (BooleanUtils.isTrue(equal))
            userEntityList = userRepository.findByContact_AgeGreaterThanEqualAndContact_AgeLessThanEqual(minAge, maxAge);
        else
            userEntityList = userRepository.findByContact_AgeGreaterThanAndContact_AgeLessThan(minAge, maxAge);

        List<User> userList = userEntityList.stream().map(entity -> mapper.map(entity, User.class))
                .collect(Collectors.toList());

        return userList;
    }

    private boolean checkIfUserExists(Long userId) {
        return userRepository.existsById(userId);
    }

}

package com.damar.aopdata.service;


import com.damar.aopdata.exception.UserNotFoundException;
import com.damar.aopdata.model.User;
import com.damar.aopdata.repository.UserRepository;
import com.damar.aopdata.repository.entity.UserEntity;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
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
    public List<User> getAll(Integer age, String name, Boolean isActive) {
        List<UserEntity> userEntityList;

        if (Objects.nonNull(isActive)) {
            if (Objects.nonNull(age)) {
                if (StringUtils.isNotBlank(name))
                    userEntityList = userRepository.findByActiveAndContact_AgeAndNameContainingIgnoreCase(isActive, age, name);
                else
                    userEntityList = userRepository.findByActiveAndContact_Age(isActive, age);
            } else {
                if (StringUtils.isNotBlank(name)) {
                    userEntityList = userRepository.findByActiveAndNameContainingIgnoreCase(isActive, name);
                } else {
                    if (BooleanUtils.isTrue(isActive))
                        userEntityList = userRepository.findByActiveTrue();
                    else
                        userEntityList = userRepository.findByActiveFalse();
                }
            }
        } else {
            if (Objects.nonNull(age)) {
                if (StringUtils.isNotBlank(name))
                    userEntityList = userRepository.findByContact_AgeAndNameContainingIgnoreCase(age, name);
                else
                    userEntityList = userRepository.findByContact_Age(age);
            } else {
                if (StringUtils.isNotBlank(name))
                    userEntityList = userRepository.findByNameContainingIgnoreCase(name);
                else
                    userEntityList = userRepository.findAll();
            }
        }

        return userEntityList.stream().map(entity -> mapper.map(entity, User.class))
                .collect(Collectors.toList());
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

        return userEntityList.stream().map(entity -> mapper.map(entity, User.class))
                .collect(Collectors.toList());
    }

    private boolean checkIfUserExists(Long userId) {
        return userRepository.existsById(userId);
    }

}

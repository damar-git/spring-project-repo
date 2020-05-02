package com.damar.aopdata.repository;

import com.damar.aopdata.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity getByUsername(String username);

    List<UserEntity> getByActive(Boolean active);

    List<UserEntity> getByContact_AgeGreaterThan(Integer age);

    List<UserEntity> getByContact_AgeLessThan(Integer age);
}

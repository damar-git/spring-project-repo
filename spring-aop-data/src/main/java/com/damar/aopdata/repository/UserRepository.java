package com.damar.aopdata.repository;

import com.damar.aopdata.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    List<UserEntity> findByActiveTrue();

    List<UserEntity> findByActiveFalse();

    List<UserEntity> findByContact_AgeGreaterThanAndContact_AgeLessThan(Integer ageMin, Integer ageMax);

    List<UserEntity> findByContact_AgeGreaterThanEqualAndContact_AgeLessThanEqual(Integer ageMin, Integer ageMax);
}

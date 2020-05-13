package com.damar.aopdata.repository;

import com.damar.aopdata.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByActiveAndContact_Age(Boolean active, Integer age);

    List<UserEntity> findByActiveAndContact_AgeAndNameContainingIgnoreCase(Boolean active, Integer age, String name);

    List<UserEntity> findByActiveAndNameContainingIgnoreCase(Boolean active, String name);

    List<UserEntity> findByContact_Age(Integer age);

    List<UserEntity> findByNameContainingIgnoreCase(String name);

    List<UserEntity> findByContact_AgeAndNameContainingIgnoreCase(Integer age, String name);

    List<UserEntity> findByActiveTrue();

    List<UserEntity> findByActiveFalse();

    List<UserEntity> findByContact_AgeGreaterThanAndContact_AgeLessThan(Integer ageMin, Integer ageMax);

    List<UserEntity> findByContact_AgeGreaterThanEqualAndContact_AgeLessThanEqual(Integer ageMin, Integer ageMax);
}

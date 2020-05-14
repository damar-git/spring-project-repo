package com.damar.aopdata.repository;

import com.damar.aopdata.repository.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    List<PlayerEntity> findByActiveAndPlayerDetail_Age(Boolean active, Integer age);

    List<PlayerEntity> findByActiveAndPlayerDetail_AgeAndNameContainingIgnoreCase(Boolean active, Integer age, String name);

    List<PlayerEntity> findByActiveAndNameContainingIgnoreCase(Boolean active, String name);

    List<PlayerEntity> findByPlayerDetail_Age(Integer age);

    List<PlayerEntity> findByNameContainingIgnoreCase(String name);

    List<PlayerEntity> findByPlayerDetail_AgeAndNameContainingIgnoreCase(Integer age, String name);

    List<PlayerEntity> findByActiveTrue();

    List<PlayerEntity> findByActiveFalse();

    List<PlayerEntity> findByPlayerDetail_AgeGreaterThanAndPlayerDetail_AgeLessThan(Integer ageMin, Integer ageMax);

    List<PlayerEntity> findByPlayerDetail_AgeGreaterThanEqualAndPlayerDetail_AgeLessThanEqual(Integer ageMin, Integer ageMax);
}

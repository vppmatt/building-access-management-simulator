package com.neueda.buildingaccessmanagementsimulator.data;

import com.neueda.buildingaccessmanagementsimulator.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByName(String name);
}

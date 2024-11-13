package com.neueda.buildingaccessmanagementsimulator.data;

import com.neueda.buildingaccessmanagementsimulator.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
}

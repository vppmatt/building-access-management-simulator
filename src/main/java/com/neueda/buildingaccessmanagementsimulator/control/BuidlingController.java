package com.neueda.buildingaccessmanagementsimulator.control;


import com.neueda.buildingaccessmanagementsimulator.data.BuildingRepository;
import com.neueda.buildingaccessmanagementsimulator.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/building")
@CrossOrigin
public class BuidlingController {

    @Autowired
    BuildingRepository buildingRepository;

    @GetMapping()
    public List<Building> getAll() {
        return buildingRepository.findAll();
    }
}

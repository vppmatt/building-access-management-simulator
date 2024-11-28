package com.neueda.buildingaccessmanagementsimulator.control;


import com.neueda.buildingaccessmanagementsimulator.data.BuildingRepository;
import com.neueda.buildingaccessmanagementsimulator.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Building create(@RequestBody Building building) {
        return buildingRepository.save(building);
    }

    @PutMapping("/{id}")
    public Building update(@PathVariable("id") Integer id, @RequestBody Building building) {
        building.setId(id);
        return buildingRepository.save(building);
    }
}

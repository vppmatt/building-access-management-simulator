package com.neueda.buildingaccessmanagementsimulator.data;

import com.neueda.buildingaccessmanagementsimulator.model.AccessRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface AccessRepository extends JpaRepository<AccessRecord, Integer> {
    Page<AccessRecord> findAllByDatasetAndTimeLessThan(PageRequest pr, int dataset, LocalTime now);


    List<AccessRecord> findAllByDatasetAndTimeLessThan(int dataset, LocalTime now);

    List<AccessRecord> findAllByDataset(int dataset);

    Page<AccessRecord> findAllByDataset(PageRequest pr, int dataset);
}

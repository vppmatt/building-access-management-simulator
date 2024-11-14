package com.neueda.buildingaccessmanagementsimulator.control;

import com.neueda.buildingaccessmanagementsimulator.data.AccessRepository;
import com.neueda.buildingaccessmanagementsimulator.exceptions.DateCannotBeInTheFutureException;
import com.neueda.buildingaccessmanagementsimulator.model.AccessRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin
public class AccessController {



    @Autowired
    AccessRepository accessRepository;

    private Integer getDataset(LocalDate date) {
        return 1 + date.getDayOfYear() % 5;
    }

    @GetMapping()
    public Map<String, String> getInfo() {
        return Map.of("info", "To use this api supply a date, eg /api/logs/yyyyMMdd. Optional params are ?all=true or ?page=x - if not supplied first 50 records will be returned");
    }

    @GetMapping("/{date}")
    public List<AccessRecord> getAll(@PathVariable("date") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date, @RequestParam(name = "all",defaultValue = "false") Boolean all, @RequestParam(name = "page",defaultValue = "0") Integer page) throws DateCannotBeInTheFutureException {

        if (date.isAfter(LocalDate.now())) {
            throw new DateCannotBeInTheFutureException("Date cannot be in the future : " + date.toString());
        }

        int dataset = getDataset(date);

        if (date.equals(LocalDate.now())) {
            if (all) {
                return accessRepository.findAllByDatasetAndTimeLessThan(dataset, LocalTime.now());
            }

            Integer size = 50;
            PageRequest pageable = PageRequest.of(page, size);
            Page<AccessRecord> pageAccessRecord = accessRepository.findAllByDatasetAndTimeLessThan(pageable,dataset, LocalTime.now());

            return pageAccessRecord.getContent();


        }
        else {
            if (all) {
                return accessRepository.findAllByDataset(dataset);
            }

            Integer size = 50;
            PageRequest pageable = PageRequest.of(page, size);
            Page<AccessRecord> pageAccessRecord = accessRepository.findAllByDataset(pageable, dataset);

            return pageAccessRecord.getContent();

        }


    }

}

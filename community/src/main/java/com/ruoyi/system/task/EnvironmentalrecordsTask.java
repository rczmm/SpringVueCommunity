package com.ruoyi.system.task;

import com.ruoyi.system.domain.Environmentalrecords;
import com.ruoyi.system.service.IEnvironmentalrecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("EnvironmentalrecordsTask")
public class EnvironmentalrecordsTask {

    private final IEnvironmentalrecordsService environmentalrecordsService;

    @Autowired
    public EnvironmentalrecordsTask(IEnvironmentalrecordsService environmentalrecordsService) {
        this.environmentalrecordsService = environmentalrecordsService;
    }

    public void runDay() {
        Environmentalrecords record = new Environmentalrecords();
        record.setDate(new Date());
        environmentalrecordsService.insertEnvironmentalrecords(record);
    }
}

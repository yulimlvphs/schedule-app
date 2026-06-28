package com.yulim.scheduleapp.controller;
import com.yulim.scheduleapp.dto.ScheduleCreateRequest;
import com.yulim.scheduleapp.dto.ScheduleResponse;
import com.yulim.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponse createSchedule(
            @RequestBody ScheduleCreateRequest request
    ) {
        return scheduleService.createSchedule(request);
    }
}
